package com.deraesw.pokemoncards.data.repository

import com.deraesw.pokemoncards.model.CardDetail
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    suspend fun getCardList(cardSetId: String): Flow<List<CardDetail>>
    suspend fun getCardCountForSet(cardSetId: String): Int
    suspend fun saveCardList(cardSetId: String, cardList: List<CardDetail>)
}
