package com.deraesw.pokemoncards.core.core.model

data class Card(
    val id: String,
    val name: String,
    val superType: String?,
    val level: String? = null,
    val hp: String? = null,
    val imageSmall: String? = null,
    val imageLarge: String? = null,
    val evolvesFrom: String? = null,
    val convertedRetreatCost: Int? = null,
    val number: String? = null,
    val artist: String? = null,
    val flavorText: String? = null,
    val rarity: String? = null,
    val types: List<CardType> = listOf()
)

data class CardType(
    val id: String,
    val name: String,
)
