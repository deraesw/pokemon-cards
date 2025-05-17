package com.deraesw.pokemoncards.core.database.mapper

import com.deraesw.pokemoncards.core.core.model.Card
import com.deraesw.pokemoncards.core.core.model.CardSetModel
import com.deraesw.pokemoncards.core.core.model.CardType
import kotlin.test.Test
import kotlin.test.assertTrue

class ModelToQueryTest {

    @Test
    fun `toCardEntity should return Card_data with correct values`() {
        val data = Card(
            id = "1",
            name = "Card",
            level = "1",
            hp = "1",
            imageSmall = "imageSmall",
            imageLarge = "imageLarge",
            evolvesFrom = "evolvesFrom",
            number = "10",
            artist = "artist",
            flavorText = "flavorText",
            rarity = "rarity",
            superType = "superType",
            types = listOf(),
            setId = "setId"
        )

        val entity = data.toCardEntity("baseId")

        assertTrue { entity.id == data.id }
        assertTrue { entity.link_card_set == "baseId" }
        assertTrue { entity.name == data.name }
        assertTrue { entity.level == data.level }
        assertTrue { entity.hp == data.hp }
        assertTrue { entity.image_small == data.imageSmall }
        assertTrue { entity.image_large == data.imageLarge }
        assertTrue { entity.evolves_from == data.evolvesFrom }
        assertTrue { entity.number == data.number }
        assertTrue { entity.artist == data.artist }
        assertTrue { entity.flavor_text == data.flavorText }
        assertTrue { entity.rarity == data.rarity }
        assertTrue { entity.super_type == data.superType }
        assertTrue { entity.numberSort == 10L }
    }

    @Test
    fun `toCardSetEntity should return Card_set with correct values`() {
        val data = CardSetModel(
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

        val entities = listOf(data).toCardSetEntity()

        assertTrue { entities.size == 1 }
        assertTrue { entities[0].id == data.id }
        assertTrue { entities[0].name == data.name }
        assertTrue { entities[0].total == 1L }
        assertTrue { entities[0].series == data.series }
        assertTrue { entities[0].printedTotal == 1L }
        assertTrue { entities[0].releaseDate == data.releaseDate }
        assertTrue { entities[0].updatedAt == data.updatedAt }
        assertTrue { entities[0].legalities == data.legalities }
        assertTrue { entities[0].imageSymbol == data.imageSymbol }
        assertTrue { entities[0].imageLogo == data.imageLogo }
    }

    @Test
    fun `toJunctionCardTypeEntity should return Junction_card_card_type with correct values`() {
        val data = Card(
            id = "1",
            name = "Card",
            superType = "superType",
            types = listOf(
                CardType(
                    id = "1",
                    name = "Fire"
                ),
                CardType(
                    id = "2",
                    name = "Grass"
                )
            ),
            setId = "setId"
        )

        val entities = data.toJunctionCardTypeEntity()

        assertTrue { entities.size == 2 }
        assertTrue { entities[0].card_id == data.id }
        assertTrue { entities[0].type_id == "1" }
        assertTrue { entities[1].card_id == data.id }
        assertTrue { entities[1].type_id == "2" }
    }
}
