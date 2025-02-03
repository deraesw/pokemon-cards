package com.deraesw.pokemoncards.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCardData(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("supertype") val superType: String,
//    @SerialName("subtypes") val subTypes: List<String> = listOf(),
    @SerialName("level") val level: String? = null,
    @SerialName("hp") val hp: String? = null,
//    @SerialName("types") val types: List<String>? = null,
//    @SerialName("evolvesFrom") val evolvesFrom: String? = null,
//    @SerialName("abilities") val abilities: List<Ability>? = null,
//    @SerialName("attacks") val attacks: List<Attack>? = null,
//    @SerialName("weaknesses") val weaknesses: List<Weakness>? = null,
//    @SerialName("retreatCost") val retreatCost: List<String>? = null,
//    @SerialName("convertedRetreatCost") val convertedRetreatCost: Int? = null,
//    @SerialName("set") val set: CardSet,
//    @SerialName("number") val number: String,
//    @SerialName("artist") val artist: String? = null,
//    @SerialName("rarity") val rarity: String? = null,
//    @SerialName("flavorText") val flavorText: String? = null,
//    @SerialName("nationalPokedexNumbers") val nationalPokedexNumbers: List<Int>? = null,
//    @SerialName("legalities") val legalities: CardLegalities,
    @SerialName("images") val images: NetworkCardImages,
//    @SerialName("tcgplayer") val tcgplayer: Tcgplayer? = null,
//    @SerialName("cardmarket") val cardmarket: Cardmarket? = null
)
