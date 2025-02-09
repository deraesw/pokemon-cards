package com.deraesw.pokemoncards.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.deraesw.pokemoncards.data.database.DatabaseFactory
import com.deraesw.pokemoncards.data.mapper.toCardDetailListFlow
import com.deraesw.pokemoncards.data.mapper.toCardEntity
import com.deraesw.pokemoncards.model.CardDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class CardRepositoryImp(
    private val databaseFactory: DatabaseFactory,
) : CardRepository {
    private val queries by lazy {
        databaseFactory.database.cardDataQueries
    }

    override suspend fun getCardList(cardSetId: String): Flow<List<CardDetail>> {
        return queries
            .selectAllCardData(cardSetId)
            .asFlow()
            .mapToList(Dispatchers.IO)
            .toCardDetailListFlow()
    }

    override suspend fun getCardCountForSet(cardSetId: String): Int {
        return queries
            .countCardDataForSet(cardSetId)
            .executeAsOne()
            .toInt()
    }

    override suspend fun saveCardList(cardSetId: String, cardList: List<CardDetail>) {
        val data = cardList.toCardEntity(cardSetId)
        queries.transaction {
            data.forEach { item ->
                queries.insertCardData(item)
            }
        }
    }
}
