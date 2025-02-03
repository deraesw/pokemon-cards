package com.deraesw.pokemoncards.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkSetLegalities(
    val unlimited: String
)
