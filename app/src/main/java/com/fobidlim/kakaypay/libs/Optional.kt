package com.fobidlim.kakaypay.libs

import java.util.*

class Optional<M>(
    private val optional: M?
) {

    val isNotEmpty: Boolean
        get() = this.optional != null

    val isEmpty: Boolean
        get() = this.optional == null

    fun get(): M {
        if (optional == null) {
            throw NoSuchElementException("No value present")
        }
        return optional
    }
}