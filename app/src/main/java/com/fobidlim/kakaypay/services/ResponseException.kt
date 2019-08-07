package com.fobidlim.kakaypay.services

import retrofit2.Response

open class ResponseException(
    val response: Response<*>
) : RuntimeException()