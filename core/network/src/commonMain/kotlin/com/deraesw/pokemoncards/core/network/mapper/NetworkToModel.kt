package com.deraesw.pokemoncards.core.network.mapper

import com.deraesw.pokemoncards.core.core.model.Card
import com.deraesw.pokemoncards.core.core.model.CardAttacks
import com.deraesw.pokemoncards.core.core.model.CardSet
import com.deraesw.pokemoncards.core.core.model.CardType
import com.deraesw.pokemoncards.core.core.model.CardTypeKey
import com.deraesw.pokemoncards.core.network.model.NetworkCardData
import com.deraesw.pokemoncards.core.network.model.NetworkCardSet

internal object NetworkToModel {
    fun List<NetworkCardSet>.toCardSetList(): List<CardSet> {
        return this.map { item -> item.toCardSet() }
    }

    private fun NetworkCardSet.toCardSet(): CardSet {
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

    fun List<NetworkCardData>.toCardList(): List<Card> {
        return this.map { item -> item.toCard() }
    }

    private fun NetworkCardData.toCard(): Card {
        return Card(
            id = this.id,
            name = this.name,
            superType = this.superType,
            level = this.level,
            hp = this.hp,
            imageSmall = this.images.small,
            imageLarge = this.images.large,
            evolvesFrom = this.evolvesFrom,
            convertedRetreatCost = this.convertedRetreatCost,
            number = this.number,
            artist = this.artist,
            flavorText = this.flavorText,
            types = this.types?.map { CardType(id = it.uppercase(), name = it) } ?: listOf(),
            attacks = this.attacks?.map { attack ->
                CardAttacks(
                    name = attack.name,
                    damage = attack.damage ?: "",
                    description = attack.text ?: "",
                    cost = attack.cost.map { CardTypeKey(it) }
                )
            } ?: listOf()
        )
    }
}