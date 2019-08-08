package com.fobidlim.kakaypay.models

import kotlinx.android.parcel.Parcelize

@Parcelize
data class MediaResolutions(
    @SerializedName("low_resolution") val lowResolution: MediaResolution,
    val thumbnail: MediaResolution,
    @SerializedName("standard_resolution") val standardResolution: MediaResolution
) : Parcelable