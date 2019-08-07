package com.fobidlim.kakaypay.libs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.trello.rxlifecycle3.android.ActivityEvent
import io.reactivex.ObservableTransformer
import io.reactivex.subjects.PublishSubject

open class ActivityViewModel<ViewType : ActivityLifeCycleType> : ViewModel() {

    private val viewChange = PublishSubject.create<Optional<ViewType>>()
    private val view = viewChange.filter { it.isNotEmpty }.map { it.get() }
    private val intent = PublishSubject.create<Intent>()

    fun intent(intent: Intent) = this.intent.onNext(intent)

    @CallSuper
    fun onCreate(context: Context, savedInstanceState: Bundle?) {
        dropView()
    }

    @CallSuper
    fun onResume(view: Any) {
        onTakeView(view)
    }

    @CallSuper
    fun onPause() {
        dropView()
    }

    @CallSuper
    fun onDestroy() {
        viewChange.onComplete()
    }

    private fun onTakeView(view: Any) =
        viewChange.onNext(
            Optional(
                when (view) {
                    is ActivityLifeCycleType -> view as ViewType
                    else -> null
                }
            )
        )

    private fun dropView() = viewChange.onNext(Optional(null))

    protected fun intent() = intent

    protected fun <T> bindToLifecycle() =
        ObservableTransformer<T, T> { source ->
            source.takeUntil(
                view.switchMap { v ->
                    v.lifecycle()
                        .map { v to it }
                }
                    .filter { isFinished(it.first, it.second) })
        }

    private fun isFinished(view: ViewType, event: ActivityEvent) =
        when (view) {
            is BaseActivity<*> -> event == ActivityEvent.DESTROY && view.isFinishing
            else -> event == ActivityEvent.DESTROY
        }
}