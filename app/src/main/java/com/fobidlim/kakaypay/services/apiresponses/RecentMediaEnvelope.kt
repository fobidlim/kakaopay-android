package com.fobidlim.kakaypay.services.apiresponses

import com.fobidlim.kakaypay.models.Envelope
import com.fobidlim.kakaypay.models.Media

data class RecentMediaEnvelope(
    val data: MutableList<Media>
) : Envelope()