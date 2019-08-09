package com.fobidlim.kakaypay.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comments(
    val count: Int
) : Parcelable