package com.deraesw.pokemoncards.core.database.driver

import app.cash.sqldelight.db.SqlDriver

expect fun createTestDriver(): SqlDriver
