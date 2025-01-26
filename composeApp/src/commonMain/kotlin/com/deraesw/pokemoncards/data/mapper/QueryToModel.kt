package com.deraesw.pokemoncards.data.mapper

import com.deraesw.pokemoncards.data.database.Card_set
import com.deraesw.pokemoncards.model.CardSet
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