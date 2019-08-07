package com.fobidlim.kakaypay.services

import com.fobidlim.kakaypay.models.User

interface ApiClientType {

    fun user(accessToken: String): Flowable<User>
}