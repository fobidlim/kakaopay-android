package com.fobidlim.kakaypay.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Caption(
    val id: String,
    @SerializedName("created_time") val createdTime: String,
    val text: String,
    val from: User
) : Parcelable