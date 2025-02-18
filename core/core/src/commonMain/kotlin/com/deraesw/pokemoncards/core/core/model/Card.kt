package com.deraesw.pokemoncards.core.core.model

import kotlin.jvm.JvmInline

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
    val types: List<CardType> = listOf(),
    val attacks: List<CardAttacks> = listOf(),
)

data class CardType(
    val id: String,
    val name: String,
)

data class CardAttacks(
    //val id: String,
    val name: String,
    val damage: String,
    val description: String,
    val cost: List<CardTypeKey>
)

@JvmInline
value class CardTypeKey(
    private val value: String
) {
    fun key(): String = value.uppercase()
}
