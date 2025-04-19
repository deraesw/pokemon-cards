package com.deraesw.pokemoncards.presentation.screen.card.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deraesw.pokemoncards.core.core.bus.SyncBus
import com.deraesw.pokemoncards.core.core.bus.SyncEvent
import com.deraesw.pokemoncards.core.core.model.SortCardData
import com.deraesw.pokemoncards.core.core.util.Logger
import com.deraesw.pokemoncards.core.data.domain.NetworkManager
import com.deraesw.pokemoncards.core.data.repository.CardRepository
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
    private val networkManager: NetworkManager,
    private val syncBus: SyncBus,
    providedSetId: String? = null
) : ViewModel() {

    private val localState = MutableStateFlow(CardListLocalState())

    private val _loadingState = MutableStateFlow(true)
    val loadingState = _loadingState.asStateFlow()

    val syncState = syncBus
        .events
        .map {
            SyncState(
                syncType = when (it) {
                    SyncEvent.None -> SyncState.Type.NONE
                    SyncEvent.StartCardSync -> SyncState.Type.START
                    is SyncEvent.CardSyncProgress -> SyncState.Type.FETCH_CARD
                    SyncEvent.CompleteCardSync -> SyncState.Type.COMPLETE
                },
                message = when (it) {
                    is SyncEvent.CardSyncProgress -> it.message
                    else -> ""
                }
            )
        }
        .catch {
            Logger.error("CardListViewModel", "sync state error - ${it.message}")
        }
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5000),
            initialValue = SyncState()
        )

//    init {
//        Logger.debug("CardListViewModel", "init $syncBus")
//        viewModelScope.launch {
//
//            syncBus.sendEvent(SyncEvent.StartCardSync)
//            delay(1000)
//            for (i in 1..10) {
//                syncBus.sendEvent(SyncEvent.CardSyncProgress("Progress: $i"))
//                delay(1000)
//            }
//            syncBus.sendEvent(SyncEvent.CompleteCardSync)
//        }
//    }

    val uiState: StateFlow<CardListState> = localState
        .debounce(500)
        .flatMapLatest { state ->
            cardRepository
                .getCardList(
                    cardSetId = state.cardSetId,
                    sorter = state.sortCardData
                )
                .map {
                    val cardList = filterCards(
                        query = state.searchQuery,
                        list = it.toCardListItems()
                    )
                    CardListState(
                        cardList = cardList,
                        sortCardData = state.sortCardData,
                        showEmptySearch = cardList.isEmpty() && state.searchQuery.isNotEmpty()
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

    init {
        if (providedSetId != null && localState.value.cardSetId.isEmpty()) {
            selectCardSet(providedSetId)
        }
    }

    fun selectCardSet(cardSetId: String) {
        if (this.localState.value.cardSetId == cardSetId) return

        viewModelScope.launch(Dispatchers.IO) {
            networkManager.fetchSetCardsList(cardSetId)
        }

        this.cardId.value = ""
        this._loadingState.tryEmit(true)
        this.localState.update {
            it.copy(cardSetId = cardSetId)
        }
    }

    fun dismissSync() {
        viewModelScope.launch {
            syncBus.sendEvent(SyncEvent.None)
        }
    }

    fun updateSearchQuery(query: String) {
        localState.update {
            it.copy(searchQuery = query)
        }
    }

    fun selectCard(cardId: String) {
        val cardList = uiState.value.cardList
        val cardIndex = cardList.indexOfFirst { it.id == cardId }
        val previousId = cardList.getOrNull(cardIndex - 1)?.id
        val nextId = cardList.getOrNull(cardIndex + 1)?.id
        Logger.debug("CardListViewModel", "selectCard - $cardId, $previousId, $nextId")
        this.cardId.value = cardId
    }

    fun dismissSelectedCard() {
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
    val sortCardData: SortCardData = SortCardData.CARD_NUMBER,
    val showEmptySearch: Boolean = false
)

data class SyncState(
    val syncType: Type = Type.NONE,
    val message: String = ""
) {
    enum class Type {
        NONE, START, FETCH_CARD, COMPLETE
    }

    val isSyncInProgress: Boolean
        get() = syncType != Type.NONE
}
