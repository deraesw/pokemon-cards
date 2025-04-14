package com.deraesw.pokemoncards.core.database.dao

import com.deraesw.pokemoncards.core.core.model.CardSetModel
import com.deraesw.pokemoncards.core.core.model.SortData
import kotlinx.coroutines.flow.Flow

interface CardSetDao {
    fun selectAllCardSet(sorter: SortData = SortData.NAME): List<CardSetModel>
    fun selectCardSet(id: String): CardSetModel?

    suspend fun selectAllCardSetFlow(sorter: SortData = SortData.NAME): Flow<List<CardSetModel>>
    suspend fun bulkInsertCardSet(cardSetModels: List<CardSetModel>)
}
