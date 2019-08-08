package com.fobidlim.kakaypay.viewmodels

import android.annotation.SuppressLint
import com.fobidlim.kakaypay.libs.ActivityViewModel
import com.fobidlim.kakaypay.libs.Environment
import com.fobidlim.kakaypay.ui.activities.SignInActivity
import com.fobidlim.kakaypay.ui.dialogs.InstagramAuthDialog
import io.reactivex.Observable
import io.reactivex.subjects.CompletableSubject
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

@SuppressLint("CheckResult")
class SignInViewModel @Inject constructor(
    private val environment: Environment
) : ActivityViewModel<SignInActivity>(), InstagramAuthDialog.Delegate {

    private val signInClick = PublishSubject.create<Any>()
    private val instagramAccessToken = PublishSubject.create<String>()

    private val showInstagramAuthDialog: Observable<Any>
    private val showMainActivity = CompletableSubject.create()

    init {
        showInstagramAuthDialog = signInClick

        instagramAccessToken
            .switchMap { accessToken ->
                user(accessToken)
                    .map { accessToken to it }
            }
            .doOnError { Timber.w(it, "accessToken: ") }
            .doOnNext { environment.currentUser.login(it.second, it.first) }
            .compose(bindToLifecycle())
            .subscribe { showMainActivity.onComplete() }
    }

    private fun user(accessToken: String) =
        environment.apiClient.user(accessToken)
            .toObservable()

    fun signInClick() = signInClick.onNext(0)

    override fun instagramAccessTokenReceived(token: String) = instagramAccessToken.onNext(token)

    fun showInstagramAuthDialog() = showInstagramAuthDialog
    fun showMainActivity() = showMainActivity
}