package com.deraesw.pokemoncards.presentation.screen.card.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deraesw.pokemoncards.core.core.model.SortCardData
import com.deraesw.pokemoncards.core.core.util.Logger
import com.deraesw.pokemoncards.data.repository.CardRepository
import com.deraesw.pokemoncards.domain.NetworkManager
import com.deraesw.pokemoncards.presentation.model.CardDetail
import com.deraesw.pokemoncards.presentation.model.CardListItem
import com.deraesw.pokemoncards.presentation.model.mapper.toCardDetail
import com.deraesw.pokemoncards.presentation.model.mapper.toCardListItems
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class CardListViewModel(
    private val cardRepository: CardRepository,
    private val networkManager: NetworkManager
) : ViewModel() {

    private val localState = MutableStateFlow(CardListLocalState())

    val uiState: StateFlow<CardListState> = localState
        .flatMapLatest { state ->
            cardRepository
                .getCardList(
                    cardSetId = state.cardSetId,
                    sorter = state.sortCardData
                )
                .map {
                    Logger.debug("CardListViewModel", "fetchCardList list found - ${it.size}")
                    CardListState(
                        cardList = it.toCardListItems(),
                        isLoading = false,
                        sortCardData = state.sortCardData
                    )
                }
        }
        .catch {
            Logger.error("CardListViewModel", "fetchCardList error - ${it.message}")
        }
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5000),
            initialValue = CardListState(isLoading = true)
        )

    private val cardId = MutableStateFlow("")
    val cardDetail: StateFlow<CardDetail?> = cardId
        .flatMapLatest { id ->
            Logger.debug("CardListViewModel", "fetchCard - $id")
            if (id.isEmpty()) return@flatMapLatest flowOf(null)
            cardRepository
                .getCard(id)
                .map {
                    it.toCardDetail()
                }
        }
        .catch {
            Logger.error("CardListViewModel", "fetchCard error - ${it.message}")
        }
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5000),
            initialValue = null
        )

    fun selectCardSet(cardSetId: String) {
        Logger.debug("CardListViewModel", "fetchCardList - $cardSetId")
        this.cardId.value = ""
        this.localState.update {
            it.copy(cardSetId = cardSetId)
        }
    }


    fun selectCard(cardId: String) {
        Logger.debug("CardListViewModel", "selectCard - $cardId")
        this.cardId.value = cardId
    }

    fun dismissSelectedCard() {
        Logger.debug("CardListViewModel", "dismissSelectedCard")
        this.cardId.value = ""
    }

    fun setSortCardData(sortCardData: SortCardData) {
        localState.update {
            it.copy(sortCardData = sortCardData)
        }
    }

    fun reSyncCardList() {
        viewModelScope.launch {
            networkManager.fetchSetCardsList(
                carSetId = localState.value.cardSetId,
                force = true
            )
        }
    }

    fun reSyncCard(cardId: String) {
        viewModelScope.launch {
            networkManager.fetchCard(carId = cardId)
        }
    }
}

data class CardListLocalState(
    val cardSetId: String = "",
    val sortCardData: SortCardData = SortCardData.CARD_NUMBER
)

data class CardListState(
    val cardList: List<CardListItem> = listOf(),
    val isLoading: Boolean = false,
    val sortCardData: SortCardData = SortCardData.CARD_NUMBER
)
