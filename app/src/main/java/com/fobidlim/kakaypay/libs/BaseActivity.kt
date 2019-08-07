package com.fobidlim.kakaypay.libs

import com.trello.rxlifecycle3.components.support.RxAppCompatActivity

open class BaseActivity<ViewModelType : ActivityViewModel<*>> : RxAppCompatActivity(), ActivityLifeCycleType {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
    }
}