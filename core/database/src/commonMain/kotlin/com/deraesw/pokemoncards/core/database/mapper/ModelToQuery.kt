package com.deraesw.pokemoncards.core.database.mapper

import com.deraesw.pokemoncards.core.core.model.Card
import com.deraesw.pokemoncards.core.core.model.CardSetModel
import com.deraesw.pokemoncards.core.database.Card_data
import com.deraesw.pokemoncards.core.database.Card_set
import com.deraesw.pokemoncards.core.database.Junction_card_card_type

fun List<CardSetModel>.toCardSetEntity(): List<Card_set> {
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

fun Card.toCardEntity(baseId: String): Card_data {
    return Card_data(
        id = this.id,
        link_card_set = baseId,
        name = this.name,
        level = this.level,
        hp = this.hp,
        image_small = this.imageSmall,
        image_large = this.imageLarge,
        evolves_from = this.evolvesFrom,
        number = this.number,
        artist = this.artist,
        flavor_text = this.flavorText,
        rarity = this.rarity,
        super_type = this.superType,
        numberSort = this.number?.toLongOrNull() ?: -1,
    )
}

fun Card.toJunctionCardTypeEntity(): List<Junction_card_card_type> {
    return this.types.map {
        Junction_card_card_type(
            card_id = this.id,
            type_id = it.id
        )
    }
}
