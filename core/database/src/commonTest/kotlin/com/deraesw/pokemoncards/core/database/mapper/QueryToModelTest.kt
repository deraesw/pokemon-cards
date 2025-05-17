package com.deraesw.pokemoncards.core.database.mapper

import com.deraesw.pokemoncards.core.database.Card_set
import com.deraesw.pokemoncards.core.database.Card_type
import com.deraesw.pokemoncards.core.database.SelectCardResistances
import com.deraesw.pokemoncards.core.database.SelectCardWeaknesses
import kotlin.test.Test
import kotlin.test.assertTrue

class QueryToModelTest {

    private val cardSetEntity = Card_set(
        id = "1",
        name = "Card",
        total = 1,
        series = "series",
        printedTotal = 1,
        releaseDate = "releaseDate",
        updatedAt = "updatedAt",
        legalities = "legalities",
        imageSymbol = "imageSymbol",
        imageLogo = "imageLogo"
    )

    @Test
    fun `toCardSet should return CardSetModel with correct values`() {
        val model = cardSetEntity.toCardSet()

        assertTrue { model.id == cardSetEntity.id }
        assertTrue { model.name == cardSetEntity.name }
        assertTrue { model.total == cardSetEntity.total.toInt() }
        assertTrue { model.series == cardSetEntity.series }
        assertTrue { model.printedTotal == cardSetEntity.printedTotal.toInt() }
        assertTrue { model.releaseDate == cardSetEntity.releaseDate }
        assertTrue { model.updatedAt == cardSetEntity.updatedAt }
        assertTrue { model.legalities == cardSetEntity.legalities }
        assertTrue { model.imageSymbol == cardSetEntity.imageSymbol }
        assertTrue { model.imageLogo == cardSetEntity.imageLogo }
    }

    @Test
    fun `toCardSetList should return CardSetModel list with correct values`() {
        val models = listOf(cardSetEntity).toCardSetList()

        assertTrue { models.size == 1 }
        assertTrue { models[0] == cardSetEntity.toCardSet() }
    }

    @Test
    fun `toCardTypeList should return CardType list with correct values`() {
        val entity = Card_type(
            id = "1",
            name = "Fire"
        )

        val models = listOf(entity).toCardTypeList()

        assertTrue { models.size == 1 }
        assertTrue { models[0].id == entity.id }
        assertTrue { models[0].name == entity.name }
    }

    @Test
    fun `toCardResistanceList should return CardResistance list with correct values`() {
        val entity = SelectCardResistances(
            link_card_type_id = "1",
            value_ = "1",
            link_card_id = "1",
            name = "Fire"
        )

        val models = listOf(entity).toCardResistanceList()

        assertTrue { models.size == 1 }
        assertTrue { models[0].typeKey == entity.link_card_type_id }
        assertTrue { models[0].value == entity.value_ }
    }

    @Test
    fun `toCardWeaknessList should return CardWeakness list with correct values`() {
        val entity = SelectCardWeaknesses(
            link_card_type_id = "1",
            value_ = "1",
            link_card_id = "1",
            name = "Fire"
        )

        val models = listOf(entity).toCardWeaknessList()

        assertTrue { models.size == 1 }
        assertTrue { models[0].typeKey == entity.link_card_type_id }
        assertTrue { models[0].value == entity.value_ }
    }
}
