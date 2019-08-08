package com.fobidlim.kakaypay.viewmodels

import com.fobidlim.kakaypay.libs.ActivityViewModel
import com.fobidlim.kakaypay.libs.Environment
import com.fobidlim.kakaypay.ui.activities.MainActivity
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val environment: Environment
) : ActivityViewModel<MainActivity>() {

    init {
        environment.currentUser.loggedInUser()
            .switchMap {}
            .compose(bindToLifecycle())
            .subscribe {}
    }

    private fun recentMedia(accessToken: String) = environment.cli
}