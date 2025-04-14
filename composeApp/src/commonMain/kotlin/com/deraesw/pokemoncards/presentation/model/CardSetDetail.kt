package com.deraesw.pokemoncards.presentation.model

data class CardSetDetail(
    val id: String,
    val name: String,
    val series: String,
    val total: Int,
    val printedTotal: Int,
    val updatedAt: String,
    val formatedUpdatedAt: String,
    val imageLogo: String? = null
)
