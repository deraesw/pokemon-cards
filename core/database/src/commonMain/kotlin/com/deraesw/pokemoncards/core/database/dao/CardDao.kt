package com.deraesw.pokemoncards.core.database.dao

import com.deraesw.pokemoncards.core.core.model.Card
import kotlinx.coroutines.flow.Flow

interface CardDao {
    suspend fun selectCardListFlow(cardSetId: String): Flow<List<Card>>
    suspend fun selectCardSetCount(cardSetId: String): Int
    suspend fun bulkInsertCard(cardSetId: String, cardList: List<Card>)
    suspend fun bulkInsertCardType(types: List<String>)
}
