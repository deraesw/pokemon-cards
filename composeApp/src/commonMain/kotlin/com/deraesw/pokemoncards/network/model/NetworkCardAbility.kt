package com.deraesw.pokemoncards.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkCardAbility(
    val name: String,
    val text: String,
    val type: String
)
