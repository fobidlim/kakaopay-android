package com.fobidlim.kakaypay.services

import com.fobidlim.kakaypay.models.Comment
import com.fobidlim.kakaypay.models.Media
import com.fobidlim.kakaypay.models.User
import io.reactivex.Flowable

interface ApiClientType {

    fun user(accessToken: String): Flowable<User>

    fun recentMedia(): Flowable<MutableList<Media>>

    fun comments(mediaId: String): Flowable<MutableList<Comment>>
}