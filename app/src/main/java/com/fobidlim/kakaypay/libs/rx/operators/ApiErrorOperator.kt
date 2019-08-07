package com.fobidlim.kakaypay.libs.rx.operators

import com.fobidlim.kakaypay.models.Envelope
import com.fobidlim.kakaypay.services.ApiException
import com.fobidlim.kakaypay.services.ResponseException
import retrofit2.Response

class ApiErrorOperator<T : Envelope>(
    private val gson: Gson
) : FlowableOperator<T, Response<T>> {

    @Throws(Exception::class)
    override fun apply(observer: Subscriber<in T>): Subscriber<in Response<T>> =
        object : Subscriber<Response<T>> {

            override fun onSubscribe(s: Subscription) {
                observer.onSubscribe(s)
            }

            override fun onNext(tResponse: Response<T>) {
                when (tResponse.isSuccessful) {
                    true -> observer.onNext(tResponse.body())
                    else -> {
                        try {
                            gson.fromJson(tResponse.errorBody()!!.string(), Envelope::class.java).let {
                                observer.onError(ApiException(it, tResponse))
                            }
                        } catch (e: IOException) {
                            observer.onError(ResponseException(tResponse))
                        }
                    }
                }
            }
        }
}