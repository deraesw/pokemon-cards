package com.deraesw.pokemoncards.core.data.di

import com.deraesw.pokemoncards.core.data.domain.NetworkManager
import com.deraesw.pokemoncards.core.data.domain.NetworkManagerImp
import com.deraesw.pokemoncards.core.data.repository.AppPreferencesRepository
import com.deraesw.pokemoncards.core.data.repository.AppPreferencesRepositoryImp
import com.deraesw.pokemoncards.core.data.repository.CardRepository
import com.deraesw.pokemoncards.core.data.repository.CardRepositoryImp
import com.deraesw.pokemoncards.core.data.repository.CardSetRepository
import com.deraesw.pokemoncards.core.data.repository.CardSetRepositoryImp
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    singleOf(::CardSetRepositoryImp) bind CardSetRepository::class
    singleOf(::AppPreferencesRepositoryImp) bind AppPreferencesRepository::class
    singleOf(::CardRepositoryImp) bind CardRepository::class

    singleOf(::NetworkManagerImp) bind NetworkManager::class
}
