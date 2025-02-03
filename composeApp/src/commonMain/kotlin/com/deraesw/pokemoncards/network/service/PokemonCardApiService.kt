package com.deraesw.pokemoncards.network.service

import com.deraesw.pokemoncards.network.model.NetworkCardData
import com.deraesw.pokemoncards.network.model.NetworkCardSet

interface PokemonCardApiService {
    suspend fun getAllSets(): List<NetworkCardSet>
    suspend fun getSetCards(baseId: String): List<NetworkCardData>
}
