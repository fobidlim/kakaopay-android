package com.fobidlim.kakaypay.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fobidlim.kakaypay.R
import com.fobidlim.kakaypay.applicationComponent
import com.fobidlim.kakaypay.libs.IntentKey.MEDIA_ID
import com.fobidlim.kakaypay.services.ApiClientType
import com.fobidlim.kakaypay.ui.adapters.CommentAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_comments.*

class CommentsBottomSheetDialog : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(mediaId: String) =
            CommentsBottomSheetDialog().apply {
                arguments = Bundle().apply {
                    putString(MEDIA_ID, mediaId)
                }
            }
    }

    private val disposables = CompositeDisposable()

    private lateinit var apiClient: ApiClientType
    private val adapter = CommentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apiClient = applicationComponent.environment().apiClient
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_comments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.adapter = adapter

        arguments?.getString(MEDIA_ID)?.let { mediaId ->
            disposables.add(
                comments(mediaId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { adapter.updateData(it) })
        }
    }

    override fun onDestroyView() {
        disposables.clear()
        super.onDestroyView()
    }

    private fun comments(mediaId: String) =
        apiClient.comments(mediaId)
            .toObservable()
}