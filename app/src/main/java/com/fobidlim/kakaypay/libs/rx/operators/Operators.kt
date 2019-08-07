package com.fobidlim.kakaypay.libs.rx.operators

import com.fobidlim.kakaypay.models.Envelope

object Operators {

    fun <T : Envelope> apiError(gson: Gson) = ApiErrorOperator(gson)
}