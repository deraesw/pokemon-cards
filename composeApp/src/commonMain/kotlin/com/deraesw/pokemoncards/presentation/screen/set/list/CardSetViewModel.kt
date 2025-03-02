package com.deraesw.pokemoncards.presentation.screen.set.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deraesw.pokemoncards.core.core.model.CardSet
import com.deraesw.pokemoncards.core.core.model.SortData
import com.deraesw.pokemoncards.data.repository.CardSetRepository
import com.deraesw.pokemoncards.domain.NetworkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class CardSetViewModel(
    private val cardSetRepository: CardSetRepository,
    private val networkManager: NetworkManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(CardSetState())
    val uiState: StateFlow<CardSetState> = _uiState
        //.debounce(300) to read about it
        .flatMapLatest { state ->
            cardSetRepository.allCardSets(
                sorter = state.sortData
            ).map { cardSetList ->
                state.copy(
                    cardSetList = filterCardSets(
                        query = state.searchQuery,
                        list = cardSetList
                    ),
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5000),
            initialValue = _uiState.value
        )

    fun setSelectedCardSet(id: String) {
        _uiState.update {
            it.copy(selectedCardSetId = id)
        }
        viewModelScope.launch(Dispatchers.IO) {
            networkManager.fetchSetCardsList(id)
        }
    }

    fun setSortData(sortData: SortData) {
        _uiState.update {
            it.copy(sortData = sortData)
        }
    }

    fun updateSearchQuery(query: String) {
        _uiState.update {
            it.copy(searchQuery = query)
        }
    }

    private fun filterCardSets(
        query: String,
        list: List<CardSet>
    ): List<CardSet> {
        return if (query.isEmpty()) {
            list
        } else {
            list.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
    }
}

data class CardSetState(
    val cardSetList: List<CardSet> = listOf(),
    val selectedCardSetId: String? = null,
    val sortData: SortData = SortData.NAME,
    val searchQuery: String = ""
)
