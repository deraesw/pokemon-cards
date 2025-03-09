package com.deraesw.pokemoncards.core.network.service

import com.deraesw.pokemoncards.core.core.model.Card
import com.deraesw.pokemoncards.core.core.model.CardSet

interface PokemonCardApiService {
    suspend fun getAllSets(): List<CardSet>
    suspend fun getSetCards(baseId: String): List<Card>
    suspend fun getCardTypes(): List<String>
    suspend fun getCards(cardId: String): Card?
}
