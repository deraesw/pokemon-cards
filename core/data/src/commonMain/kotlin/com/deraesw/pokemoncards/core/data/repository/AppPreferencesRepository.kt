package com.deraesw.pokemoncards.core.data.repository

interface AppPreferencesRepository {
    suspend fun getLastSyncTime(): String?
    suspend fun saveLastSyncTime()
}

