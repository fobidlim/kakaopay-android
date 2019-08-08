package com.fobidlim.kakaypay.viewmodels

import android.annotation.SuppressLint
import com.fobidlim.kakaypay.libs.ActivityViewModel
import com.fobidlim.kakaypay.libs.Environment
import com.fobidlim.kakaypay.ui.activities.SplashActivity
import io.reactivex.subjects.CompletableSubject
import javax.inject.Inject

@SuppressLint("CheckResult")
class SplashViewModel @Inject constructor(
    environment: Environment
) : ActivityViewModel<SplashActivity>() {

    private val showMainActivity = CompletableSubject.create()
    private val showSignInActivity = CompletableSubject.create()

    init {
        environment.currentUser.isLoggedIn()
            .compose(bindToLifecycle())
            .subscribe {
                when (it) {
                    true -> showMainActivity.onComplete()
                    else -> showSignInActivity.onComplete()
                }
            }
    }

    fun showMainActivity() = showMainActivity
    fun showSignInActivity() = showSignInActivity
}