package com.fobidlim.kakaypay.libs.rx.transformers

import com.fobidlim.kakaypay.models.Envelope

object Transformers {

    fun <T> neverError() = NeverErrorTransformer()

    fun <T> pipeErrorsTo(errorSubject: PublishSubject<Throwable>) =
        NeverErrorTransformer(Consumer { errorSubject.onNext(it) })

    fun <T> neverApiError() = NeverApiErrorTransformer()


    fun <T> pipeApiErrorsTo(errorSubject: PublishSubject<Envelope>) =
        NeverApiErrorTransformer(Consumer<Envelope> { errorSubject.onNext(it) })
}
