package com.fobidlim.kakaypay.libs.rx.transformers

import com.fobidlim.kakaypay.models.Envelope

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
            .doOnError { e ->
                val env = Envelope.fromThrowable(e)
                if (env != null && this.errorAction != null) {
                    this.errorAction.accept(env)
                }
            }
            .onErrorResumeNext(Function { Flowable.empty() })
    }
}
