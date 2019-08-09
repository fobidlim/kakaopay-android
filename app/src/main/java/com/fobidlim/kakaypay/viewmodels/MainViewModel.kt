package com.fobidlim.kakaypay.viewmodels

import com.fobidlim.kakaypay.libs.ActivityViewModel
import com.fobidlim.kakaypay.libs.Environment
import com.fobidlim.kakaypay.models.Media
import com.fobidlim.kakaypay.ui.activities.MainActivity
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val environment: Environment
) : ActivityViewModel<MainActivity>() {

    private val updateData = BehaviorSubject.create<MutableList<Media>>()

    init {
        recentMedia()
            .compose(bindToLifecycle())
            .subscribe(updateData)
    }

    private fun recentMedia() =
        environment.apiClient.recentMedia()
            .toObservable()

    fun updateData() = updateData
}