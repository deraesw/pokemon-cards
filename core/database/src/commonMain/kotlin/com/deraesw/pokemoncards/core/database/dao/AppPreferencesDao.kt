package com.deraesw.pokemoncards.core.database.dao

interface AppPreferencesDao {
    suspend fun selectLastSyncTime(): String?
    suspend fun insertLastSyncTime()
}
