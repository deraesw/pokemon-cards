package com.deraesw.pokemoncards.core.database.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import com.deraesw.pokemoncards.core.core.model.Card
import com.deraesw.pokemoncards.core.core.model.CardAttacks
import com.deraesw.pokemoncards.core.core.model.CardResistance
import com.deraesw.pokemoncards.core.core.model.CardTypeKey
import com.deraesw.pokemoncards.core.core.model.CardWeakness
import com.deraesw.pokemoncards.core.core.model.SortCardData
import com.deraesw.pokemoncards.core.core.util.Logger
import com.deraesw.pokemoncards.core.database.Card_attacks_cost
import com.deraesw.pokemoncards.core.database.Card_resistance
import com.deraesw.pokemoncards.core.database.Card_weakness
import com.deraesw.pokemoncards.core.database.factory.DatabaseFactory
import com.deraesw.pokemoncards.core.database.mapper.toCardDetailFlow
import com.deraesw.pokemoncards.core.database.mapper.toCardDetailListFlow
import com.deraesw.pokemoncards.core.database.mapper.toCardEntity
import com.deraesw.pokemoncards.core.database.mapper.toCardTypeList
import com.deraesw.pokemoncards.core.database.mapper.toJunctionCardTypeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class CardDaoImp(
    private val databaseFactory: DatabaseFactory,
) : CardDao {
    private val queries by lazy {
        databaseFactory.database.cardDataQueries
    }

    override suspend fun selectCardListFlow(
        cardSetId: String,
        sorter: SortCardData
    ): Flow<List<Card>> {
        return queries
            .selectAllCardData(
                cardSetId = cardSetId,
                sorter = sorter.name
            )
            .asFlow()
            .mapToList(Dispatchers.IO)
            .toCardDetailListFlow()
    }

    override suspend fun selectCardFlow(cardId: String): Flow<Card> {
        val cardType = queries.selectCardTypes(cardId).executeAsList()

        val attacks = queries.selectCardAttacks(cardId).executeAsList().map {
            val cost = queries.selectCardAttacksCost(it.id).executeAsList().map { costType ->
                CardTypeKey(costType)
            }
            CardAttacks(
                name = it.name,
                damage = it.damage,
                description = it.description,
                cost = cost
            )
        }

        val weakness = queries.selectCardWeaknesses(cardId).executeAsList().map {
            CardWeakness(
                typeKey = it.link_card_type_id,
                value = it.value_
            )
        }
        val resistance = queries.selectCardResistances(cardId).executeAsList().map {
            CardResistance(
                typeKey = it.link_card_type_id,
                value = it.value_
            )
        }

        return queries
            .selectCardData(cardId)
            .asFlow()
            .mapToOne(Dispatchers.IO)
            .toCardDetailFlow(
                types = cardType.toCardTypeList(),
                attacks = attacks,
                weaknesses = weakness,
                resistances = resistance
            )
    }

    override suspend fun selectCardSetCount(cardSetId: String): Int {
        return queries
            .countCardDataForSet(cardSetId)
            .executeAsOne()
            .toInt()
    }

    override suspend fun bulkInsertCard(
        cardSetId: String,
        cardList: List<Card>
    ) {
        cardList.forEach { card ->
            queries.transaction {
                runCatching {
                    val cardData = card.toCardEntity(cardSetId)
                    val typeJunction = card.toJunctionCardTypeEntity()

                    queries.insertCardData(cardData)
                    typeJunction.forEach { item ->
                        queries.insertCardTypeJunction(item)
                    }

                    Logger.debug("CardDao", "insert card - save card attacks.")
                    saveCardAttacks(card)
                    Logger.debug("CardDao", "insert card - save card weakness.")
                    saveCardWeakness(card)
                    Logger.debug("CardDao", "insert card - save card resistance.")
                    saveCardResistance(card)
                }.onFailure {
                    Logger.error("CardDao", "Error while inserting card data")
                    this.rollback()
                }
            }
        }
    }

    override suspend fun insertCard(card: Card) {
        queries.transaction {
            runCatching {
                Logger.debug("CardDao", "insert card - delete card.")
                queries.deleteCard(card_id = card.id)

                val cardData = card.toCardEntity(card.setId)
                val typeJunction = card.toJunctionCardTypeEntity()

                Logger.debug("CardDao", "insert card - save card.")
                queries.insertCardData(cardData)
                typeJunction.forEach { item ->
                    queries.insertCardTypeJunction(item)
                }

                Logger.debug("CardDao", "insert card - save card attacks.")
                saveCardAttacks(card)
                Logger.debug("CardDao", "insert card - save card weakness.")
                saveCardWeakness(card)
                Logger.debug("CardDao", "insert card - save card resistance.")
                saveCardResistance(card)
            }.onFailure {
                Logger.error("CardDao", "Error while inserting card data")
                this.rollback()
            }
        }
    }

    private fun saveCardAttacks(card: Card) {
        card.attacks.forEach { attack ->
            queries.insertCardAttacks(
                cardId = card.id,
                name = attack.name,
                damage = attack.damage,
                desciption = attack.description
            )

            val lastId = queries.selectLastInsertId().executeAsOne()
            attack.cost.forEach { cost ->
                queries.insertCardAttacksCost(
                    Card_attacks_cost(
                        link_card_attacks_id = lastId,
                        link_card_type_id = cost.key()
                    )
                )
            }
        }

    }

    private fun saveCardWeakness(card: Card) {
        card.weaknesses.forEach {
            queries.insertCardWeakness(
                Card_weakness(
                    link_card_id = card.id,
                    link_card_type_id = it.typeKey,
                    value_ = it.value
                )
            )
        }
    }

    private fun saveCardResistance(card: Card) {
        card.resistances.forEach {
            queries.insertCardResistance(
                Card_resistance(
                    link_card_id = card.id,
                    link_card_type_id = it.typeKey,
                    value_ = it.value
                )
            )
        }
    }

    override suspend fun bulkInsertCardType(types: List<String>) {
        queries.transaction {
            runCatching {
                types.forEach { item ->
                    queries.insertCardType(id = item.uppercase(), name = item)
                }
            }.onFailure {
                Logger.error("CardDao", "Error while inserting card types")
                this.rollback()
            }.onSuccess {
                Logger.info("CardDao", "Card types inserted successfully")
            }
        }
    }
}
