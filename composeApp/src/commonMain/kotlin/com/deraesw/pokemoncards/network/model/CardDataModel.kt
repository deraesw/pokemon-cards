package com.deraesw.pokemoncards.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CardLegalities(
    @SerialName("unlimited") val unlimited: String
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
