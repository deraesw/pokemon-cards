package com.deraesw.pokemoncards.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkCardSet(
    val id: String,
    val name: String,
    val series: String,
    val printedTotal: Int,
    val total: Int,
    val legalities: NetworkSetLegalities,
    //val ptcgoCode: String, // TODO to handle might be optional
    val releaseDate: String,
    val updatedAt: String,
    val images: NetWorkSetImages
)

@Serializable
data class NetworkSetLegalities(
    val unlimited: String
)

@Serializable
data class NetWorkSetImages(
    val symbol: String,
    val logo: String
)
