package com.fobidlim.kakaypay.libs.rx.operators

import com.fobidlim.kakaypay.models.Envelope
import com.google.gson.Gson

object Operators {

    fun <T : Envelope> apiError(gson: Gson) = ApiErrorOperator<T>(gson)
}