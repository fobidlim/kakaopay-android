package com.fobidlim.kakaypay.libs.rx.transformers

import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.functions.Consumer
import org.reactivestreams.Publisher

class NeverErrorTransformer<T> : FlowableTransformer<T, T> {

    private val errorAction: Consumer<Throwable>?

    constructor() {
        this.errorAction = null
    }

    constructor(errorAction: Consumer<Throwable>?) {
        this.errorAction = errorAction
    }

    override fun apply(upstream: Flowable<T>): Publisher<T> {
        return upstream
            .doOnError { this.errorAction?.accept(it) }
            .onErrorResumeNext(Flowable.empty())
    }
}
