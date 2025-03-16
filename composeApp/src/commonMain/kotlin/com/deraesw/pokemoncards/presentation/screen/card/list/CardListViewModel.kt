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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class CardListViewModel(
    private val cardRepository: CardRepository,
    private val networkManager: NetworkManager
) : ViewModel() {

    private val localState = MutableStateFlow(CardListLocalState())

    private val _loadingState = MutableStateFlow(true)
    val loadingState = _loadingState.asStateFlow()

    val uiState: StateFlow<CardListState> = localState
        .debounce(500)
        .flatMapLatest { state ->
            cardRepository
                .getCardList(
                    cardSetId = state.cardSetId,
                    sorter = state.sortCardData
                )
                .map {
                    Logger.debug("CardListViewModel", "fetchCardList list found - ${it.size}")
                    CardListState(
                        cardList = filterCards(
                            query = state.searchQuery,
                            list = it.toCardListItems()
                        ),
                        sortCardData = state.sortCardData
                    ).also {
                        _loadingState.tryEmit(false)
                    }
                }
        }
        .catch {
            Logger.error("CardListViewModel", "fetchCardList error - ${it.message}")
        }
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5000),
            initialValue = CardListState()
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
        viewModelScope.launch(Dispatchers.IO) {
            networkManager.fetchSetCardsList(cardSetId)
        }
        Logger.debug("CardListViewModel", "fetchCardList - $cardSetId")
        this.cardId.value = ""
        this._loadingState.tryEmit(true)
        this.localState.update {
            it.copy(cardSetId = cardSetId)
        }
    }

    fun updateSearchQuery(query: String) {
        localState.update {
            it.copy(searchQuery = query)
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

    private fun filterCards(
        query: String,
        list: List<CardListItem>
    ): List<CardListItem> {
        return if (query.isEmpty()) {
            list
        } else {
            list.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
    }
}

data class CardListLocalState(
    val cardSetId: String = "",
    val sortCardData: SortCardData = SortCardData.CARD_NUMBER,
    val searchQuery: String = ""
)

data class CardListState(
    val cardList: List<CardListItem> = listOf(),
    val sortCardData: SortCardData = SortCardData.CARD_NUMBER
)
