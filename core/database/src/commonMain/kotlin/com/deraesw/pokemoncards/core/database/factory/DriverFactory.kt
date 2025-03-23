package com.deraesw.pokemoncards.core.database.factory

import app.cash.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun createDriver(): SqlDriver
}
