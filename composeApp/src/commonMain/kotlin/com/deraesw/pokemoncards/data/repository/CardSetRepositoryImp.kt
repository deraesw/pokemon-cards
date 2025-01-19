package com.deraesw.pokemoncards.data.repository

import com.deraesw.pokemoncards.data.database.DatabaseFactory
import com.deraesw.pokemoncards.model.CardSet

class CardSetRepositoryImp(
    private val databaseFactory: DatabaseFactory,
) : CardSetRepository {
    override fun getAllSets(): List<CardSet> {
        return databaseFactory.database.cardSetQueries.selectAllSet().executeAsList().map {
            CardSet(it.id, it.name ?: "", it.total?.toInt() ?: 0)
        }
    }
}