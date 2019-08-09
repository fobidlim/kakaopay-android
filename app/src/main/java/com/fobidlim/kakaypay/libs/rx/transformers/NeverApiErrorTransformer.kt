package com.fobidlim.kakaypay.libs.rx.transformers

import com.fobidlim.kakaypay.models.Envelope
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import org.reactivestreams.Publisher

class NeverApiErrorTransformer<T> : FlowableTransformer<T, T> {

    private val errorAction: Consumer<Envelope>?

    constructor() {
        this.errorAction = null
    }

    constructor(errorAction: Consumer<Envelope>?) {
        this.errorAction = errorAction
    }

    override fun apply(upstream: Flowable<T>): Publisher<T> {
        return upstream
            .doOnError {
                Envelope.fromThrowable(it)
                    ?.let { envelope ->
                        errorAction?.accept(envelope)
                    }
            }
            .onErrorResumeNext(Function { Flowable.empty() })
    }
}
