package com.fobidlim.kakaypay.ui.activities

import androidx.databinding.DataBindingUtil
import com.fobidlim.kakaypay.R
import com.fobidlim.kakaypay.ViewModelFactory
import com.fobidlim.kakaypay.applicationComponent
import com.fobidlim.kakaypay.databinding.ActivitySignInBinding
import com.fobidlim.kakaypay.getViewModel
import com.fobidlim.kakaypay.libs.BaseActivity
import com.fobidlim.kakaypay.viewmodels.SignInViewModel
import javax.inject.Inject

class SignInActivity : BaseActivity<SignInViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        applicationComponent.inject(this)
        viewModel = getViewModel(viewModelFactory)

        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivitySignInBinding>(this, R.layout.activity_sign_in)
            .apply {
                viewModel = this@SignInActivity.viewModel
            }
    }
}