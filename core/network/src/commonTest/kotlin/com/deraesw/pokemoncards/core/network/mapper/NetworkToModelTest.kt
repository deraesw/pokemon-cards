package com.deraesw.pokemoncards.core.network.mapper

import com.deraesw.pokemoncards.core.network.mapper.NetworkToModel.toCardList
import com.deraesw.pokemoncards.core.network.mapper.NetworkToModel.toCardSetList
import com.deraesw.pokemoncards.core.network.model.NetWorkSetImages
import com.deraesw.pokemoncards.core.network.model.NetworkCardAbility
import com.deraesw.pokemoncards.core.network.model.NetworkCardAttack
import com.deraesw.pokemoncards.core.network.model.NetworkCardData
import com.deraesw.pokemoncards.core.network.model.NetworkCardImages
import com.deraesw.pokemoncards.core.network.model.NetworkCardSet
import com.deraesw.pokemoncards.core.network.model.NetworkSetLegalities
import kotlin.test.Test
import kotlin.test.assertTrue

class NetworkToModelTest {

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
    fun `toCardList - return the list of cards`() {
        val data = listOf(
            NetworkCardData(
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
                abilities = listOf(
                    NetworkCardAbility(
                        name = "Sand Stream",
                        text = "Flip a coin. If heads, this attack does 40 damage plus 10 more damage.",
                        type = "Ability"
                    )
                ),
                attacks = listOf(
                    NetworkCardAttack(
                        name = "Scratch",
                        cost = listOf("Colorless"),
                        convertedEnergyCost = 0
                    )
                ),
                weaknesses = null,
                retreatCost = null,
                convertedRetreatCost = null,
                flavorText = null,
                types = null,
                evolvesFrom = null,
            )
        ).toCardList()
        assertTrue { data.size == 1 }
        data.first().apply {
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
            assertTrue { types == null }
            assertTrue { evolvesFrom == null }
        }
    }
}
