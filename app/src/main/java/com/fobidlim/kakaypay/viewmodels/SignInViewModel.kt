package com.fobidlim.kakaypay.viewmodels

import com.fobidlim.kakaypay.libs.ActivityViewModel
import com.fobidlim.kakaypay.libs.Environment
import com.fobidlim.kakaypay.ui.activities.SignInActivity
import com.fobidlim.kakaypay.ui.dialogs.InstagramAuthDialog
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val environment: Environment
) : ActivityViewModel<SignInActivity>(), InstagramAuthDialog.Delegate {

    private val signInClick = PublishSubject.create<Any>()
    private val instagramAccessToken = PublishSubject.create<String>()

    private val showInstagramAuthDialog: Observable<Any>

    init {
        showInstagramAuthDialog = signInClick

        instagramAccessToken
            .switchMap { user(it) }
            .compose(bindToLifecycle())
            .subscribe {}
    }

    private fun user(accessToken: String) =
        environment.apiClient.user(accessToken)
            .toObservable()

    fun signInClick() = signInClick.onNext(0)

    override fun instagramAccessTokenReceived(token: String) = instagramAccessToken.onNext(token)

    fun showInstagramAuthDialog() = showInstagramAuthDialog
}