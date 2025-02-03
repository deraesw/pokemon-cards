package com.deraesw.pokemoncards.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetWorkSetImages(
    val symbol: String,
    val logo: String
)
