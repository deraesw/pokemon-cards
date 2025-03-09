package com.deraesw.pokemoncards.data.repository

import com.deraesw.pokemoncards.core.core.model.Card
import com.deraesw.pokemoncards.core.core.model.SortCardData
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    suspend fun getCardList(
        cardSetId: String,
        sorter: SortCardData = SortCardData.CARD_NUMBER
    ): Flow<List<Card>>

    suspend fun getCard(cardId: String): Flow<Card>
    suspend fun getCardCountForSet(cardSetId: String): Int
    suspend fun saveCardList(cardSetId: String, cardList: List<Card>)
    suspend fun saveCard(card: Card)
    suspend fun savedCardType(types: List<String>)
}
