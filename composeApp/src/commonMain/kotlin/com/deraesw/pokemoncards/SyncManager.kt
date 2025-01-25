package com.deraesw.pokemoncards

import com.deraesw.pokemoncards.data.repository.AppPreferencesRepository
import com.deraesw.pokemoncards.data.repository.CardSetRepository
import com.deraesw.pokemoncards.model.CardSet
import com.deraesw.pokemoncards.network.service.PokemonCardApiService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object SyncManager : KoinComponent {
    private var syncInProgress = false

    private val cardSetRepository: CardSetRepository by inject()
    private val pokemonService: PokemonCardApiService by inject()
    private val appPreferencesRepository: AppPreferencesRepository by inject()

    suspend fun initialSync() {
        println("Trigger logic to initial sync")
        if (syncInProgress) return
        syncInProgress = true

        val lastSync = appPreferencesRepository.getLastSyncTime()
        if (lastSync != null) {
            println("Already perform an initial sync.")
            return
        }

        println("Fetching all sets...")
        val data = pokemonService.getAllSets()
        val modelData = data.map { item ->
            println("mapping ${item.name}")
            CardSet(
                id = item.id,
                name = item.name,
                total = item.total
            )
        }
        println("Saving all sets...")
        cardSetRepository.saveCardSetList(modelData)

        println("Sync done...")
        appPreferencesRepository.saveLastSyncTime()
        syncInProgress = false
    }
}