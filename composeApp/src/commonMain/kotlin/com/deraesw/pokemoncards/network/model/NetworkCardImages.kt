package com.deraesw.pokemoncards.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCardImages(
    @SerialName("symbol") val symbol: String? = null,
    @SerialName("logo") val logo: String? = null,
    @SerialName("small") val small: String? = null,
    @SerialName("large") val large: String? = null
)
