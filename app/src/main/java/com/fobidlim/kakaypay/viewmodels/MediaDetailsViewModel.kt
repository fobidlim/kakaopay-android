package com.fobidlim.kakaypay.viewmodels

import android.annotation.SuppressLint
import com.fobidlim.kakaypay.libs.ActivityViewModel
import com.fobidlim.kakaypay.libs.Environment
import com.fobidlim.kakaypay.libs.IntentKey
import com.fobidlim.kakaypay.models.Media
import com.fobidlim.kakaypay.ui.activities.MediaDetailsActivity
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

@SuppressLint("CheckResult")
class MediaDetailsViewModel @Inject constructor(
    private val environment: Environment
) : ActivityViewModel<MediaDetailsActivity>() {

    private val setMedia = BehaviorSubject.create<Media>()

    init {
        intent()
            .map { it.getParcelableExtra<Media>(IntentKey.MEDIA) }
            .compose(bindToLifecycle())
            .subscribe(setMedia)
    }

    fun setMedia() = setMedia
}