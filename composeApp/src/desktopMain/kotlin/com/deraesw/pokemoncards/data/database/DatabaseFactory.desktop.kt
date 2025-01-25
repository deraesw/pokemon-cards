package com.deraesw.pokemoncards.data.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

actual fun createDriver(): SqlDriver {
    val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:pokemon_card.db")
    PokemonCardDatabase.Schema.create(driver)
    return driver
}
