package com.deraesw.pokemoncards.core.database.factory

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.deraesw.pokemoncards.core.database.PokemonCardDatabase

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = PokemonCardDatabase.Schema,
            context = context,
            name = "pokemon_cards.db"
        )
    }
}
