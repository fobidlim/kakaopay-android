package com.fobidlim.kakaypay.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    val id: String,
    val latitude: Double,
    val longitude: Double,
    @SerializedName("street_address") val streetAddress: String,
    val name: String
) : Parcelable