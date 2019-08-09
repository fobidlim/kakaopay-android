package com.fobidlim.kakaypay.libs.rx.operators

import com.google.gson.Gson

object Operators {

    fun <T> apiError(gson: Gson) = ApiErrorOperator<T>(gson)
}