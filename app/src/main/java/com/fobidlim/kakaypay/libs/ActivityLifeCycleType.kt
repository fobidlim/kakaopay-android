package com.fobidlim.kakaypay.libs

import com.trello.rxlifecycle3.android.ActivityEvent
import io.reactivex.Observable

interface ActivityLifeCycleType {

    fun lifecycle(): Observable<ActivityEvent>
}