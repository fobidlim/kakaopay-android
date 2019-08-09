package com.fobidlim.kakaypay.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(
    val id: String,
    val text: String,
    val from: User,
    @SerializedName("created_time") val createdTime: String
) : Parcelable