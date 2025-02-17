package com.deraesw.pokemoncards.core.database.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import com.deraesw.pokemoncards.core.core.model.Card
import com.deraesw.pokemoncards.core.core.util.Logger
import com.deraesw.pokemoncards.core.database.factory.DatabaseFactory
import com.deraesw.pokemoncards.core.database.mapper.toCardDetailFlow
import com.deraesw.pokemoncards.core.database.mapper.toCardDetailListFlow
import com.deraesw.pokemoncards.core.database.mapper.toCardEntity
import com.deraesw.pokemoncards.core.database.mapper.toCardTypeList
import com.deraesw.pokemoncards.core.database.mapper.toJunctionCardTypeEntity
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

    override suspend fun selectCardFlow(cardId: String): Flow<Card> {
        val cardType = queries.selectCardTypes(cardId).executeAsList()
        return queries
            .selectCardData(cardId)
            .asFlow()
            .mapToOne(Dispatchers.IO)
            .toCardDetailFlow(cardType.toCardTypeList())
    }

    override suspend fun selectCardSetCount(cardSetId: String): Int {
        return queries
            .countCardDataForSet(cardSetId)
            .executeAsOne()
            .toInt()
    }

    override suspend fun bulkInsertCard(cardSetId: String, cardList: List<Card>) {
        val data = cardList.toCardEntity(cardSetId)
        val typeJunction = cardList.toJunctionCardTypeEntity()
        queries.transaction {
            data.forEach { item ->
                queries.insertCardData(item)
            }
            typeJunction.forEach { item ->
                queries.insertCardTypeJunction(item)
            }
        }
    }

    override suspend fun bulkInsertCardType(types: List<String>) {
        queries.transaction {
            runCatching {
                types.forEach { item ->
                    queries.insertCardType(id = item.uppercase(), name = item)
                }
            }.onFailure {
                Logger.error("CardDao", "Error while inserting card types")
                this.rollback()
            }.onSuccess {
                Logger.info("CardDao", "Card types inserted successfully")
            }
        }
    }
}
