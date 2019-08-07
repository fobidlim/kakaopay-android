package com.fobidlim.kakaypay.services

import com.fobidlim.kakaypay.models.Envelope
import retrofit2.Response

class ApiException(
    val errorEnvelope: Envelope,
    val response: Response<*>
): ResponseException(response)