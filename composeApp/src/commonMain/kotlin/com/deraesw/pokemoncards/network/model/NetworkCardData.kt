package com.deraesw.pokemoncards.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCardData(
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
