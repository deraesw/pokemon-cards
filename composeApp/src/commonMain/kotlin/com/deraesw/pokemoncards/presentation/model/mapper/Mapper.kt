package com.deraesw.pokemoncards.presentation.model.mapper

import com.deraesw.pokemoncards.core.core.model.Card
import com.deraesw.pokemoncards.presentation.model.CardDetail
import com.deraesw.pokemoncards.presentation.model.CardListItem
import com.deraesw.pokemoncards.presentation.model.SuperType

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
        hp = this.hp ?: "",
        imageLarge = this.imageLarge,
        types = this.types.map { it.name },
        attacks = this.attacks,
        rarity = this.rarity ?: "",
        flavorText = this.flavorText ?: "",
        artist = this.artist ?: "",
        weaknesses = this.weaknesses,
        resistances = this.resistances,
        retreatCost = this.retreatCost,
        subTypes = this.subTypes.joinToString(),
        evolvesFrom = this.evolvesFrom ?: "",
        rules = this.rules,
        superType = when (superType?.uppercase()) {
            "POKEMON" -> SuperType.POKEMON
            "ITEM" -> SuperType.ITEM
            "ENERGY" -> SuperType.ENERGY
            "TRAINER" -> SuperType.TRAINER
            else -> SuperType.POKEMON
        }
    )
}
