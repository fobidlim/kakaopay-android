package com.fobidlim.kakaypay.libs

import com.fobidlim.kakaypay.services.ApiClientType

data class Environment(
    val currentUser: CurrentUser,
    val apiClient: ApiClientType
)