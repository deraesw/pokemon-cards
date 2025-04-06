package com.deraesw.pokemoncards.core.database.factory

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.deraesw.pokemoncards.core.core.util.Logger
import com.deraesw.pokemoncards.core.core.util.SystemUtil
import com.deraesw.pokemoncards.core.database.PokemonCardDatabase
import java.io.File
import java.util.Properties

actual class DriverFactory {
    // TODO handle exceptions for file handling
    actual fun createDriver(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver(
            url = "jdbc:sqlite:${getDatabasePath()}",
            properties = Properties().apply { put("foreign_keys", "true") }
        )
        PokemonCardDatabase.Schema.create(driver)
        return driver
    }

    private fun getDatabasePath(): String {
        val appData = SystemUtil.getDataDirectoryPath()
        Logger.debug("D", "appData: $appData")
        val dbDir = File("$appData/data")

        if (!dbDir.exists()) {
            dbDir.mkdirs()
        }

        return "$dbDir/pokemon_cards.db"
    }
}
