package com.deraesw.pokemoncards.domain

import com.deraesw.pokemoncards.core.core.util.Logger
import com.deraesw.pokemoncards.core.network.service.PokemonCardApiService
import com.deraesw.pokemoncards.data.repository.AppPreferencesRepository
import com.deraesw.pokemoncards.data.repository.CardRepository
import com.deraesw.pokemoncards.data.repository.CardSetRepository

class NetworkManagerImp(
    private val pokemonService: PokemonCardApiService,
    private val cardSetRepository: CardSetRepository,
    private val cardRepository: CardRepository,
    private val appPreferencesRepository: AppPreferencesRepository
) : NetworkManager {
    private var syncInProgress = false

    override suspend fun initialSync() {
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

        println("Saving all sets...")
        cardSetRepository.saveCardSetList(data)

        val types = pokemonService.getCardTypes()
        cardRepository.savedCardType(types)

        println("Sync done...")
        appPreferencesRepository.saveLastSyncTime()
        syncInProgress = false
    }

    override suspend fun fetchSetCardsList(
        carSetId: String,
        force: Boolean
    ) {
        if (force.not()) {
            val count = cardRepository.getCardCountForSet(carSetId)
            if (count > 0) {
                Logger.info("NetworkManager", "Set cards already fetched.")
                return
            }
        }
        Logger.info("NetworkManager", "Fetching set cards.")

        val data = pokemonService
            .getSetCards(carSetId)

        if (data.isEmpty()) {
            Logger.info("NetworkManager", "No data found.")
            return
        }

        Logger.info("NetworkManager", "Data found: ${data.size}, saving.")
        cardRepository.saveCardList(carSetId, data)
    }
}
