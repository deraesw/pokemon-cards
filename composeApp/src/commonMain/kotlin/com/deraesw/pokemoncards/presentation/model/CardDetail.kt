package com.deraesw.pokemoncards.presentation.model

import com.deraesw.pokemoncards.core.core.model.CardAttacks
import com.deraesw.pokemoncards.core.core.model.CardResistance
import com.deraesw.pokemoncards.core.core.model.CardTypeKey
import com.deraesw.pokemoncards.core.core.model.CardWeakness

data class CardDetail(
    val id: String,
    val name: String,
    val superType: SuperType = SuperType.POKEMON,
    val number: String = "",
    val hp: String = "",
    val imageLarge: String? = null,
    val artist: String = "",
    val flavorText: String = "",
    val rarity: String = "",
    val subTypes: String = "",
    val evolvesFrom: String = "",
    val rules: List<String> = emptyList(),
    val types: List<String> = emptyList(),
    val attacks: List<CardAttacks> = emptyList(),
    val weaknesses: List<CardWeakness> = emptyList(),
    val resistances: List<CardResistance> = emptyList(),
    val retreatCost: List<CardTypeKey> = emptyList(),
) {
    val isPokemon: Boolean = superType == SuperType.POKEMON
    val isItem: Boolean = superType == SuperType.ITEM
    val isEnergy: Boolean = superType == SuperType.ENERGY

    val pokemonRule: String = rules.firstOrNull()?.takeIf { isPokemon } ?: ""
    val mainType: String = types.firstOrNull() ?: ""
}
