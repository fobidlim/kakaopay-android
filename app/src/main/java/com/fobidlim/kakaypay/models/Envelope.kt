package com.fobidlim.kakaypay.models

import com.fobidlim.kakaypay.services.ApiException

open class Envelope {

    var code: String = ""

    companion object {

        fun fromThrowable(t: Throwable): Envelope? =
            when (t) {
                is ApiException -> t.errorEnvelope
                else -> null
            }
    }
}