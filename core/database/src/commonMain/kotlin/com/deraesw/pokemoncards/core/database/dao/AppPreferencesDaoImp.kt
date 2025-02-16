package com.deraesw.pokemoncards.core.database.dao

import com.deraesw.pokemoncards.core.core.util.DateUtil
import com.deraesw.pokemoncards.core.database.DatabaseFactory

class AppPreferencesDaoImp(
    private val databaseFactory: DatabaseFactory
) : AppPreferencesDao {

    private val appPreferencesQueries by lazy {
        databaseFactory.database.appPreferencesQueries
    }

    override suspend fun selectLastSyncTime(): String? {
        return appPreferencesQueries
            .selectLastSyncTime()
            .executeAsOneOrNull()
            ?.pref_value
    }

    override suspend fun insertLastSyncTime() {
        appPreferencesQueries.transaction {
            appPreferencesQueries.insertLastSyncTime(
                time = DateUtil.currentUtcDateTime
            )
        }
    }
}
