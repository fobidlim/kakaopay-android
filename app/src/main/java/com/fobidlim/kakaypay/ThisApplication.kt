package com.fobidlim.kakaypay

import android.app.Application

class ThisApplication : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
            .build()
            .apply {
                inject(this@ThisApplication)
            }
    }
}