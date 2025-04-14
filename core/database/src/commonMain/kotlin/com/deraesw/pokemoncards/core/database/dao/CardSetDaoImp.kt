package com.deraesw.pokemoncards.core.database.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.deraesw.pokemoncards.core.core.model.CardSetModel
import com.deraesw.pokemoncards.core.core.model.SortData
import com.deraesw.pokemoncards.core.database.factory.DatabaseFactory
import com.deraesw.pokemoncards.core.database.mapper.toCardSet
import com.deraesw.pokemoncards.core.database.mapper.toCardSetEntity
import com.deraesw.pokemoncards.core.database.mapper.toCardSetList
import com.deraesw.pokemoncards.core.database.mapper.toCardSetListFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class CardSetDaoImp(
    private val databaseFactory: DatabaseFactory,
) : CardSetDao {
    private val queries by lazy {
        databaseFactory.database.cardSetQueries
    }

    override fun selectAllCardSet(
        sorter: SortData
    ): List<CardSetModel> {
        return queries.selectAllSet(
            sorter = sorter.name
        ).executeAsList().toCardSetList()
    }

    override fun selectCardSet(id: String): CardSetModel? {
        return queries.selectSetById(id).executeAsOneOrNull()?.toCardSet()
    }

    override suspend fun selectAllCardSetFlow(
        sorter: SortData
    ): Flow<List<CardSetModel>> {
        return queries.selectAllSet(
            sorter = sorter.name
        )
            .asFlow()
            .mapToList(Dispatchers.IO)
            .toCardSetListFlow()
    }

    override suspend fun bulkInsertCardSet(cardSetModels: List<CardSetModel>) {
        val data = cardSetModels.toCardSetEntity()
        queries.transaction {
            data.forEach { item ->
                queries.insertCard(item)
            }
        }
    }
}
