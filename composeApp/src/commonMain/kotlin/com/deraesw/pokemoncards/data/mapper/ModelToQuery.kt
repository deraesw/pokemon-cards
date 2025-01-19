package com.deraesw.pokemoncards.data.mapper

import com.deraesw.pokemoncards.data.database.Card_set
import com.deraesw.pokemoncards.model.CardSet

fun List<CardSet>.toCardSetEntity(): List<Card_set> {
    return this.map {
        Card_set(
            id = it.id,
            name = it.name,
            total = it.total.toLong()
        )
    }
}