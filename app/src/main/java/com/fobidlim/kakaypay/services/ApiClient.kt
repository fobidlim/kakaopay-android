package com.fobidlim.kakaypay.services

import com.fobidlim.kakaypay.libs.rx.operators.Operators
import com.fobidlim.kakaypay.models.Envelope
import com.fobidlim.kakaypay.models.User
import com.fobidlim.kakaypay.services.apiresponses.RecentMediaEnvelope
import com.fobidlim.kakaypay.services.apiresponses.UserEnvelope
import com.google.gson.Gson
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class ApiClient(
    private val gson: Gson,
    private val apiService: ApiService
) : ApiClientType {

    override fun user(accessToken: String): Flowable<User> =
        apiService.user(accessToken)
            .toFlowable()
            .lift(apiErrorOperator<UserEnvelope>())
            .subscribeOn(Schedulers.io())
            .map { it.data }

    override fun recentMedia(): Flowable<MutableList<Media>> =
        apiService.recentMedia()
            .toFlowable()
            .lift(apiErrorOperator<RecentMediaEnvelope>())
            .subscribeOn(Schedulers.io())
            .map { it.data }

    private fun <T : Envelope> apiErrorOperator() = Operators.apiError<T>(gson)
}