package com.fobidlim.kakaypay.viewmodels

import com.fobidlim.kakaypay.libs.ActivityViewModel
import com.fobidlim.kakaypay.libs.Environment
import com.fobidlim.kakaypay.models.Media
import com.fobidlim.kakaypay.ui.activities.MainActivity
import com.fobidlim.kakaypay.ui.adapters.MediaAdapter
import com.fobidlim.kakaypay.ui.viewholders.MediaViewHolder
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val environment: Environment
) : ActivityViewModel<MainActivity>(), MediaAdapter.Delegate {

    private val mediaItemClick = PublishSubject.create<Media>()

    private val updateData = BehaviorSubject.create<MutableList<Media>>()
    private val showMediaDetails: Observable<Media>

    init {
        showMediaDetails = mediaItemClick.doOnNext { Timber.d("mediaItemClick? $it") }

        recentMedia()
            .compose(bindToLifecycle())
            .subscribe(updateData)
    }

    private fun recentMedia() =
        environment.apiClient.recentMedia()
            .toObservable()

    override fun mediaViewHolderItemClick(holder: MediaViewHolder, media: Media) = mediaItemClick.onNext(media)

    fun updateData() = updateData
    fun showMediaDetails() = showMediaDetails
}