package com.deraesw.pokemoncards.core.data.repository

import com.deraesw.pokemoncards.core.database.dao.AppPreferencesDao

class AppPreferencesRepositoryImp(
    private val appPreferencesDao: AppPreferencesDao,
) : AppPreferencesRepository {

    override suspend fun getLastSyncTime(): String? {
        return appPreferencesDao.selectLastSyncTime()
    }

    override suspend fun saveLastSyncTime() {
        appPreferencesDao.insertLastSyncTime()
    }
}

