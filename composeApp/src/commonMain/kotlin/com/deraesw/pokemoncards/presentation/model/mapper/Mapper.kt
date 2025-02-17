package com.deraesw.pokemoncards.presentation.model.mapper

import com.deraesw.pokemoncards.core.core.model.Card
import com.deraesw.pokemoncards.presentation.model.CardDetail
import com.deraesw.pokemoncards.presentation.model.CardListItem

fun List<Card>.toCardListItems(): List<CardListItem> {
    return this.map { it.toCardListItem() }
}

fun Card.toCardListItem(): CardListItem {
    return CardListItem(
        id = this.id,
        name = this.name,
        imageSmall = this.imageSmall
    )
}

fun Card.toCardDetail(): CardDetail {
    return CardDetail(
        id = this.id,
        name = this.name,
        number = this.number ?: "",
        imageLarge = this.imageLarge,
        types = this.types.map { it.name }
    )
}
