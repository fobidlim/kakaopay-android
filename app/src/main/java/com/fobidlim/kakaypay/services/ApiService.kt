package com.fobidlim.kakaypay.services

import com.fobidlim.kakaypay.services.apiresponses.CommentsEnvelope
import com.fobidlim.kakaypay.services.apiresponses.RecentMediaEnvelope
import com.fobidlim.kakaypay.services.apiresponses.UserEnvelope
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v1/users/self")
    fun user(@Query("access_token") accessToken: String): Single<Response<UserEnvelope>>

    @GET("v1/users/self/media/recent")
    fun recentMedia(): Single<Response<RecentMediaEnvelope>>

    @GET("v1/media/{mediaId}/comments")
    fun comments(@Path("mediaId") mediaId: String): Single<Response<CommentsEnvelope>>
}