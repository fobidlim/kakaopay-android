package com.fobidlim.kakaypay.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MediaResolutions(
    @SerializedName("low_resolution") val lowResolution: MediaResolution,
    val thumbnail: MediaResolution,
    @SerializedName("standard_resolution") val standardResolution: MediaResolution
) : Parcelable