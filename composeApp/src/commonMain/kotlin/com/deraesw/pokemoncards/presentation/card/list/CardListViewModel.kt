package com.deraesw.pokemoncards.presentation.card.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deraesw.pokemoncards.core.core.util.Logger
import com.deraesw.pokemoncards.data.repository.CardRepository
import com.deraesw.pokemoncards.presentation.model.CardDetail
import com.deraesw.pokemoncards.presentation.model.CardListItem
import com.deraesw.pokemoncards.presentation.model.mapper.toCardDetail
import com.deraesw.pokemoncards.presentation.model.mapper.toCardListItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CardListViewModel(
    private val cardRepository: CardRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<CardListState>(CardListState.Empty)
    val uiState: StateFlow<CardListState> = _uiState.asStateFlow()

//    private var job: Job? = null

    fun selectCardSet(cardSetId: String) {
        fetchCardList(cardSetId)
    }

    private fun fetchCardList(cardSetId: String) {
        Logger.debug("CardListViewModel", "fetchCardList - $cardSetId")
//        job?.cancel()
//        job =
        viewModelScope.launch {
            cardRepository
                .getCardList(cardSetId)
                .collect { list ->
                    _uiState.value = CardListState.Success(
                        cardList = list.toCardListItems()
                    )
                    Logger.debug("CardListViewModel", "fetchCardList list found - ${list.size}")
                }
        }
    }

    fun selectCard(cardId: String) {
        fetchCard(cardId)
    }

    private fun fetchCard(cardId: String) {
        viewModelScope.launch {
            cardRepository
                .getCard(cardId)
                .collect { card ->
                    _uiState.update { cardListState ->
                        when (cardListState) {
                            is CardListState.Success -> {
                                cardListState.copy(
                                    selectedCard = card.toCardDetail()
                                )
                            }

                            else -> {
                                cardListState
                            }
                        }
                    }
                    Logger.debug("CardListViewModel", "fetchCard - $card")
                }
        }
    }

    fun dismissSelectedCard() {
        _uiState.update { cardListState ->
            when (cardListState) {
                is CardListState.Success -> {
                    cardListState.copy(selectedCard = null)
                }

                else -> {
                    cardListState
                }
            }
        }
    }
}

sealed interface CardListState {
    data class Success(
        val cardList: List<CardListItem>,
        val selectedCard: CardDetail? = null
    ) : CardListState

    object Empty : CardListState
//    object Loading : CardListState
//    object Error : CardListState
}