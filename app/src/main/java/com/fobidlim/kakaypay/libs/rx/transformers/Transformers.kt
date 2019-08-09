package com.fobidlim.kakaypay.libs.rx.transformers

import com.fobidlim.kakaypay.models.Envelope
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject

object Transformers {

    fun <T> neverError() = NeverErrorTransformer<T>()

    fun <T> pipeErrorsTo(errorSubject: PublishSubject<Throwable>) =
        NeverErrorTransformer<T>(Consumer { errorSubject.onNext(it) })

    fun <T> neverApiError() = NeverApiErrorTransformer<T>()


    fun <T> pipeApiErrorsTo(errorSubject: PublishSubject<Envelope>) =
        NeverApiErrorTransformer<T>(Consumer { errorSubject.onNext(it) })
}
