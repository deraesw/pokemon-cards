package com.deraesw.pokemoncards.core.network.di

import com.deraesw.pokemoncards.core.network.client.NetworkClient
import com.deraesw.pokemoncards.core.network.service.PokemonCardApiService
import com.deraesw.pokemoncards.core.network.service.PokemonCardApiServiceImp
import io.ktor.client.engine.HttpClientEngine
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class NetworkModuleTest : KoinTest {

    private val httpClientEngine: HttpClientEngine by inject()
    private val networkClient: NetworkClient by inject()
    private val pokemonCardApiService: PokemonCardApiService by inject()

    @BeforeTest
    fun setup() {
        startKoin {
            modules(networkModule)
        }
    }

    @AfterTest
    fun cleanUp() {
        stopKoin()
    }

    @Test
    fun `network module should provide NetworkClient`() {
        val module = get<NetworkClient>()

        assertTrue { module == networkClient }
    }

    @Test
    fun `httpClientEngine should provide HttpClientEngine`() {
        val module = get<HttpClientEngine>()

        assertTrue { module == httpClientEngine }
    }

    @Test
    fun `pokemonCardApiService should provide PokemonCardApiService`() {
        val module = get<PokemonCardApiService>()

        assertTrue { module == pokemonCardApiService }
        assertTrue { module is PokemonCardApiServiceImp }
    }
}
