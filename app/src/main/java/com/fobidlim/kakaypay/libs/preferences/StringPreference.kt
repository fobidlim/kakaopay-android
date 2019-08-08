package com.fobidlim.kakaypay.libs.preferences

import android.content.SharedPreferences
import androidx.core.content.edit

class StringPreference(
    private val sharedPreferences: SharedPreferences,
    private val key: String,
    private val defaultValue: String? = null
) : StringPreferenceType {

    override fun isSet(): Boolean = sharedPreferences.contains(key)

    override fun get(): String? = sharedPreferences.getString(key, defaultValue)

    override fun set(value: String) = sharedPreferences.edit { putString(key, value) }

    override fun delete() = sharedPreferences.edit { remove(key) }
}