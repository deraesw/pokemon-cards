package com.deraesw.pokemoncards.core.database.factory

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.deraesw.pokemoncards.core.database.PokemonCardDatabase
import java.util.Properties

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver(
            url = "jdbc:sqlite:pokemon_card.db",
            properties = Properties().apply { put("foreign_keys", "true") }
        )
        PokemonCardDatabase.Schema.create(driver)
        return driver
    }
}
