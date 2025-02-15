package com.deraesw.pokemoncards.core.network.di

import com.deraesw.pokemoncards.core.network.client.NetworkClient
import com.deraesw.pokemoncards.core.network.client.createHttpEngine
import com.deraesw.pokemoncards.core.network.service.PokemonCardApiService
import com.deraesw.pokemoncards.core.network.service.PokemonCardApiServiceImp
import io.ktor.client.engine.HttpClientEngine
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {
    single<HttpClientEngine> { createHttpEngine() }
    single { NetworkClient(get()) }
    singleOf(::PokemonCardApiServiceImp) bind PokemonCardApiService::class
}
