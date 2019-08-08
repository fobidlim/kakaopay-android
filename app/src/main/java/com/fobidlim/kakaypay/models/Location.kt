package com.fobidlim.kakaypay.models

import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    val id: String,
    val latitude: Double,
    val longitude: Double,
    @SerializedName("street_address") val streetAddress: String,
    val name: String
) : Parcelable