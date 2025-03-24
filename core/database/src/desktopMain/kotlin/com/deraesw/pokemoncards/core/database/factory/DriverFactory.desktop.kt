package com.deraesw.pokemoncards.core.database.factory

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.deraesw.pokemoncards.core.core.util.Logger
import com.deraesw.pokemoncards.core.core.util.SystemUtil.isMac
import com.deraesw.pokemoncards.core.core.util.SystemUtil.isWindow
import com.deraesw.pokemoncards.core.database.PokemonCardDatabase
import java.io.File
import java.util.Properties

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver(
            url = "jdbc:sqlite:${getDatabasePath()}",
            properties = Properties().apply { put("foreign_keys", "true") }
        )
        PokemonCardDatabase.Schema.create(driver)
        return driver
    }

    private fun getDatabasePath(): String {
        val appData = when {
            isWindow() -> {
                System.getenv("APPDATA") ?: System.getProperty("user.home")
            }

            isMac() -> {
                System.getProperty("user.home") + "/Library/Application Support"
            }

            else -> System.getProperty("user.home")
        }
        Logger.debug("D", "appData: $appData")
        val dbDir = File("$appData/PokemonCardsApp")

        if (!dbDir.exists()) {
            dbDir.mkdirs()
        }

        return "$dbDir/pokemon_cards.db"
    }
}
