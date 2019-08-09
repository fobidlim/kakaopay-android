package com.fobidlim.kakaypay.models

import com.google.gson.annotations.SerializedName

data class ErrorMeta(
    val code: String,
    @SerializedName("error_type") val errorType: String,
    @SerializedName("error_message") val errorMessage: String
)