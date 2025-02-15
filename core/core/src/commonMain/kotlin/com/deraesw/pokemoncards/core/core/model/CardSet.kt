package com.deraesw.pokemoncards.core.core.model

import com.deraesw.pokemoncards.core.core.util.DateUtil

data class CardSet(
    val id: String,
    val name: String,
    val series: String,
    val printedTotal: Int,
    val total: Int,
    val releaseDate: String,
    val updatedAt: String,
    val legalities: String? = null,
    val imageSymbol: String? = null,
    val imageLogo: String? = null
) {
    val formatedReleaseDate = DateUtil.convertDateToDisplayDate(releaseDate)

    fun formatedUpdatedAt(): String {
        return DateUtil.convertToDateTimeDisplay(updatedAt)
    }
}
