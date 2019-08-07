package com.fobidlim.kakaypay.ui.activities

import com.fobidlim.kakaypay.R
import com.fobidlim.kakaypay.libs.BaseActivity
import com.fobidlim.kakaypay.viewmodels.SignInViewModel

class SignInActivity : BaseActivity<SignInViewModel>() {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sign_in)
    }
}