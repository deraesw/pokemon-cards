package com.deraesw.pokemoncards.core.network.service

import com.deraesw.pokemoncards.core.network.client.NetworkClient
import com.deraesw.pokemoncards.core.network.mock.MockCard
import com.deraesw.pokemoncards.core.network.mock.MockCardSet
import com.deraesw.pokemoncards.core.network.mock.MockType
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

class PokemonCardApiServiceTest {
    @Test
    fun `getAllSets - return the list of sets`() = runTest {
        val mockClient = getMockClient(json = MockCardSet.json)

        val data = PokemonCardApiServiceImp(mockClient).getAllSets()
        assertTrue { data.size == 1 }
        assertTrue { data.first().id == "base1" }
        assertTrue { data.first().name == "Base" }
        assertTrue { data.first().series == "Base" }
        assertTrue { data.first().printedTotal == 102 }
        assertTrue { data.first().total == 102 }
        assertTrue { data.first().releaseDate == "1999/01/09" }
        assertTrue { data.first().updatedAt == "2020/08/14 09:35:00" }
        assertTrue { data.first().imageSymbol == "https://images.pokemontcg.io/base1/symbol.png" }
        assertTrue { data.first().imageLogo == "https://images.pokemontcg.io/base1/logo.png" }
    }

    @Test
    fun `getAllSets - return empty list when the request fails`() = runTest {
        val mockClient = getMockClient(
            json = MockCardSet.failedJson,
        )

        val data = PokemonCardApiServiceImp(mockClient).getAllSets()
        assertTrue { data.isEmpty() }
    }

    @Test
    fun `getSetCards - return the list of cards`() = runTest {
        val mockClient = getMockClient(json = MockCard.listJson)

        val data = PokemonCardApiServiceImp(mockClient).getSetCards("g1")

        assertTrue { data.size == 1 }
        assertTrue { data.first().id == "g1-1" }
        assertTrue { data.first().name == "Venusaur-EX" }
        assertTrue { data.first().superType == "Pokémon" }
        assertTrue { data.first().setId == "g1" }
        assertTrue { data.first().subTypes.size == 2 }
        assertTrue { data.first().subTypes.contains("Basic") }
        assertTrue { data.first().subTypes.contains("EX") }
        assertTrue { data.first().rules.size == 1 }
        assertTrue {
            data.first().rules.first() == "Pokémon-EX rule: " +
                    "When a Pokémon-EX has been Knocked Out," +
                    " your opponent takes 2 Prize cards."
        }

        assertTrue { data.first().hp == "180" }
        assertTrue { data.first().imageSmall == "https://images.pokemontcg.io/g1/1.png" }
        assertTrue { data.first().imageLarge == "https://images.pokemontcg.io/g1/1_hires.png" }

        assertTrue { data.first().evolvesFrom == null }
        assertTrue { data.first().evolvesTo.size == 1 }
        assertTrue { data.first().evolvesTo.first() == "M Venusaur-EX" }
        assertTrue { data.first().number == "1" }
        assertTrue { data.first().artist == "Eske Yoshinob" }
        assertTrue { data.first().rarity == "Rare Holo EX" }
        assertTrue { data.first().types.size == 1 }
        assertTrue { data.first().types.first().id == "GRASS" }
        assertTrue { data.first().types.first().name == "Grass" }
        assertTrue { data.first().attacks.size == 2 }
        assertTrue { data.first().weaknesses.size == 1 }
        assertTrue { data.first().weaknesses.first().typeKey == "FIRE" }
        assertTrue { data.first().weaknesses.first().value == "×2" }
        assertTrue { data.first().retreatCost.size == 4 }
    }

    @Test
    fun `getSetCards - return empty list when the request fails`() = runTest {
        val mockClient = getMockClient(json = MockCard.failedJson)

        val data = PokemonCardApiServiceImp(mockClient).getSetCards("g1")

        assertTrue { data.isEmpty() }
    }

    @Test
    fun `getCards - return the card`() = runTest {
        val mockClient = getMockClient(json = MockCard.cardJson)

        val data = PokemonCardApiServiceImp(mockClient).getCards("g1")

        requireNotNull(data).apply {
            assertTrue { data.id == "g1-1" }
            assertTrue { data.name == "Venusaur-EX" }
            assertTrue { data.superType == "Pokémon" }
            assertTrue { data.setId == "g1" }
            assertTrue { data.subTypes.size == 2 }
            assertTrue { data.subTypes.contains("Basic") }
            assertTrue { data.subTypes.contains("EX") }
            assertTrue { data.rules.size == 1 }
            assertTrue {
                data.rules.first() == "Pokémon-EX rule: " +
                        "When a Pokémon-EX has been Knocked Out," +
                        " your opponent takes 2 Prize cards."
            }

            assertTrue { data.hp == "180" }
            assertTrue { data.imageSmall == "https://images.pokemontcg.io/g1/1.png" }
            assertTrue { data.imageLarge == "https://images.pokemontcg.io/g1/1_hires.png" }

            assertTrue { data.evolvesFrom == null }
            assertTrue { data.evolvesTo.size == 1 }
            assertTrue { data.evolvesTo.first() == "M Venusaur-EX" }
            assertTrue { data.number == "1" }
            assertTrue { data.artist == "Eske Yoshinob" }
            assertTrue { data.rarity == "Rare Holo EX" }
            assertTrue { data.types.size == 1 }
            assertTrue { data.types.first().id == "GRASS" }
            assertTrue { data.types.first().name == "Grass" }
            assertTrue { data.attacks.size == 2 }
            assertTrue { data.weaknesses.size == 1 }
            assertTrue { data.weaknesses.first().typeKey == "FIRE" }
            assertTrue { data.weaknesses.first().value == "×2" }
            assertTrue { data.retreatCost.size == 4 }
        }
    }

    @Test
    fun `getCards - return null when the request fails`() = runTest {
        val mockClient = getMockClient(json = MockCard.failedJson)

        val data = PokemonCardApiServiceImp(mockClient).getCards("g1")

        assertTrue { data == null }
    }

    @Test
    fun `getCardTypes - return the list of type`() = runTest {
        val mockClient = getMockClient(json = MockType.listJson)

        val data = PokemonCardApiServiceImp(mockClient).getCardTypes()
        assertTrue { data.size == 2 }

        println(data[0])
        assertTrue { data[0] == "Colorless" }
        assertTrue { data[1] == "Darkness" }
    }

    @Test
    fun `getCardTypes - return empty list when the request fails`() = runTest {
        val mockClient = getMockClient(json = MockType.failedJson)

        val data = PokemonCardApiServiceImp(mockClient).getCardTypes()

        assertTrue { data.isEmpty() }
    }

    private fun getMockClient(
        json: String,
        status: HttpStatusCode = HttpStatusCode.OK
    ): NetworkClient {
        val mockEngine = MockEngine {
            respond(
                content = ByteReadChannel(json),
                status = status,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        return NetworkClient(mockEngine)
    }
}
