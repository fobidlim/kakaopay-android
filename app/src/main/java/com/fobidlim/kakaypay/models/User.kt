package com.fobidlim.kakaypay.models

import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: String,
    val username: String,
    @SerializedName("full_name") val fullName: String,
    @SerializeName("profile_picture") val profilePicture: String,
    val bio: String,
    val website: String,
    @SerializeName("is_business") val isBusiness: Boolean,
    val counts: UserCount
) : Parcelable