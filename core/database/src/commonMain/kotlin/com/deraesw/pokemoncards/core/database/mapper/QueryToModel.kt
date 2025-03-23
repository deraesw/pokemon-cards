package com.deraesw.pokemoncards.core.database.mapper

import com.deraesw.pokemoncards.core.core.model.Card
import com.deraesw.pokemoncards.core.core.model.CardAttacks
import com.deraesw.pokemoncards.core.core.model.CardResistance
import com.deraesw.pokemoncards.core.core.model.CardSet
import com.deraesw.pokemoncards.core.core.model.CardType
import com.deraesw.pokemoncards.core.core.model.CardTypeKey
import com.deraesw.pokemoncards.core.core.model.CardWeakness
import com.deraesw.pokemoncards.core.database.Card_data
import com.deraesw.pokemoncards.core.database.Card_set
import com.deraesw.pokemoncards.core.database.Card_type
import com.deraesw.pokemoncards.core.database.SelectCardData
import com.deraesw.pokemoncards.core.database.SelectCardResistances
import com.deraesw.pokemoncards.core.database.SelectCardWeaknesses
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

fun Flow<SelectCardData>.toCardDetailFlow(
    types: List<CardType> = emptyList(),
    attacks: List<CardAttacks> = emptyList(),
    weaknesses: List<CardWeakness> = emptyList(),
    resistances: List<CardResistance> = emptyList(),
    retreatCost: List<CardTypeKey> = emptyList()
): Flow<Card> {
    return this.map { item ->
        item.toCardDetail(
            types = types,
            attacks = attacks,
            weaknesses = weaknesses,
            resistances = resistances,
            retreatCost = retreatCost
        )
    }
}

fun Card_data.toCardDetail(
    types: List<CardType> = emptyList(),
    attacks: List<CardAttacks> = emptyList(),
    weaknesses: List<CardWeakness> = emptyList(),
    resistances: List<CardResistance> = emptyList(),
    retreatCost: List<CardTypeKey> = emptyList()
): Card {
    return Card(
        id = this.id,
        name = this.name,
        level = this.level,
        hp = this.hp,
        imageSmall = this.image_small,
        imageLarge = this.image_large,
        evolvesFrom = this.evolves_from,
        number = this.number,
        artist = this.artist,
        flavorText = this.flavor_text,
        rarity = this.rarity,
        superType = null,
        types = types,
        attacks = attacks,
        setId = this.link_card_set,
        weaknesses = weaknesses,
        resistances = resistances,
        retreatCost = retreatCost
    )
}

fun SelectCardData.toCardDetail(
    types: List<CardType> = emptyList(),
    attacks: List<CardAttacks> = emptyList(),
    weaknesses: List<CardWeakness> = emptyList(),
    resistances: List<CardResistance> = emptyList(),
    retreatCost: List<CardTypeKey> = emptyList()
): Card {
    return Card(
        id = this.id,
        name = this.name,
        level = this.level,
        hp = this.hp,
        imageSmall = this.image_small,
        imageLarge = this.image_large,
        evolvesFrom = this.evolves_from,
        number = this.number,
        artist = this.artist,
        flavorText = this.flavor_text,
        rarity = this.rarity,
        superType = this.super_type,
        subTypes = this.sub_type?.split("|") ?: listOf(),
        rules = this.rules?.split("|") ?: listOf(),
        types = types,
        attacks = attacks,
        setId = this.link_card_set,
        weaknesses = weaknesses,
        resistances = resistances,
        retreatCost = retreatCost
    )
}

fun List<Card_type>.toCardTypeList(): List<CardType> {
    return this.map { item ->
        CardType(
            id = item.id,
            name = item.name
        )
    }
}

fun List<SelectCardResistances>.toCardResistanceList(): List<CardResistance> {
    return this.map { item ->
        CardResistance(
            typeKey = item.link_card_type_id,
            value = item.value_
        )
    }
}

fun List<SelectCardWeaknesses>.toCardWeaknessList(): List<CardWeakness> {
    return this.map { item ->
        CardWeakness(
            typeKey = item.link_card_type_id,
            value = item.value_
        )
    }
}
