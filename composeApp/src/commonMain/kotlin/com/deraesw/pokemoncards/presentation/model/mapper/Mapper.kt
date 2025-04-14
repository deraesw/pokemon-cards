package com.deraesw.pokemoncards.presentation.model.mapper

import com.deraesw.pokemoncards.core.core.model.Card
import com.deraesw.pokemoncards.core.core.model.CardSetModel
import com.deraesw.pokemoncards.core.core.util.DateUtil
import com.deraesw.pokemoncards.presentation.model.CardDetail
import com.deraesw.pokemoncards.presentation.model.CardListItem
import com.deraesw.pokemoncards.presentation.model.CardSetDetail
import com.deraesw.pokemoncards.presentation.model.CardSetListItem
import com.deraesw.pokemoncards.presentation.model.SuperType

fun List<Card>.toCardListItems(): List<CardListItem> {
    return this.map { it.toCardListItem() }
}

fun Card.toCardListItem(): CardListItem {
    return CardListItem(
        id = this.id,
        name = this.name,
        number = this.number ?: "",
        imageSmall = this.imageSmall,
        imageLarge = this.imageLarge,
        mainType = (this.types.firstOrNull()?.name ?: "").also {
            println("Main Type: ${this.types}")
        },
        superType = superType.toSuperType()
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
        superType = superType.toSuperType()
    )
}

fun CardSetModel.toCardSetDetail(): CardSetDetail {
    return CardSetDetail(
        id = this.id,
        name = this.name,
        series = this.series,
        total = this.total,
        printedTotal = this.printedTotal,
        updatedAt = this.updatedAt,
        formatedUpdatedAt = DateUtil.convertToDateTimeDisplay(updatedAt),
        imageLogo = this.imageLogo,
    )
}

fun List<CardSetModel>.toCardSetList(): List<CardSetListItem> {
    return this.map {
        CardSetListItem(
            id = it.id,
            name = it.name,
            series = it.series,
            total = it.total,
            formatedReleaseDate = DateUtil.convertDateToDisplayDate(it.releaseDate),
            imageSymbol = it.imageSymbol
        )
    }
}

private fun String?.toSuperType() = when (this?.uppercase()) {
    "POKEMON" -> SuperType.POKEMON
    "ITEM" -> SuperType.ITEM
    "ENERGY" -> SuperType.ENERGY
    "TRAINER" -> SuperType.TRAINER
    else -> SuperType.POKEMON
}
