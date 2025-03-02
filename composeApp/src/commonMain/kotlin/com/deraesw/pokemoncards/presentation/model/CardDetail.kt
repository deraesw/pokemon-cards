package com.deraesw.pokemoncards.presentation.model

import com.deraesw.pokemoncards.core.core.model.CardAttacks

data class CardDetail(
    val id: String,
    val name: String,
    val number: String = "",
    val hp: String = "",
    val imageLarge: String? = null,
    val artist: String = "",
    val flavorText: String = "",
    val rarity: String = "",
    val types: List<String> = emptyList(),
    val attacks: List<CardAttacks> = emptyList()
)
