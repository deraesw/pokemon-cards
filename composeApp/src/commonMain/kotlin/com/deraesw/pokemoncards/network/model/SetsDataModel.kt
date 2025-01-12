package com.deraesw.pokemoncards.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CardSet(
    val id: String,
    val name: String,
    val series: String,
    val printedTotal: Int,
    val total: Int,
    val legalities: Legalities,
    //val ptcgoCode: String, // TODO to handle might be optional
    val releaseDate: String,
    val updatedAt: String,
    val images: Images
)

@Serializable
data class Legalities(
    val unlimited: String
)

@Serializable
data class Images(
    val symbol: String,
    val logo: String
)
