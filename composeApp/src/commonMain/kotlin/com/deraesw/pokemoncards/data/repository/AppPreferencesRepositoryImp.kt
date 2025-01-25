package com.deraesw.pokemoncards.data.repository

import com.deraesw.pokemoncards.data.database.DatabaseFactory
import com.deraesw.pokemoncards.util.DateUtil

class AppPreferencesRepositoryImp(
    private val databaseFactory: DatabaseFactory
) : AppPreferencesRepository {

    private val appPreferencesQueries by lazy {
        databaseFactory.database.appPreferencesQueries
    }

    override suspend fun getLastSyncTime(): String? {
        return appPreferencesQueries
            .selectLastSyncTime()
            .executeAsOneOrNull()
            ?.pref_value
    }

    override suspend fun saveLastSyncTime() {
        appPreferencesQueries.transaction {
            appPreferencesQueries.insertLastSyncTime(
                time = DateUtil.currentUtcDateTime
            )
        }
    }
}
