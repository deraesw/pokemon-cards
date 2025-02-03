package com.deraesw.pokemoncards.domain

import com.deraesw.pokemoncards.data.mapper.toCardList
import com.deraesw.pokemoncards.data.mapper.toCardSetList
import com.deraesw.pokemoncards.data.repository.AppPreferencesRepository
import com.deraesw.pokemoncards.data.repository.CardRepository
import com.deraesw.pokemoncards.data.repository.CardSetRepository
import com.deraesw.pokemoncards.network.service.PokemonCardApiService

class NetworkManagerImp(
    private val pokemonService: PokemonCardApiService,
    private val cardSetRepository: CardSetRepository,
    private val cardRepository: CardRepository,
    private val appPreferencesRepository: AppPreferencesRepository
) : NetworkManager {
    private var syncInProgress = false

    suspend fun initialSync() {
        println("Trigger logic to initial sync")
        if (syncInProgress) return
        syncInProgress = true

        val lastSync = appPreferencesRepository.getLastSyncTime()
        if (lastSync != null) {
            println("Already perform an initial sync. $lastSync")
            return
        }

        println("Fetching all sets...")
        val data = pokemonService
            .getAllSets()
            .toCardSetList()

        println("Saving all sets...")
        cardSetRepository.saveCardSetList(data)

        println("Sync done...")
        appPreferencesRepository.saveLastSyncTime()
        syncInProgress = false
    }

    override suspend fun fetchSetCardsList(
        carSetId: String
    ) {
        val count = cardRepository.getCardCountForSet(carSetId)
        if (count > 0) {
            println("Set cards already fetched.")
            return
        }
        println("Fetching set cards.")
        val data = pokemonService
            .getSetCards(carSetId)
            .toCardList()

        if (data.isEmpty()) {
            println("No data found!.")
            return
        }

        println("Saving set cards.")
        cardRepository.saveCardList(carSetId, data)
    }
}
