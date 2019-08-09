package com.fobidlim.kakaypay.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Media(
    val id: String,
    val comments: Comments? = null,
    val caption: Caption? = null,
    val likes: Likes? = null,
    val link: String = "",
    val user: User,
    val images: MediaResolutions? = null,
    val type: String,
    val filter: String,
    val tags: MutableList<String>,
    val location: Location? = null,
    @SerializedName("created_time") val createdTime: String,
    val videos: MediaResolutions? = null
) : Parcelable