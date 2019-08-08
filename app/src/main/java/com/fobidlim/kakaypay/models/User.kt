package com.fobidlim.kakaypay.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: String,
    val username: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("profile_picture") val profilePicture: String,
    val bio: String,
    val website: String,
    @SerializedName("is_business") val isBusiness: Boolean,
    val counts: UserCount
) : Parcelable