package com.deraesw.pokemoncards.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.deraesw.pokemoncards.core.core.model.Card
import com.deraesw.pokemoncards.data.database.DatabaseFactory
import com.deraesw.pokemoncards.data.mapper.toCardDetailListFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class CardRepositoryImp(
    private val databaseFactory: DatabaseFactory,
) : CardRepository {
    private val queries by lazy {
        databaseFactory.database.cardDataQueries
    }

    override suspend fun getCardList(cardSetId: String): Flow<List<Card>> {
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

    override suspend fun saveCardList(cardSetId: String, cardList: List<Card>) {
//        //TODO
        //        val data = cardList.toCardEntity(cardSetId)
//        queries.transaction {
//            data.forEach { item ->
//                queries.insertCardData(item)
//            }
//        }
    }

    override suspend fun savedCardType(types: List<String>) {
        queries.transaction {
            runCatching {
                types.forEach { item ->
                    queries.insertCardType(name = item)
                }
            }.onSuccess {
                this.rollback()
            }
        }
    }
}
