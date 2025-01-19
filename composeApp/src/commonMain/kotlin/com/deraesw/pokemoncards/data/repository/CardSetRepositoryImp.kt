package com.deraesw.pokemoncards.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.deraesw.pokemoncards.data.database.Card_set
import com.deraesw.pokemoncards.data.database.DatabaseFactory
import com.deraesw.pokemoncards.data.mapper.toCardSetList
import com.deraesw.pokemoncards.model.CardSet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class CardSetRepositoryImp(
    private val databaseFactory: DatabaseFactory,
) : CardSetRepository {
    override fun getAllSets(): List<CardSet> {
        return databaseFactory.database.cardSetQueries.selectAllSet().executeAsList().map {
            CardSet(it.id, it.name ?: "", it.total?.toInt() ?: 0)
        }
    }

    override suspend fun allCardSets(): Flow<List<CardSet>> {
        return databaseFactory
            .database
            .cardSetQueries.selectAllSet()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .toCardSetList()
    }

    override suspend fun saveCardSetList(cardSets: List<CardSet>) {
        val data = cardSets.map {
            Card_set(
                it.id,
                it.name,
                it.total.toLong()
            )
        }.first()
        databaseFactory.database.cardSetQueries.transaction {
            databaseFactory.database.cardSetQueries.insertCard(data)
        }
    }
}