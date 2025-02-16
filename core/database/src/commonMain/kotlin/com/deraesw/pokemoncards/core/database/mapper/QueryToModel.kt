package com.deraesw.pokemoncards.core.database.mapper

import com.deraesw.pokemoncards.core.core.model.Card
import com.deraesw.pokemoncards.core.core.model.CardSet
import com.deraesw.pokemoncards.core.database.Card_data
import com.deraesw.pokemoncards.core.database.Card_set
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Flow<List<Card_set>>.toCardSetListFlow(): Flow<List<CardSet>> {
    return this.map { list -> list.toCardSetList() }
}

fun List<Card_set>.toCardSetList(): List<CardSet> {
    return this.map { item -> item.toCardSet() }
}

fun Card_set.toCardSet(): CardSet {
    return CardSet(
        id = this.id,
        name = this.name,
        total = this.total.toInt(),
        series = this.series ?: "",
        printedTotal = this.printedTotal.toInt(),
        releaseDate = this.releaseDate,
        updatedAt = this.updatedAt,
        legalities = this.legalities,
        imageSymbol = this.imageSymbol,
        imageLogo = this.imageLogo
    )
}

fun Flow<List<Card_data>>.toCardDetailListFlow(): Flow<List<Card>> {
    return this.map { list -> list.toCardDetailList() }
}

fun List<Card_data>.toCardDetailList(): List<Card> {
    return this.map { item -> item.toCardDetail() }
}

fun Card_data.toCardDetail(): Card {
    return Card(
        id = this.id,
        name = this.name,
        level = this.level,
        hp = this.hp,
        imageSmall = this.image_small,
        imageLarge = this.image_large,
        evolvesFrom = this.evolves_from,
        convertedRetreatCost = this.converted_retreat_cost?.toInt(),
        number = this.number,
        artist = this.artist,
        flavorText = this.flavor_text,
        rarity = this.rarity,
        superType = null
    )
}