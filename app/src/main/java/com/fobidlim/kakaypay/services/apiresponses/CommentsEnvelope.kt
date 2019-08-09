package com.fobidlim.kakaypay.services.apiresponses

import com.fobidlim.kakaypay.models.Comment

data class CommentsEnvelope(
    val data: MutableList<Comment>
)