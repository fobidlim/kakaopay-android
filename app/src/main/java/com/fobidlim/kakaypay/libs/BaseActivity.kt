package com.fobidlim.kakaypay.libs

import android.content.Intent
import android.view.MenuItem
import androidx.annotation.CallSuper
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import timber.log.Timber

open class BaseActivity<ViewModelType : ActivityViewModel<*>> : RxAppCompatActivity(), ActivityLifeCycleType {

    companion object {
        private const val VIEW_MODEL_KEY = "viewModel"
    }

    protected lateinit var viewModel: ViewModelType

    @CallSuper
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate ${toString()}")

        viewModel.apply {
            intent(intent)
            onCreate()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
        when (item?.itemId) {
            android.R.id.home -> onBackPressed().run { true }
            else -> super.onOptionsItemSelected(item)
        }

    @CallSuper
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let {
            viewModel.intent(intent)
        }
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        Timber.d("onStart ${toString()}")
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        Timber.d("onResume ${toString()}")

        viewModel.onResume(this)
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
        Timber.d("onPause ${toString()}")

        viewModel.onPause()
    }

    override fun onStop() {
        super.onStop()
        Timber.d("onStop ${toString()}")

        viewModel.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy ${toString()}")

        viewModel.onDestroy()
    }
}