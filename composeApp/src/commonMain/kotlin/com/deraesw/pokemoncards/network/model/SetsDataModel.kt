package com.deraesw.pokemoncards.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CardSet(
    val id: String,
    val name: String,
    val series: String,
    val printedTotal: Int,
    val total: Int,
    val legalities: SetLegalities,
    //val ptcgoCode: String, // TODO to handle might be optional
    val releaseDate: String,
    val updatedAt: String,
    val images: SetImages
)

@Serializable
data class SetLegalities(
    val unlimited: String
)

@Serializable
data class SetImages(
    val symbol: String,
    val logo: String
)
