package com.deraesw.pokemoncards.core.database.factory

import com.deraesw.pokemoncards.core.database.PokemonCardDatabase

class DatabaseFactory(
    private val factory: DriverFactory
) {
    val database: PokemonCardDatabase by lazy { PokemonCardDatabase(factory.createDriver()) }
}
