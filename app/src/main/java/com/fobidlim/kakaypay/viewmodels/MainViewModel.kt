package com.fobidlim.kakaypay.viewmodels

import android.annotation.SuppressLint
import com.fobidlim.kakaypay.libs.ActivityViewModel
import com.fobidlim.kakaypay.libs.Environment
import com.fobidlim.kakaypay.libs.rx.transformers.Transformers.pipeApiErrorsTo
import com.fobidlim.kakaypay.libs.rx.transformers.Transformers.pipeErrorsTo
import com.fobidlim.kakaypay.models.Media
import com.fobidlim.kakaypay.services.apiresponses.ErrorEnvelope
import com.fobidlim.kakaypay.ui.activities.MainActivity
import com.fobidlim.kakaypay.ui.adapters.MediaAdapter
import com.fobidlim.kakaypay.ui.viewholders.MediaViewHolder
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.CompletableSubject
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

@SuppressLint("CheckResult")
class MainViewModel @Inject constructor(
    private val environment: Environment
) : ActivityViewModel<MainActivity>(), MediaAdapter.Delegate {

    private val apiError = PublishSubject.create<ErrorEnvelope>()
    private val networkError = PublishSubject.create<Throwable>()
    private val mediaItemClick = PublishSubject.create<Media>()
    private val signOutClick = PublishSubject.create<Any>()

    private val updateData = BehaviorSubject.create<MutableList<Media>>()
    private val showErrorToast = BehaviorSubject.create<String>()
    private val showMediaDetails: Observable<Media>
    private val showSignIn = CompletableSubject.create()

    init {
        showMediaDetails = mediaItemClick.doOnNext { Timber.d("mediaItemClick? $it") }

        signOutClick
            .doOnNext { environment.currentUser.logout() }
            .compose(bindToLifecycle())
            .subscribe { showSignIn.onComplete() }

        recentMedia()
            .compose(bindToLifecycle())
            .subscribe(updateData)

        Observable.merge(
            apiError
                .map { it.meta.errorMessage },
            networkError
                .map { it.localizedMessage }
        )
            .compose(bindToLifecycle())
            .subscribe(showErrorToast)
    }

    private fun recentMedia() =
        environment.apiClient.recentMedia()
            .compose(pipeApiErrorsTo(apiError))
            .compose(pipeErrorsTo(networkError))
            .toObservable()

    fun signOutClick() = signOutClick.onNext(0)

    override fun mediaViewHolderItemClick(holder: MediaViewHolder, media: Media) = mediaItemClick.onNext(media)

    fun updateData() = updateData
    fun showErrorToast() = showErrorToast
    fun showMediaDetails() = showMediaDetails
    fun showSignIn() = showSignIn
}