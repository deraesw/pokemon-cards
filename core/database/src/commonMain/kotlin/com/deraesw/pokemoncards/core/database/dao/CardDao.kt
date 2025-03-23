package com.deraesw.pokemoncards.core.database.dao

import com.deraesw.pokemoncards.core.core.model.Card
import com.deraesw.pokemoncards.core.core.model.SortCardData
import kotlinx.coroutines.flow.Flow

interface CardDao {
    suspend fun selectCardListFlow(cardSetId: String, sorter: SortCardData): Flow<List<Card>>
    suspend fun selectCardFlow(cardId: String): Flow<Card>
    suspend fun selectCardSetCount(cardSetId: String): Int
    suspend fun bulkInsertCard(cardSetId: String, cardList: List<Card>)
    suspend fun insertCard(card: Card)
    suspend fun bulkInsertCardType(types: List<String>)
}
