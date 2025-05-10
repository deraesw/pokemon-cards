package com.deraesw.pokemoncards.core.network.mapper

import com.deraesw.pokemoncards.core.network.mapper.NetworkToModel.toCard
import com.deraesw.pokemoncards.core.network.mapper.NetworkToModel.toCardList
import com.deraesw.pokemoncards.core.network.mapper.NetworkToModel.toCardSetList
import com.deraesw.pokemoncards.core.network.model.NetWorkSetImages
import com.deraesw.pokemoncards.core.network.model.NetworkCardAbility
import com.deraesw.pokemoncards.core.network.model.NetworkCardAttack
import com.deraesw.pokemoncards.core.network.model.NetworkCardData
import com.deraesw.pokemoncards.core.network.model.NetworkCardImages
import com.deraesw.pokemoncards.core.network.model.NetworkCardResistance
import com.deraesw.pokemoncards.core.network.model.NetworkCardSet
import com.deraesw.pokemoncards.core.network.model.NetworkCardSetData
import com.deraesw.pokemoncards.core.network.model.NetworkCardWeakness
import com.deraesw.pokemoncards.core.network.model.NetworkSetLegalities
import kotlin.test.Test
import kotlin.test.assertTrue

class NetworkToModelTest {

    private val defaultNetworkCardData = NetworkCardData(
        id = "g1-1",
        name = "Venusaur-EX",
        superType = "Pokémon",
        hp = "180",
        number = "1",
        artist = "Eske Yoshinob",
        rarity = "Rare Holo EX",
        images = NetworkCardImages(
            small = "https://images.pokemontcg.io/g1/1.png",
            large = "https://images.pokemontcg.io/g1/1_hires.png"
        ),
        subTypes = listOf("Basic", "EX"),
        level = "70",
        set = NetworkCardSetData(""),
    )

    private val defaultNetworkCardAbility = NetworkCardAbility(
        name = "Sand Stream",
        text = "Flip a coin. If heads, this attack does 40 damage plus 10 more damage.",
        type = "Ability"
    )

    private val defaultNetworkCardAttack = NetworkCardAttack(
        name = "Scratch",
        cost = listOf("Colorless"),
        convertedEnergyCost = 0,
        damage = "10",
        text = "description"
    )

    @Test
    fun `toCardSetList - return the list of sets`() {
        val data = listOf(
            NetworkCardSet(
                id = "base1",
                name = "Base",
                series = "Base",
                printedTotal = 102,
                total = 102,
                legalities = NetworkSetLegalities(
                    unlimited = "Legal"
                ),
                images = NetWorkSetImages(
                    symbol = "https://images.pokemontcg.io/base1/symbol.png",
                    logo = "https://images.pokemontcg.io/base1/logo.png"
                ),
                releaseDate = "1999/01/09",
                updatedAt = "2020/08/14 09:35:00"
            )
        ).toCardSetList()

        assertTrue { data.size == 1 }
        data.first().apply {
            assertTrue { id == "base1" }
            assertTrue { name == "Base" }
            assertTrue { series == "Base" }
            assertTrue { printedTotal == 102 }
            assertTrue { total == 102 }
            assertTrue { legalities == "Legal" }
            assertTrue { imageSymbol == "https://images.pokemontcg.io/base1/symbol.png" }
            assertTrue { imageLogo == "https://images.pokemontcg.io/base1/logo.png" }
            assertTrue { releaseDate == "1999/01/09" }
            assertTrue { updatedAt == "2020/08/14 09:35:00" }
        }
    }

    @Test
    fun `toCard - return the card with default data populated`() {
        val data = defaultNetworkCardData.toCard()

        data.apply {
            assertTrue { id == "g1-1" }
            assertTrue { name == "Venusaur-EX" }
            assertTrue { superType == "Pokémon" }
            assertTrue { hp == "180" }
            assertTrue { artist == "Eske Yoshinob" }
            assertTrue { rarity == "Rare Holo EX" }
            assertTrue { imageSmall == "https://images.pokemontcg.io/g1/1.png" }
            assertTrue { imageLarge == "https://images.pokemontcg.io/g1/1_hires.png" }
            assertTrue { convertedRetreatCost == null }
            assertTrue { flavorText == null }
            assertTrue { subTypes.size == 2 }

            assertTrue { attacks.isEmpty() }
            assertTrue { evolvesTo.isEmpty() }
            assertTrue { resistances.isEmpty() }
            assertTrue { retreatCost.isEmpty() }
            assertTrue { rules.isEmpty() }
            assertTrue { types.isEmpty() }
            assertTrue { weaknesses.isEmpty() }
        }
    }

    @Test
    fun `toCard - return the card with attacks populated`() {
        val data = defaultNetworkCardData
            .copy(
                attacks = listOf(defaultNetworkCardAttack),
            )
            .toCard()

        data.attacks.first().apply {
            assertTrue { name == "Scratch" }
            assertTrue { damage == "10" }
            assertTrue { description == "description" }
            assertTrue { cost.size == 1 }
            assertTrue { cost.first().key() == "COLORLESS" }
        }
    }

    @Test
    fun `toCard - return the card with types populated`() {
        val data = defaultNetworkCardData
            .copy(
                types = listOf("Grass", "Fire"),
            )
            .toCard()

        assertTrue("Value ${data.types[0].id}") { data.types[0].id == "GRASS" }
        assertTrue("Value ${data.types[0].name}") { data.types[0].name == "Grass" }

        assertTrue("Value ${data.types[1].id}") { data.types[1].id == "FIRE" }
        assertTrue("Value ${data.types[1].name}") { data.types[1].name == "Fire" }
    }

    @Test
    fun `toCard - return the card with retreatCost populated`() {
        val data = defaultNetworkCardData
            .copy(
                retreatCost = listOf("Grass", "Fire"),
            )
            .toCard()

        assertTrue("Value ${data.retreatCost[0].key()}") { data.retreatCost[0].key() == "GRASS" }
        assertTrue("Value ${data.retreatCost[1].key()}") { data.retreatCost[1].key() == "FIRE" }
    }

    @Test
    fun `toCard - return the card with resistances populated`() {
        val data = defaultNetworkCardData
            .copy(
                resistances = listOf(
                    NetworkCardResistance(
                        type = "Grass",
                        value = "20"
                    ),
                    NetworkCardResistance(
                        type = "Fire",
                        value = "10"
                    )
                ),
            )
            .toCard()

        data.resistances[0].apply {
            assertTrue { typeKey == "GRASS" }
            assertTrue { value == "20" }
        }
        data.resistances[1].apply {
            assertTrue { typeKey == "FIRE" }
            assertTrue { value == "10" }
        }
    }

    @Test
    fun `toCard - return the card with weaknesses populated`() {
        val data = defaultNetworkCardData
            .copy(
                weaknesses = listOf(
                    NetworkCardWeakness(
                        type = "Grass",
                        value = "20"
                    ),
                    NetworkCardWeakness(
                        type = "Fire",
                        value = "10"
                    )
                ),
            )
            .toCard()

        data.weaknesses[0].apply {
            assertTrue { typeKey == "GRASS" }
            assertTrue { value == "20" }
        }
        data.weaknesses[1].apply {
            assertTrue { typeKey == "FIRE" }
            assertTrue { value == "10" }
        }
    }

    @Test
    fun `toCardList - return the list of cards`() {
        val data = listOf(defaultNetworkCardData).toCardList()
        assertTrue { data.size == 1 }

        assertTrue { data.first() == defaultNetworkCardData.toCard() }
    }
}
