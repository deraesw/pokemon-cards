package com.deraesw.pokemoncards.domain

interface NetworkManager {
    suspend fun initialSync()
    suspend fun fetchSetCardsList(carSetId: String, force: Boolean = false)
}
