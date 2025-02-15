package com.deraesw.pokemoncards.data.repository

import com.deraesw.pokemoncards.core.core.model.Card
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    suspend fun getCardList(cardSetId: String): Flow<List<Card>>
    suspend fun getCardCountForSet(cardSetId: String): Int
    suspend fun saveCardList(cardSetId: String, cardList: List<Card>)
    suspend fun savedCardType(types: List<String>)
}
