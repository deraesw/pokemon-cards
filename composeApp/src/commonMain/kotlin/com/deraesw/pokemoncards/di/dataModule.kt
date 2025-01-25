package com.deraesw.pokemoncards.di

import app.cash.sqldelight.db.SqlDriver
import com.deraesw.pokemoncards.data.database.DatabaseFactory
import com.deraesw.pokemoncards.data.database.createDriver
import com.deraesw.pokemoncards.data.repository.AppPreferencesRepository
import com.deraesw.pokemoncards.data.repository.AppPreferencesRepositoryImp
import com.deraesw.pokemoncards.data.repository.CardSetRepository
import com.deraesw.pokemoncards.data.repository.CardSetRepositoryImp
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    single<SqlDriver> { createDriver() }
    single { DatabaseFactory(get()) }
    singleOf(::CardSetRepositoryImp) bind CardSetRepository::class
    singleOf(::AppPreferencesRepositoryImp) bind AppPreferencesRepository::class
}