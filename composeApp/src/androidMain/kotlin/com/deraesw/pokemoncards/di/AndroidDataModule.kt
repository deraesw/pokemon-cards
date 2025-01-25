package com.deraesw.pokemoncards.di

import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.deraesw.pokemoncards.data.database.createAndroidSqliteDriver
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

// Temp solution to get the context for the driver
val androidDataModule = module {
    single<AndroidSqliteDriver> { createAndroidSqliteDriver(androidContext()) }
}
