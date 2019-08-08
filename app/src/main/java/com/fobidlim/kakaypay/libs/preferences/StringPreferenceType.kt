package com.fobidlim.kakaypay.libs.preferences

interface StringPreferenceType {

    fun isSet(): Boolean

    fun get(): String?

    fun set(value: String)

    fun delete()
}