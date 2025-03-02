package com.deraesw.pokemoncards.presentation.screen.card.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deraesw.pokemoncards.core.core.util.Logger
import com.deraesw.pokemoncards.data.repository.CardRepository
import com.deraesw.pokemoncards.presentation.model.CardDetail
import com.deraesw.pokemoncards.presentation.model.CardListItem
import com.deraesw.pokemoncards.presentation.model.mapper.toCardDetail
import com.deraesw.pokemoncards.presentation.model.mapper.toCardListItems
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@OptIn(ExperimentalCoroutinesApi::class)
class CardListViewModel(
    private val cardRepository: CardRepository
) : ViewModel() {

    private val cardSetId = MutableStateFlow("")
    val uiState: StateFlow<CardListState> = cardSetId
        .flatMapLatest { id ->
            cardRepository
                .getCardList(id)
                .map {
                    Logger.debug("CardListViewModel", "fetchCardList list found - ${it.size}")
                    CardListState(
                        cardList = it.toCardListItems(),
                        isLoading = false
                    )
                }
        }.stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5000),
            initialValue = CardListState(isLoading = true)
        )

    private val cardId = MutableStateFlow("")
    val cardDetail: StateFlow<CardDetail?> = cardId.flatMapLatest { id ->
        Logger.debug("CardListViewModel", "fetchCard - $id")
        if (id.isEmpty()) return@flatMapLatest flowOf(null)
        cardRepository
            .getCard(id)
            .map {
                it.toCardDetail()
            }
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5000),
        initialValue = null
    )

    fun selectCardSet(cardSetId: String) {
        Logger.debug("CardListViewModel", "fetchCardList - $cardSetId")
        this.cardId.value = ""
        this.cardSetId.value = cardSetId
    }


    fun selectCard(cardId: String) {
        Logger.debug("CardListViewModel", "selectCard - $cardId")
        this.cardId.value = cardId
    }

    fun dismissSelectedCard() {
        Logger.debug("CardListViewModel", "dismissSelectedCard")
        this.cardId.value = ""
    }
}

data class CardListState(
    val cardList: List<CardListItem> = listOf(),
    val isLoading: Boolean = false,
)
