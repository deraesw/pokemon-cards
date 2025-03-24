package com.deraesw.pokemoncards.core.data.domain

import com.deraesw.pokemoncards.core.core.bus.SyncBus
import com.deraesw.pokemoncards.core.core.bus.SyncEvent
import com.deraesw.pokemoncards.core.core.util.Logger
import com.deraesw.pokemoncards.core.data.repository.AppPreferencesRepository
import com.deraesw.pokemoncards.core.data.repository.CardRepository
import com.deraesw.pokemoncards.core.data.repository.CardSetRepository
import com.deraesw.pokemoncards.core.network.service.PokemonCardApiService

class NetworkManagerImp(
    private val pokemonService: PokemonCardApiService,
    private val cardSetRepository: CardSetRepository,
    private val cardRepository: CardRepository,
    private val appPreferencesRepository: AppPreferencesRepository,
    private val syncBus: SyncBus
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
        syncBus.sendEvent(SyncEvent.StartCardSync)

        val data = pokemonService
            .getSetCards(carSetId)

        if (data.isEmpty()) {
            Logger.info("NetworkManager", "No data found.")
            return
        }

        Logger.info("NetworkManager", "Data found: ${data.size}, saving.")
        cardRepository.saveCardList(carSetId, data)
    }

    override suspend fun fetchCard(carId: String) {
        Logger.info("NetworkManager", "Fetching card.")

        val data = pokemonService.getCards(carId)

        if (data == null) {
            Logger.info("NetworkManager", "No data found.")
            return
        }

        Logger.info("NetworkManager", "Data found saving.")
        cardRepository.saveCard(data)
    }
}
