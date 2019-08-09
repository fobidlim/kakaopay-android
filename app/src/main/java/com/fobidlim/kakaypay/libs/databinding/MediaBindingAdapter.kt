package com.fobidlim.kakaypay.libs.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.fobidlim.kakaypay.R
import com.fobidlim.kakaypay.models.MediaResolution

object MediaBindingAdapter {

    @JvmStatic
    @BindingAdapter("loadImage", "loadThumbnail", requireAll = false)
    fun loadImage(imageView: ImageView, thumbnail: MediaResolution?, media: MediaResolution?) {
        when {
            thumbnail == null && media == null ->
                Glide.with(imageView)
                    .load(R.mipmap.ic_launcher_round)
                    .into(imageView)

            thumbnail == null && media != null ->
                Glide.with(imageView)
                    .load(media.url)
                    .override(media.width, media.height)
                    .into(imageView)

            thumbnail != null && media == null ->
                Glide.with(imageView)
                    .load(thumbnail.url)
                    .override(thumbnail.width, thumbnail.height)
                    .into(imageView)

            thumbnail != null && media != null ->
                Glide.with(imageView)
                    .load(media.url)
                    .override(media.width, media.height)
                    .thumbnail(
                        Glide.with(imageView)
                            .load(thumbnail.url)
                            .override(thumbnail.width, thumbnail.height)
                    )
                    .into(imageView)
        }
    }
}