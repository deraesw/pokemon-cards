package com.deraesw.pokemoncards.data.mapper

import com.deraesw.pokemoncards.data.database.Card_data
import com.deraesw.pokemoncards.data.database.Card_set
import com.deraesw.pokemoncards.model.CardDetail
import com.deraesw.pokemoncards.model.CardSet

fun List<CardSet>.toCardSetEntity(): List<Card_set> {
    return this.map {
        Card_set(
            id = it.id,
            name = it.name,
            total = it.total.toLong(),
            series = it.series,
            printedTotal = it.printedTotal.toLong(),
            releaseDate = it.releaseDate,
            updatedAt = it.updatedAt,
            legalities = it.legalities,
            imageSymbol = it.imageSymbol,
            imageLogo = it.imageLogo
        )
    }
}

fun List<CardDetail>.toCardEntity(baseId: String): List<Card_data> {
    return this.map {
        Card_data(
            id = it.id,
            link_card_set = baseId,
            name = it.name,
            level = it.level,
            hp = it.hp,
            image_small = it.imageSmall,
            image_large = it.imageLarge,
            evolves_from = it.evolvesFrom,
            converted_retreat_cost = it.convertedRetreatCost?.toLong(),
            number = it.number,
            artist = it.artist,
            flavor_text = it.flavorText,
            rarity = it.rarity
        )
    }
}
