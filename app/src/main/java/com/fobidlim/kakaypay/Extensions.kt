package com.fobidlim.kakaypay

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

val Activity.app: ThisApplication
    get() = application as ThisApplication

val FragmentActivity.applicationComponent: ApplicationComponent
    get() = app.component

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(viewModelFactory: ViewModelProvider.Factory): T =
    ViewModelProviders.of(this, viewModelFactory)[T::class.java]