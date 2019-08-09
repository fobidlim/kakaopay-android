package com.fobidlim.kakaypay.services.apiresponses

import com.fobidlim.kakaypay.models.ErrorMeta
import com.fobidlim.kakaypay.services.ApiException

data class ErrorEnvelope(
    val meta: ErrorMeta
) {
    companion object {

        fun fromThrowable(t: Throwable): ErrorEnvelope? =
            when (t) {
                is ApiException -> t.errorEnvelope
                else -> null
            }
    }
}