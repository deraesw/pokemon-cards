package com.deraesw.pokemoncards.network.service

import com.deraesw.pokemoncards.network.model.CardSet

interface PokemonCardApiService {
    suspend fun getAllSets(): List<CardSet>
}