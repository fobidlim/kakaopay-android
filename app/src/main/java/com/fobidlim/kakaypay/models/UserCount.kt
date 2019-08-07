package com.fobidlim.kakaypay.models

import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserCount(
    val media: Int,
    val follows: Int,
    @SerializeName("followed_by") val followedBy: Int
) : Parcelable