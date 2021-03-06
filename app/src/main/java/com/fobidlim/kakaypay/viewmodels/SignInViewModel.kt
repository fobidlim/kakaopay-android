package com.fobidlim.kakaypay.viewmodels

import android.annotation.SuppressLint
import com.fobidlim.kakaypay.libs.ActivityViewModel
import com.fobidlim.kakaypay.libs.Environment
import com.fobidlim.kakaypay.libs.rx.transformers.Transformers.pipeApiErrorsTo
import com.fobidlim.kakaypay.libs.rx.transformers.Transformers.pipeErrorsTo
import com.fobidlim.kakaypay.services.apiresponses.ErrorEnvelope
import com.fobidlim.kakaypay.ui.activities.SignInActivity
import com.fobidlim.kakaypay.ui.dialogs.InstagramAuthDialog
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.CompletableSubject
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@SuppressLint("CheckResult")
class SignInViewModel @Inject constructor(
    private val environment: Environment
) : ActivityViewModel<SignInActivity>(), InstagramAuthDialog.Delegate {

    private val signInClick = PublishSubject.create<Any>()
    private val instagramAccessToken = PublishSubject.create<String>()
    private val apiError = PublishSubject.create<ErrorEnvelope>()
    private val networkError = PublishSubject.create<Throwable>()

    private val showInstagramAuthDialog: Observable<Any>
    private val showMainActivity = CompletableSubject.create()
    private val showErrorToast = BehaviorSubject.create<String>()

    init {
        showInstagramAuthDialog = signInClick

        instagramAccessToken
            .switchMap { accessToken ->
                user(accessToken)
                    .map { accessToken to it }
            }
            .doOnNext { environment.currentUser.login(it.second, it.first) }
            .compose(bindToLifecycle())
            .subscribe { showMainActivity.onComplete() }

        Observable.merge(
            apiError
                .doOnNext { environment.currentUser.logout() }
                .map { it.meta.errorMessage },
            networkError
                .map { it.localizedMessage }
        )
            .compose(bindToLifecycle())
            .subscribe(showErrorToast)
    }

    private fun user(accessToken: String) =
        environment.apiClient.user(accessToken)
            .compose(pipeApiErrorsTo(apiError))
            .compose(pipeErrorsTo(networkError))
            .toObservable()

    fun signInClick() = signInClick.onNext(0)

    override fun instagramAccessTokenReceived(token: String) = instagramAccessToken.onNext(token)

    fun showInstagramAuthDialog() = showInstagramAuthDialog
    fun showMainActivity() = showMainActivity
    fun showErrorToast() = showErrorToast
}