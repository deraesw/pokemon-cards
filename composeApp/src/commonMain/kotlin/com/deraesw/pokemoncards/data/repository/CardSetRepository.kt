package com.deraesw.pokemoncards.data.repository

import com.deraesw.pokemoncards.core.core.model.CardSet
import com.deraesw.pokemoncards.model.SortData
import kotlinx.coroutines.flow.Flow

interface CardSetRepository {
    fun getAllSets(sorter: SortData = SortData.NAME): List<CardSet>
    fun getSet(id: String): CardSet?
    suspend fun allCardSets(sorter: SortData = SortData.NAME): Flow<List<CardSet>>
    suspend fun saveCardSetList(cardSets: List<CardSet>)
}