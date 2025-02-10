package com.deraesw.pokemoncards.presentation.cardlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deraesw.pokemoncards.data.repository.CardRepository
import com.deraesw.pokemoncards.model.CardListItem
import com.deraesw.pokemoncards.util.Logger
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CardListViewModel(
    private val cardRepository: CardRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<CardListState>(CardListState.Empty)
    val uiState: StateFlow<CardListState> = _uiState.asStateFlow()

    private var job: Job? = null

    fun selectCardSet(cardSetId: String) {
        fetchCardList(cardSetId)
    }

    private fun fetchCardList(cardSetId: String) {
        Logger.debug("CardListViewModel", "fetchCardList - $cardSetId - ${job != null}")
        job?.cancel()
        job = viewModelScope.launch {
            cardRepository
                .getCardList(cardSetId)
                .collect { list ->
                    _uiState.value = CardListState.Success(
                        cardList = list.map {
                            CardListItem(
                                id = it.id,
                                name = it.name,
                                imageSmall = it.imageSmall
                            )
                        }
                    )
                    Logger.debug("CardListViewModel", "fetchCardList list found - ${list.size}")
                }
        }
    }
}

sealed interface CardListState {
    data class Success(val cardList: List<CardListItem>) : CardListState
    object Empty : CardListState
//    object Loading : CardListState
//    object Error : CardListState
}