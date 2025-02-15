package com.deraesw.pokemoncards.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class NetworkCardData(
    val id: String,
    val name: String,
    @SerialName("supertype") val superType: String,
    val number: String,
    @SerialName("subtypes") val subTypes: List<String> = listOf(),
    val level: String? = null,
    val hp: String? = null,
    val types: List<String>? = null,
    val evolvesFrom: String? = null,
    val abilities: List<NetworkCardAbility>? = null,
    val attacks: List<NetworkCardAttack>? = null,
    val weaknesses: List<NetworkCardWeakness>? = null,
    val retreatCost: List<String>? = null,
    val convertedRetreatCost: Int? = null,
    val artist: String? = null,
    val rarity: String? = null,
    val flavorText: String? = null,
//    @SerialName("nationalPokedexNumbers") val nationalPokedexNumbers: List<Int>? = null,
//    @SerialName("legalities") val legalities: CardLegalities,
    val images: NetworkCardImages,
//    @SerialName("tcgplayer") val tcgplayer: Tcgplayer? = null,
//    @SerialName("cardmarket") val cardmarket: Cardmarket? = null
)

@Serializable
internal data class NetworkCardAbility(
    val name: String,
    val text: String,
    val type: String
)

@Serializable
internal data class NetworkCardAttack(
    val name: String,
    val cost: List<String>,
    val convertedEnergyCost: Int,
    val damage: String? = null,
    val text: String? = null
)

@Serializable
internal data class NetworkCardImages(
    val small: String? = null,
    val large: String? = null
)

@Serializable
internal data class NetworkCardWeakness(
    val type: String,
    val value: String
)
