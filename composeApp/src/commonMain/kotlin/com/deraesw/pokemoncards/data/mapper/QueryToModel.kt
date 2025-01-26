package com.deraesw.pokemoncards.data.mapper

import com.deraesw.pokemoncards.data.database.Card_set
import com.deraesw.pokemoncards.model.CardSet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Flow<List<Card_set>>.toCardSetList(): Flow<List<CardSet>> {
    return this.map { list ->
        list.map {
            CardSet(
                id = it.id,
                name = it.name,
                total = it.total.toInt(),
                series = it.series ?: "",
                printedTotal = it.printedTotal.toInt(),
                releaseDate = it.releaseDate,
                updatedAt = it.updatedAt,
                legalities = it.legalities,
                imageSymbol = it.imageSymbol,
                imageLogo = it.imageLogo
            )
        }
    }
}