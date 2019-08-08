package com.fobidlim.kakaypay.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserCount(
    val media: Int,
    val follows: Int,
    @SerializedName("followed_by") val followedBy: Int
) : Parcelable