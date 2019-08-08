package com.fobidlim.kakaypay.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.fobidlim.kakaypay.R
import com.fobidlim.kakaypay.ViewModelFactory
import com.fobidlim.kakaypay.applicationComponent
import com.fobidlim.kakaypay.getViewModel
import com.fobidlim.kakaypay.libs.BaseActivity
import com.fobidlim.kakaypay.viewmodels.SplashViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class SplashActivity : BaseActivity<SplashViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        applicationComponent.inject(this)
        viewModel = getViewModel(viewModelFactory)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel.showMainActivity()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { showMainActivity() }

        viewModel.showSignInActivity()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { showSignInActivity() }
    }

    private fun showMainActivity() =
        Intent(this, MainActivity::class.java).let {
            startActivity(it)
            finish()
        }

    private fun showSignInActivity() =
        Intent(this, SignInActivity::class.java).let {
            startActivity(it)
            finish()
        }
}
