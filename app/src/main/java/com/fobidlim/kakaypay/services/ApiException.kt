package com.fobidlim.kakaypay.services

import com.fobidlim.kakaypay.services.apiresponses.ErrorEnvelope
import retrofit2.Response

class ApiException(
    val errorEnvelope: ErrorEnvelope,
    response: Response<*>
) : ResponseException(response)