package com.deraesw.pokemoncards.core.database.factory

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.deraesw.pokemoncards.core.database.PokemonCardDatabase

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:pokemon_card.db")
        PokemonCardDatabase.Schema.create(driver)
        return driver
    }
}