package com.fobidlim.kakaypay.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MediaResolution(
    val url: String,
    val width: Int,
    val height: Int
) : Parcelable