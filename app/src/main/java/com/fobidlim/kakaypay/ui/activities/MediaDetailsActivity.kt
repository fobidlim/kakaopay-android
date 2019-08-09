package com.fobidlim.kakaypay.ui.activities

import android.annotation.SuppressLint
import androidx.databinding.DataBindingUtil
import com.fobidlim.kakaypay.R
import com.fobidlim.kakaypay.ViewModelFactory
import com.fobidlim.kakaypay.applicationComponent
import com.fobidlim.kakaypay.databinding.ActivityMediaDetailsBinding
import com.fobidlim.kakaypay.getViewModel
import com.fobidlim.kakaypay.libs.BaseActivity
import com.fobidlim.kakaypay.viewmodels.MediaDetailsViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_media_details.*
import timber.log.Timber
import javax.inject.Inject

class MediaDetailsActivity : BaseActivity<MediaDetailsViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        applicationComponent.inject(this)
        viewModel = getViewModel(viewModelFactory)

        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMediaDetailsBinding>(this, R.layout.activity_media_details)

        setSupportActionBar(toolbar)
            .apply {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }

        viewModel.setMedia()
            .compose(bindToLifecycle())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { Timber.d("setMedia? $it") }
            .subscribe { binding.media = it }
    }
}