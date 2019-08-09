package com.fobidlim.kakaypay.viewmodels

import android.annotation.SuppressLint
import android.view.View
import com.fobidlim.kakaypay.libs.ActivityViewModel
import com.fobidlim.kakaypay.libs.Environment
import com.fobidlim.kakaypay.libs.IntentKey
import com.fobidlim.kakaypay.models.Media
import com.fobidlim.kakaypay.ui.activities.MediaDetailsActivity
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@SuppressLint("CheckResult")
class MediaDetailsViewModel @Inject constructor(
    private val environment: Environment
) : ActivityViewModel<MediaDetailsActivity>() {

    private val rootClick = PublishSubject.create<Any>()

    private val setMedia = BehaviorSubject.create<Media>()
    private val setCaptionVisibility = BehaviorSubject.create<Int>()

    init {
        intent()
            .map { it.getParcelableExtra<Media>(IntentKey.MEDIA) }
            .compose(bindToLifecycle())
            .subscribe(setMedia)

        rootClick
            .map { View.GONE }
            .compose(bindToLifecycle())
            .subscribe(setCaptionVisibility)
    }

    fun rootClick() = rootClick.onNext(0)

    fun setMedia() = setMedia
    fun setCaptionVisibility() = setCaptionVisibility
}