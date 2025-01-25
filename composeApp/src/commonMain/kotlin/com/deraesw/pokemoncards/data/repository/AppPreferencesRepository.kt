package com.deraesw.pokemoncards.data.repository

interface AppPreferencesRepository {
    suspend fun getLastSyncTime(): String?
    suspend fun saveLastSyncTime()
}