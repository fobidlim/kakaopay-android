package com.fobidlim.kakaypay.libs

import android.annotation.SuppressLint
import com.fobidlim.kakaypay.libs.preferences.StringPreferenceType
import com.fobidlim.kakaypay.models.User
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber

@SuppressLint("CheckResult")
class CurrentUser(
    private val gson: Gson,
    private val accessTokenPreference: StringPreferenceType,
    private val userPreference: StringPreferenceType
) : CurrentUserType() {

    private val user = BehaviorSubject.create<Optional<User>>()

    init {
        user
            .skip(1)
            .filter { it.isNotEmpty }
            .map { it.get() }
            .map { gson.toJson(it, User::class.java) }
            .subscribe(userPreference::set)

        user.onNext(Optional(gson.fromJson(userPreference.get(), User::class.java)))
    }

    override fun login(newUser: User, accessToken: String) {
        Timber.d("Login user %s", newUser.username)

        accessTokenPreference.set(accessToken)
        user.onNext(Optional(newUser))
    }

    override fun logout() {
        Timber.d("Logout current user")

        userPreference.delete()
        accessTokenPreference.delete()
        user.onNext(Optional(null))
    }

    override fun getAccessToken(): String = accessTokenPreference.get() ?: ""

    override fun refresh(freshUser: User) = user.onNext(Optional(freshUser))

    override fun observable(): Observable<Optional<User>> = user

    override fun getUser(): User? = user.value?.get()
}