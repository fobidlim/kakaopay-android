package com.fobidlim.kakaypay.viewmodels

import com.fobidlim.kakaypay.libs.ActivityViewModel
import com.fobidlim.kakaypay.libs.Environment
import com.fobidlim.kakaypay.ui.activities.SignInActivity
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val environment: Environment
) : ActivityViewModel<SignInActivity>() {

    private val signInClick = PublishSubject.create<Any>()

    init {
        signInClick
            .compose(bindToLifecycle())
            .subscribe {}
    }

    fun signInClick() = signInClick.onNext(0)
}