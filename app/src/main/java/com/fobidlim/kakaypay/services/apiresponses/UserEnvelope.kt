package com.fobidlim.kakaypay.services.apiresponses

import com.fobidlim.kakaypay.models.Envelope
import com.fobidlim.kakaypay.models.User

data class UserEnvelope(
    val data: User
) : Envelope()