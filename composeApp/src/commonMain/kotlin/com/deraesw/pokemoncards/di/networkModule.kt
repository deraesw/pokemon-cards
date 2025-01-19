package com.deraesw.pokemoncards.di

import com.deraesw.pokemoncards.network.NetworkClient
import com.deraesw.pokemoncards.network.createHttpEngine
import com.deraesw.pokemoncards.network.service.PokemonCardApiService
import com.deraesw.pokemoncards.network.service.PokemonCardApiServiceImp
import io.ktor.client.engine.HttpClientEngine
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {
    single<HttpClientEngine> { createHttpEngine() }
    single { NetworkClient(get()) }
    singleOf(::PokemonCardApiServiceImp) bind PokemonCardApiService::class
}
