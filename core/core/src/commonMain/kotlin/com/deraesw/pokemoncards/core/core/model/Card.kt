package com.deraesw.pokemoncards.core.core.model

import kotlin.jvm.JvmInline

data class Card(
    val id: String,
    val name: String,
    val superType: String?,
    val setId: String,
    val subTypes: List<String> = listOf(),
    val rules: List<String> = listOf(),
    val level: String? = null,
    val hp: String? = null,
    val imageSmall: String? = null,
    val imageLarge: String? = null,
    val evolvesFrom: String? = null,
    val evolvesTo: List<String> = listOf(),
    val convertedRetreatCost: Int? = null,
    val number: String? = null,
    val artist: String? = null,
    val flavorText: String? = null,
    val rarity: String? = null,
    val types: List<CardType> = listOf(),
    val attacks: List<CardAttacks> = listOf(),
    val weaknesses: List<CardWeakness> = listOf(),
    val resistances: List<CardResistance> = listOf(),
    val retreatCost: List<CardTypeKey> = listOf()
)

data class CardType(
    val id: String,
    val name: String,
)

data class CardAttacks(
    val name: String,
    val damage: String,
    val description: String,
    val cost: List<CardTypeKey>
)

data class CardWeakness(
    val typeKey: String,
    val value: String
)

data class CardResistance(
    val typeKey: String,
    val value: String
)

@JvmInline
value class CardTypeKey(
    private val value: String
) {
    fun key(): String = value.uppercase()
}
