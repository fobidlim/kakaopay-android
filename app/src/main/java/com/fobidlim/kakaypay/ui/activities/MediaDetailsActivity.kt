package com.fobidlim.kakaypay.ui.activities

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.databinding.DataBindingUtil
import com.fobidlim.kakaypay.R
import com.fobidlim.kakaypay.ViewModelFactory
import com.fobidlim.kakaypay.applicationComponent
import com.fobidlim.kakaypay.databinding.ActivityMediaDetailsBinding
import com.fobidlim.kakaypay.getViewModel
import com.fobidlim.kakaypay.libs.BaseActivity
import com.fobidlim.kakaypay.models.Location
import com.fobidlim.kakaypay.ui.fragments.CommentsBottomSheetDialog
import com.fobidlim.kakaypay.viewmodels.MediaDetailsViewModel
import com.fobidlim.kakaypay.viewmodels.MediaDetailsViewModel.Companion.GOOGLE_MAPS_PACKAGE_NAME
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_media_details.*
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
                .apply {
                    vm = viewModel
                }

        setSupportActionBar(toolbar)
            .apply {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }

        viewModel.setMedia()
            .compose(bindToLifecycle())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { binding.media = it }

        viewModel.setCaptionVisibility()
            .compose(bindToLifecycle())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { binding.captionVisibility = it }

        viewModel.openGoogleMaps()
            .compose(bindToLifecycle())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::openGoogleMaps)

        viewModel.showCommentsDetails()
            .compose(bindToLifecycle())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::showCommentsDetails)
    }

    private fun openGoogleMaps(location: Location) =
        Intent(Intent.ACTION_VIEW)
            .apply {
                data = Uri.parse("geo:${location.latitude},${location.longitude}")
            }
            .run {
                try {
                    // open Google Maps application
                    startActivity(this)
                } catch (e: ActivityNotFoundException) {
                    data = Uri.parse("market://details?id=$GOOGLE_MAPS_PACKAGE_NAME")

                    try {
                        // open Google Play application
                        startActivity(this)
                    } catch (e1: ActivityNotFoundException) {
                        data = Uri.parse("https://play.google.com/store/apps/details?id=$GOOGLE_MAPS_PACKAGE_NAME")

                        // open browser
                        startActivity(this)
                    }
                }
            }

    private fun showCommentsDetails(mediaId: String) =
        CommentsBottomSheetDialog.newInstance(mediaId)
            .show(supportFragmentManager, mediaId)
}