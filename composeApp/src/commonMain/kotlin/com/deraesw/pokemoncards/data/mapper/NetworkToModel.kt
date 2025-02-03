package com.deraesw.pokemoncards.data.mapper

import com.deraesw.pokemoncards.model.CardDetail
import com.deraesw.pokemoncards.model.CardSet
import com.deraesw.pokemoncards.network.model.NetworkCardData
import com.deraesw.pokemoncards.network.model.NetworkCardSet

fun List<NetworkCardSet>.toCardSetList(): List<CardSet> {
    return this.map { item -> item.toCardSet() }
}

fun NetworkCardSet.toCardSet(): CardSet {
    return CardSet(
        id = this.id,
        name = this.name,
        total = this.total,
        series = this.series,
        printedTotal = this.printedTotal,
        releaseDate = this.releaseDate,
        updatedAt = this.updatedAt,
        legalities = this.legalities.unlimited,
        imageSymbol = this.images.symbol,
        imageLogo = this.images.logo
    )
}

fun List<NetworkCardData>.toCardList(): List<CardDetail> {
    return this.map { item -> item.toCard() }
}

fun NetworkCardData.toCard(): CardDetail {
    return CardDetail(
        id = this.id,
        name = this.name,
        superType = this.superType,
        level = this.level,
        hp = this.hp,
        imageSymbol = this.images.symbol,
        imageLogo = this.images.logo,
        imageSmall = this.images.small,
        imageLarge = this.images.large
    )
}
