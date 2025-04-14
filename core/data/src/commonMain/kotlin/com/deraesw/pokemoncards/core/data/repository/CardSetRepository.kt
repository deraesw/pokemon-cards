package com.deraesw.pokemoncards.core.data.repository

import com.deraesw.pokemoncards.core.core.model.CardSetModel
import com.deraesw.pokemoncards.core.core.model.SortData
import kotlinx.coroutines.flow.Flow

interface CardSetRepository {
    fun getAllSets(sorter: SortData = SortData.NAME): List<CardSetModel>
    fun getSet(id: String): CardSetModel?
    suspend fun allCardSets(sorter: SortData = SortData.NAME): Flow<List<CardSetModel>>
    suspend fun saveCardSetList(cardSetModels: List<CardSetModel>)
}
