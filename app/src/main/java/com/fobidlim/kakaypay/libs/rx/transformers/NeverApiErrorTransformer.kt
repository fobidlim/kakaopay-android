package com.fobidlim.kakaypay.libs.rx.transformers

import com.fobidlim.kakaypay.services.apiresponses.ErrorEnvelope
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import org.reactivestreams.Publisher

class NeverApiErrorTransformer<T> : FlowableTransformer<T, T> {

    private val errorAction: Consumer<ErrorEnvelope>?

    constructor() {
        this.errorAction = null
    }

    constructor(errorAction: Consumer<ErrorEnvelope>?) {
        this.errorAction = errorAction
    }

    override fun apply(upstream: Flowable<T>): Publisher<T> {
        return upstream
            .doOnError {
                ErrorEnvelope.fromThrowable(it)
                    ?.let { envelope ->
                        errorAction?.accept(envelope)
                    }
            }
            .onErrorResumeNext(Function { Flowable.empty() })
    }
}
