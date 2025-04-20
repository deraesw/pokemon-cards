package com.deraesw.pokemoncards.presentation.screen.card.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deraesw.pokemoncards.core.core.util.Logger
import com.deraesw.pokemoncards.core.data.repository.CardRepository
import com.deraesw.pokemoncards.presentation.model.CardDetail
import com.deraesw.pokemoncards.presentation.model.mapper.toCardDetail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@OptIn(ExperimentalCoroutinesApi::class)
class CardDetailViewModel(
    private val cardRepository: CardRepository,
    providedCardId: String = ""
) : ViewModel() {
    private val cardId = MutableStateFlow(providedCardId)

    val uiState: StateFlow<CardDetail?> = cardId
        .flatMapLatest { id ->
            if (id.isEmpty()) return@flatMapLatest flowOf(null)
            cardRepository
                .getCard(id)
                .map { it.toCardDetail() }
        }
        .catch {
            Logger.error("CardDetailViewModel", "fetchCard error - ${it.message}")
        }
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5000),
            initialValue = null
        )
}
