package com.deraesw.pokemoncards.data.repository

import com.deraesw.pokemoncards.data.database.DatabaseFactory
import com.deraesw.pokemoncards.data.mapper.toCardEntity
import com.deraesw.pokemoncards.model.CardDetail

class CardRepositoryImp(
    private val databaseFactory: DatabaseFactory,
) : CardRepository {
    val queries by lazy {
        databaseFactory.database.cardDataQueries
    }

    override suspend fun getCardCountForSet(cardSetId: String): Int {
        return queries.countCardDataForSet(cardSetId).executeAsOne().toInt()
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
