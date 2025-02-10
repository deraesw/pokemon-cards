package com.deraesw.pokemoncards.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkCardImages(
    val small: String? = null,
    val large: String? = null
)
