package com.fobidlim.kakaypay.viewmodels

import android.annotation.SuppressLint
import android.view.View
import com.fobidlim.kakaypay.libs.ActivityViewModel
import com.fobidlim.kakaypay.libs.Environment
import com.fobidlim.kakaypay.libs.IntentKey
import com.fobidlim.kakaypay.models.Location
import com.fobidlim.kakaypay.models.Media
import com.fobidlim.kakaypay.ui.activities.MediaDetailsActivity
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@SuppressLint("CheckResult")
class MediaDetailsViewModel @Inject constructor(
    private val environment: Environment
) : ActivityViewModel<MediaDetailsActivity>() {

    private val rootClick = PublishSubject.create<Int>()
    private val locationClick = PublishSubject.create<Location>()

    private val setMedia = BehaviorSubject.create<Media>()
    private val openGoogleMaps: Observable<Location>
    private val setCaptionVisibility = BehaviorSubject.create<Int>()

    init {
        openGoogleMaps = locationClick

        intent()
            .map { it.getParcelableExtra<Media>(IntentKey.MEDIA) }
            .compose(bindToLifecycle())
            .subscribe(setMedia)

        rootClick
            .map {
                when (it) {
                    View.VISIBLE -> View.GONE
                    else -> View.VISIBLE
                }
            }
            .compose(bindToLifecycle())
            .subscribe(setCaptionVisibility)
    }

    fun rootClick(visibility: Int) = rootClick.onNext(visibility)
    fun locationClick(location: Location) = locationClick.onNext(location)

    fun setMedia() = setMedia
    fun openGoogleMaps() = openGoogleMaps
    fun setCaptionVisibility() = setCaptionVisibility

    companion object {
        const val GOOGLE_MAPS_PACKAGE_NAME = "com.google.android.apps.maps"
    }
}