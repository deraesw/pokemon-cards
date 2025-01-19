package com.deraesw.pokemoncards

import com.deraesw.pokemoncards.data.repository.CardSetRepository
import com.deraesw.pokemoncards.network.service.PokemonCardApiService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object SyncManager : KoinComponent {
    private var isSyncing = true

    private val cardSetRepository: CardSetRepository by inject()
    private val pokemonService: PokemonCardApiService by inject()

    suspend fun syncWhenNotDone() {
//        if (isSyncing) return
//        isSyncing = true
//
//        println("syncWhenNotDone")
//        val data = pokemonService.getAllSets()
//        val modelData = data.map { item ->
//            CardSet(
//                id = item.id,
//                name = item.name,
//                total = item.total
//            )
//        }
//        cardSetRepository.saveCardSetList(modelData)
//        println("syncWhenNotDone done")
    }
}