package com.deraesw.pokemoncards.core.database

import app.cash.sqldelight.db.SqlDriver

actual fun createDriver(): SqlDriver {
    TODO("Not yet implemented")
}

//import android.content.Context
//import app.cash.sqldelight.db.SqlDriver
//import app.cash.sqldelight.driver.android.AndroidSqliteDriver
//import org.koin.core.component.KoinComponent
//import org.koin.core.component.inject
//
//actual fun createDriver(): SqlDriver {
//    return AndroidDataInjector.driver
//}
//
//fun createAndroidSqliteDriver(context: Context): AndroidSqliteDriver {
//    return AndroidSqliteDriver(
//        schema = PokemonCardDatabase.Schema,
//        context = context,
//        name = "pokemon_cards.db"
//    )
//}
//
//object AndroidDataInjector : KoinComponent {
//    val driver: AndroidSqliteDriver by inject()
//}