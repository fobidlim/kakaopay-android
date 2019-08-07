package com.fobidlim.kakaypay.ui.activities

import com.fobidlim.kakaypay.R
import com.fobidlim.kakaypay.libs.BaseActivity
import com.fobidlim.kakaypay.viewmodels.MediaDetailsViewModel

class MediaDetailsActivity : BaseActivity<MediaDetailsViewModel>() {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_media_details)
    }
}