package com.deraesw.pokemoncards.data.repository

import com.deraesw.pokemoncards.core.core.model.Card
import com.deraesw.pokemoncards.core.database.dao.CardDao
import kotlinx.coroutines.flow.Flow

class CardRepositoryImp(
    private val cardDao: CardDao,
) : CardRepository {

    override suspend fun getCardList(cardSetId: String): Flow<List<Card>> {
        return cardDao.selectCardListFlow(cardSetId)
    }

    override suspend fun getCard(cardId: String): Flow<Card> {
        return cardDao.selectCardFlow(cardId)
    }

    override suspend fun getCardCountForSet(cardSetId: String): Int {
        return cardDao.selectCardSetCount(cardSetId)
    }

    override suspend fun saveCardList(cardSetId: String, cardList: List<Card>) {
        cardDao.bulkInsertCard(cardSetId, cardList)
    }

    override suspend fun savedCardType(types: List<String>) {
        cardDao.bulkInsertCardType(types)
    }
}
