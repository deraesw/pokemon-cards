package com.deraesw.pokemoncards.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardDataModel(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("supertype") val superType: String,
    @SerialName("subtypes") val subTypes: List<String> = listOf(),
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
//    @SerialName("images") val images: CardImages,
//    @SerialName("tcgplayer") val tcgplayer: Tcgplayer? = null,
//    @SerialName("cardmarket") val cardmarket: Cardmarket? = null
)

@Serializable
data class Ability(
    @SerialName("name") val name: String,
    @SerialName("text") val text: String,
    @SerialName("type") val type: String
)

@Serializable
data class Attack(
    @SerialName("name") val name: String,
    @SerialName("cost") val cost: List<String>,
    @SerialName("convertedEnergyCost") val convertedEnergyCost: Int,
    @SerialName("damage") val damage: String? = null,
    @SerialName("text") val text: String? = null
)

@Serializable
data class Weakness(
    @SerialName("type") val type: String,
    @SerialName("value") val value: String
)

@Serializable
data class CardLegalities(
    @SerialName("unlimited") val unlimited: String
)

@Serializable
data class CardImages(
    @SerialName("symbol") val symbol: String? = null,
    @SerialName("logo") val logo: String? = null,
    @SerialName("small") val small: String? = null,
    @SerialName("large") val large: String? = null
)

@Serializable
data class Tcgplayer(
    @SerialName("url") val url: String,
    @SerialName("updatedAt") val updatedAt: String,
    @SerialName("prices") val prices: Prices
)

@Serializable
data class Prices(
    @SerialName("holofoil") val holofoil: Holofoil? = null,
    @SerialName("1stEditionHolofoil") val firstEditionHolofoil: FirstEditionHolofoil? = null
)

@Serializable
data class Holofoil(
    @SerialName("low") val low: Double,
    @SerialName("mid") val mid: Double,
    @SerialName("high") val high: Double,
    @SerialName("market") val market: Double? = null,
    @SerialName("directLow") val directLow: Double? = null
)

@Serializable
data class FirstEditionHolofoil(
    @SerialName("low") val low: Double,
    @SerialName("mid") val mid: Double,
    @SerialName("high") val high: Double,
    @SerialName("market") val market: Double? = null,
    @SerialName("directLow") val directLow: Double? = null
)

@Serializable
data class Cardmarket(
    @SerialName("url") val url: String,
    @SerialName("updatedAt") val updatedAt: String,
    @SerialName("prices") val prices: CardmarketPrices
)

@Serializable
data class CardmarketPrices(
    @SerialName("averageSellPrice") val averageSellPrice: Double,
    @SerialName("lowPrice") val lowPrice: Double,
    @SerialName("trendPrice") val trendPrice: Double,
    @SerialName("germanProLow") val germanProLow: Double,
    @SerialName("suggestedPrice") val suggestedPrice: Double,
    @SerialName("reverseHoloSell") val reverseHoloSell: Double,
    @SerialName("reverseHoloLow") val reverseHoloLow: Double,
    @SerialName("reverseHoloTrend") val reverseHoloTrend: Double,
    @SerialName("lowPriceExPlus") val lowPriceExPlus: Double,
    @SerialName("avg1") val avg1: Double,
    @SerialName("avg7") val avg7: Double,
    @SerialName("avg30") val avg30: Double,
    @SerialName("reverseHoloAvg1") val reverseHoloAvg1: Double,
    @SerialName("reverseHoloAvg7") val reverseHoloAvg7: Double,
    @SerialName("reverseHoloAvg30") val reverseHoloAvg30: Double
)
