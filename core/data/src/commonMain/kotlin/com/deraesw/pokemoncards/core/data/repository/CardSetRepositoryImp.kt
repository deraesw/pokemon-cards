package com.deraesw.pokemoncards.core.data.repository

import com.deraesw.pokemoncards.core.core.model.CardSetModel
import com.deraesw.pokemoncards.core.core.model.SortData
import com.deraesw.pokemoncards.core.database.dao.CardSetDao
import kotlinx.coroutines.flow.Flow

class CardSetRepositoryImp(
    private val cardSetDao: CardSetDao
) : CardSetRepository {

    override fun getAllSets(
        sorter: SortData
    ): List<CardSetModel> {
        return cardSetDao.selectAllCardSet(sorter)
    }

    override fun getSet(id: String): CardSetModel? {
        return cardSetDao.selectCardSet(id)
    }

    override suspend fun allCardSets(
        sorter: SortData
    ): Flow<List<CardSetModel>> {
        return cardSetDao.selectAllCardSetFlow(sorter)
    }

    override suspend fun saveCardSetList(cardSetModels: List<CardSetModel>) {
        cardSetDao.bulkInsertCardSet(cardSetModels)
    }
}
