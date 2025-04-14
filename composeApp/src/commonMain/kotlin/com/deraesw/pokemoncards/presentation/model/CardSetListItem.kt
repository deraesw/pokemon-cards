package com.deraesw.pokemoncards.presentation.model

data class CardSetListItem(
    val id: String,
    val name: String,
    val series: String,
    val total: Int,
    val formatedReleaseDate: String,
    val imageSymbol: String? = null,
)
