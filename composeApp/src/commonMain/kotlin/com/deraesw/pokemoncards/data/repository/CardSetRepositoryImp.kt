package com.deraesw.pokemoncards.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.deraesw.pokemoncards.core.core.model.CardSet
import com.deraesw.pokemoncards.data.database.DatabaseFactory
import com.deraesw.pokemoncards.data.mapper.toCardSet
import com.deraesw.pokemoncards.data.mapper.toCardSetList
import com.deraesw.pokemoncards.data.mapper.toCardSetListFlow
import com.deraesw.pokemoncards.model.SortData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class CardSetRepositoryImp(
    private val databaseFactory: DatabaseFactory,
) : CardSetRepository {

    private val queries by lazy {
        databaseFactory.database.cardSetQueries
    }

    override fun getAllSets(
        sorter: SortData
    ): List<CardSet> {
        return queries.selectAllSet(
            sorter = sorter.name
        ).executeAsList().toCardSetList()
    }

    override fun getSet(id: String): CardSet? {
        return queries.selectSetById(id).executeAsOneOrNull()?.toCardSet()
    }

    override suspend fun allCardSets(
        sorter: SortData
    ): Flow<List<CardSet>> {
        return queries.selectAllSet(
            sorter = sorter.name
        )
            .asFlow()
            .mapToList(Dispatchers.IO)
            .toCardSetListFlow()
    }

    override suspend fun saveCardSetList(cardSets: List<CardSet>) {
//        val data = cardSets.toCardSetEntity()
//        queries.transaction {
//            data.forEach { item ->
//                queries.insertCard(item)
//            }
//        }
    }
}