package com.deraesw.pokemoncards.core.data.domain

interface NetworkManager {
    suspend fun initialSync()
    suspend fun fetchSetCardsList(carSetId: String, force: Boolean = false)
    suspend fun fetchCard(carId: String)
}
