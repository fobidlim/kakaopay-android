package com.fobidlim.kakaypay.ui.activities

import android.os.Bundle
import com.fobidlim.kakaypay.R
import com.fobidlim.kakaypay.ViewModelFactory
import com.fobidlim.kakaypay.applicationComponent
import com.fobidlim.kakaypay.getViewModel
import com.fobidlim.kakaypay.libs.BaseActivity
import com.fobidlim.kakaypay.viewmodels.MainViewModel
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        applicationComponent.inject(this)
        viewModel = getViewModel(viewModelFactory)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }
}