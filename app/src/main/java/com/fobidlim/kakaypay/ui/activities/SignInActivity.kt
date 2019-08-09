package com.fobidlim.kakaypay.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.databinding.DataBindingUtil
import com.fobidlim.kakaypay.R
import com.fobidlim.kakaypay.ViewModelFactory
import com.fobidlim.kakaypay.applicationComponent
import com.fobidlim.kakaypay.databinding.ActivitySignInBinding
import com.fobidlim.kakaypay.getViewModel
import com.fobidlim.kakaypay.libs.BaseActivity
import com.fobidlim.kakaypay.ui.dialogs.InstagramAuthDialog
import com.fobidlim.kakaypay.viewmodels.SignInViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class SignInActivity : BaseActivity<SignInViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        applicationComponent.inject(this)
        viewModel = getViewModel(viewModelFactory)

        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivitySignInBinding>(this, R.layout.activity_sign_in)
            .apply {
                vm = viewModel
            }

        viewModel.showInstagramAuthDialog()
            .compose(bindToLifecycle())
            .observeOn(AndroidSchedulers.mainThread())
            .map { InstagramAuthDialog(this, viewModel) }
            .subscribe { it.show() }

        addDisposable(
            viewModel.showMainActivity()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { showMainActivity() })

        viewModel.showErrorToast()
            .compose(bindToLifecycle())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                TODO("show toast")
            }
    }

    private fun showMainActivity() =
        Intent(this, MainActivity::class.java).let {
            startActivity(it)
            finish()
        }
}