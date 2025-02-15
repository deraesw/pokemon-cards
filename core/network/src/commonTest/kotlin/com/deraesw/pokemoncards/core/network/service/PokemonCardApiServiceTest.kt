package com.deraesw.pokemoncards.core.network.service

import com.deraesw.pokemoncards.core.network.client.NetworkClient
import com.deraesw.pokemoncards.core.network.mock.MockCard
import com.deraesw.pokemoncards.core.network.mock.MockCardSet
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

class PokemonCardApiServiceTest {
    @Test
    fun `getAllSets - return the list of sets`() {
        runBlocking {
            val mockClient = getMockClient(
                json = MockCardSet.json
            )

            val data = PokemonCardApiServiceImp(mockClient).getAllSets()
            assertTrue { data.size == 1 }
            assertTrue { data.first().id == "base1" }
            assertTrue { data.first().name == "Base" }
            assertTrue { data.first().series == "Base" }
            assertTrue { data.first().printedTotal == 102 }
            assertTrue { data.first().total == 102 }
//            assertTrue { data.first().legalities.unlimited == "Legal" }
////            assertTrue { data.first().ptcgoCode == "BS" }
//            assertTrue { data.first().releaseDate == "1999/01/09" }
//            assertTrue { data.first().updatedAt == "2020/08/14 09:35:00" }
//            assertTrue { data.first().images.symbol == "https://images.pokemontcg.io/base1/symbol.png" }
//            assertTrue { data.first().images.logo == "https://images.pokemontcg.io/base1/logo.png" }
        }
    }

    @Test
    fun `getAllSets - return empty list when the request fails`() {
        runBlocking {
            val mockClient = getMockClient(
                json = MockCardSet.failedJson,
            )

            val data = PokemonCardApiServiceImp(mockClient).getAllSets()
            assertTrue { data.isEmpty() }
        }
    }

    @Test
    fun `getSetCards - return the list of cards`() {
        runBlocking {
            val mockClient = getMockClient(
                json = MockCard.json
            )
            val data = PokemonCardApiServiceImp(mockClient).getSetCards("g1")
            assertTrue { data.size == 1 }
            assertTrue { data.first().id == "g1-1" }
            assertTrue { data.first().name == "Venusaur-EX" }
            assertTrue { data.first().superType == "Pokémon" }
            assertTrue { data.first().hp == "180" }
            assertTrue { data.first().number == "1" }
            assertTrue { data.first().artist == "Eske Yoshinob" }
            assertTrue { data.first().rarity == "Rare Holo EX" }
//            assertTrue { data.first().images.small == "https://images.pokemontcg.io/g1/1.png" }
//            assertTrue { data.first().images.large == "https://images.pokemontcg.io/g1/1_hires.png" }
//
//            assertTrue { data.first().subTypes.size == 2 }
//            assertTrue { data.first().subTypes.contains("Basic") }
//            assertTrue { data.first().subTypes.contains("EX") }
        }
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
