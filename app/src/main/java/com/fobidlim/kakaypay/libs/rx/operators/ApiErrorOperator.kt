package com.fobidlim.kakaypay.libs.rx.operators

import com.fobidlim.kakaypay.services.ApiException
import com.fobidlim.kakaypay.services.ResponseException
import com.fobidlim.kakaypay.services.apiresponses.ErrorEnvelope
import com.google.gson.Gson
import io.reactivex.FlowableOperator
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import retrofit2.Response
import java.io.IOException

class ApiErrorOperator<T>(
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
                            gson.fromJson(tResponse.errorBody()!!.string(), ErrorEnvelope::class.java).let {
                                observer.onError(ApiException(it, tResponse))
                            }
                        } catch (e: IOException) {
                            observer.onError(ResponseException(tResponse))
                        }
                    }
                }
            }

            override fun onError(t: Throwable?) {
                observer.onError(t)
            }

            override fun onComplete() {
                observer.onComplete()
            }
        }
}