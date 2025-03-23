package com.deraesw.pokemoncards.core.database.dao

import com.deraesw.pokemoncards.core.core.model.CardSet
import com.deraesw.pokemoncards.core.core.model.SortData
import kotlinx.coroutines.flow.Flow

interface CardSetDao {
    fun selectAllCardSet(sorter: SortData = SortData.NAME): List<CardSet>
    fun selectCardSet(id: String): CardSet?

    suspend fun selectAllCardSetFlow(sorter: SortData = SortData.NAME): Flow<List<CardSet>>
    suspend fun bulkInsertCardSet(cardSets: List<CardSet>)
}
