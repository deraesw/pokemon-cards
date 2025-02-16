package com.deraesw.pokemoncards.core.database

import app.cash.sqldelight.db.SqlDriver

expect fun createDriver(): SqlDriver

class DatabaseFactory(
    private val driver: SqlDriver
) {
    val database: PokemonCardDatabase by lazy { PokemonCardDatabase(driver) }
}