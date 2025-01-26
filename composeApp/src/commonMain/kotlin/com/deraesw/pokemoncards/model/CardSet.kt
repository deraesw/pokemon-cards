package com.deraesw.pokemoncards.model

data class CardSet(
    val id: String,
    val name: String,
    val series: String,
    val printedTotal: Int,
    val total: Int,
    val releaseDate: String,
    val updatedAt: String,
    val legalities: String? = null,
    val imageSymbol: String? = null,
    val imageLogo: String? = null
)
