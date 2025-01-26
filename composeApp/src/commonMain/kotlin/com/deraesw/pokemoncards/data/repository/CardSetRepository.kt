package com.deraesw.pokemoncards.data.repository

import com.deraesw.pokemoncards.model.CardSet
import kotlinx.coroutines.flow.Flow

interface CardSetRepository {
    fun getAllSets(): List<CardSet>
    fun getSet(id: String): CardSet?
    suspend fun allCardSets(): Flow<List<CardSet>>
    suspend fun saveCardSetList(cardSets: List<CardSet>)
}