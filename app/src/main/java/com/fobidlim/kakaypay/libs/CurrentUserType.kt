package com.fobidlim.kakaypay.libs

import com.fobidlim.kakaypay.models.User
import io.reactivex.Observable

abstract class CurrentUserType {

    abstract fun login(newUser: User, accessToken: String)

    abstract fun logout()

    abstract fun getAccessToken(): String

    abstract fun refresh(freshUser: User)

    abstract fun observable(): Observable<Optional<User>>

    abstract fun getUser(): User?

    fun exists(): Boolean = getUser() != null

    fun isLoggedIn(): Observable<Boolean> = observable().map { it.isNotEmpty }

    fun loggedInUser(): Observable<User?> = observable().filter { it.isNotEmpty }.map { it.get() }

    fun loggedOutUser(): Observable<Optional<User>> = observable().filter { it.isEmpty }
}