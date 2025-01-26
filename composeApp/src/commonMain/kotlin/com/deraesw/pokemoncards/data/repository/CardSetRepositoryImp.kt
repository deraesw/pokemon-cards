package com.deraesw.pokemoncards.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.deraesw.pokemoncards.data.database.DatabaseFactory
import com.deraesw.pokemoncards.data.mapper.toCardSetEntity
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
            CardSet(
                id = it.id,
                name = it.name,
                series = it.series ?: "",
                printedTotal = it.printedTotal?.toInt() ?: 0,
                total = it.total?.toInt() ?: 0,
                releaseDate = it.releaseDate ?: "",
                updatedAt = it.updatedAt ?: "",
                legalities = it.legalities ?: "",
                imageSymbol = it.imageSymbol ?: "",
                imageLogo = it.imageLogo ?: ""
            )
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
        val data = cardSets.toCardSetEntity()
        databaseFactory.database.cardSetQueries.transaction {
            data.forEach { item ->
                databaseFactory.database.cardSetQueries.insertCard(item)
            }
        }
    }
}