package com.fobidlim.kakaypay.libs.rx.transformers

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
            .doOnError { e ->
                if (this.errorAction != null) {
                    this.errorAction.accept(e)
                }
            }
            .onErrorResumeNext(Flowable.empty())
    }
}
