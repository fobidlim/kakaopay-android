package com.fobidlim.kakaypay.models

import kotlinx.android.parcel.Parcelize

@Parcelize
data class MediaResolution(
    val url: String,
    val width: Int,
    val heightg: Int
) : Parcelable