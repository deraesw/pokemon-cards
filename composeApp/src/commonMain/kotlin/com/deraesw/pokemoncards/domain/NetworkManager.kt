package com.deraesw.pokemoncards.domain

interface NetworkManager {
    suspend fun fetchSetCardsList(carSetId: String)
}
