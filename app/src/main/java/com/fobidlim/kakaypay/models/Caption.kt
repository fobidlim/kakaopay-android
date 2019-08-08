package com.fobidlim.kakaypay.models

import kotlinx.android.parcel.Parcelize

@Parcelize
data class Caption(
    val id: String,
    @SerializedName("created_time") val createdTime: String,
    val text: String,
    val from: User
) : Parcelable