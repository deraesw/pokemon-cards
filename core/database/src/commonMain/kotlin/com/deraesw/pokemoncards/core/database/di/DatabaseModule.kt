package com.deraesw.pokemoncards.core.database.di

import com.deraesw.pokemoncards.core.database.dao.AppPreferencesDao
import com.deraesw.pokemoncards.core.database.dao.AppPreferencesDaoImp
import com.deraesw.pokemoncards.core.database.dao.CardDao
import com.deraesw.pokemoncards.core.database.dao.CardDaoImp
import com.deraesw.pokemoncards.core.database.dao.CardSetDao
import com.deraesw.pokemoncards.core.database.dao.CardSetDaoImp
import com.deraesw.pokemoncards.core.database.factory.DatabaseFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val databaseModule = module {
    single { DatabaseFactory(get()) }

    singleOf(::AppPreferencesDaoImp) bind AppPreferencesDao::class
    singleOf(::CardDaoImp) bind CardDao::class
    singleOf(::CardSetDaoImp) bind CardSetDao::class
}
