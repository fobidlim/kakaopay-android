package com.fobidlim.kakaypay.services

import com.fobidlim.kakaypay.services.apiresponses.UserEnvelope
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/users/self")
    fun user(@Query("access_token") accessToken: String): Single<Response<UserEnvelope>>
}