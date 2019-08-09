package com.fobidlim.kakaypay.libs

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.fobidlim.kakaypay.models.MediaResolution

object MediaBindingAdapter {

    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(imageView: ImageView, media: MediaResolution) {
        Glide.with(imageView)
            .load(media.url)
            .override(media.width, media.height)
            .into(imageView)
    }
}