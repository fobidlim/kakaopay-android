package com.fobidlim.kakaypay

import com.fobidlim.kakaypay.libs.Environment
import com.fobidlim.kakaypay.ui.activities.MainActivity
import com.fobidlim.kakaypay.ui.activities.MediaDetailsActivity
import com.fobidlim.kakaypay.ui.activities.SignInActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ActivityViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun environment(): Environment

    fun inject(application: ThisApplication)

    fun inject(activity: SignInActivity)
    fun inject(activity: MainActivity)
    fun inject(activity: MediaDetailsActivity)
}