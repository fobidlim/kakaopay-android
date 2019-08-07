package com.fobidlim.kakaypay

import android.app.Application
import timber.log.Timber

class ThisApplication : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
            .apply {
                inject(this@ThisApplication)
            }

        Timber.plant(Timber.DebugTree())
    }
}