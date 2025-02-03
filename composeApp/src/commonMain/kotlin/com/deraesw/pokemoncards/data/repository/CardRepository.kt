package com.deraesw.pokemoncards.data.repository

import com.deraesw.pokemoncards.model.CardDetail

interface CardRepository {
    suspend fun getCardCountForSet(cardSetId: String): Int
    suspend fun saveCardList(cardSetId: String, cardList: List<CardDetail>)
}
