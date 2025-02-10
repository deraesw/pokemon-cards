package com.deraesw.pokemoncards.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkCardWeakness(
    val type: String,
    val value: String
)

