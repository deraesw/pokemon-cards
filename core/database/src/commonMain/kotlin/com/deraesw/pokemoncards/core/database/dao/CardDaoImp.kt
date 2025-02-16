package com.deraesw.pokemoncards.core.database.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.deraesw.pokemoncards.core.core.model.Card
import com.deraesw.pokemoncards.core.database.DatabaseFactory
import com.deraesw.pokemoncards.core.database.mapper.toCardDetailListFlow
import com.deraesw.pokemoncards.core.database.mapper.toCardEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class CardDaoImp(
    private val databaseFactory: DatabaseFactory,
) : CardDao {
    private val queries by lazy {
        databaseFactory.database.cardDataQueries
    }

    override suspend fun selectCardListFlow(cardSetId: String): Flow<List<Card>> {
        return queries
            .selectAllCardData(cardSetId)
            .asFlow()
            .mapToList(Dispatchers.IO)
            .toCardDetailListFlow()
    }

    override suspend fun selectCardSetCount(cardSetId: String): Int {
        return queries
            .countCardDataForSet(cardSetId)
            .executeAsOne()
            .toInt()
    }

    override suspend fun bulkInsertCard(cardSetId: String, cardList: List<Card>) {
        val data = cardList.toCardEntity(cardSetId)
        queries.transaction {
            data.forEach { item ->
                queries.insertCardData(item)
            }
        }
    }

    override suspend fun bulkInsertCardType(types: List<String>) {
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
