package com.deraesw.pokemoncards.presentation.cardset

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deraesw.pokemoncards.data.repository.CardSetRepository
import com.deraesw.pokemoncards.domain.NetworkManager
import com.deraesw.pokemoncards.model.CardSet
import com.deraesw.pokemoncards.model.SortData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CardSetViewModel(
    private val cardSetRepository: CardSetRepository,
    private val networkManager: NetworkManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        CardSetState()
    )
    val uiState: StateFlow<CardSetState> = _uiState.asStateFlow()

    private var job: Job? = null

    init {
        fetchAllCardSets(
            sorter = _uiState.value.sortData
        )
    }

    fun fetchAllCardSets(
        sorter: SortData,
        query: String = ""
    ) {
        job?.cancel()
        job = viewModelScope.launch {
            cardSetRepository
                .allCardSets(
                    sorter = sorter
                )
                .collect { list ->
                    _uiState.update {
                        it.copy(
                            cardSetList = filterCardSets(
                                query = query,
                                list = list
                            )
                        )
                    }
                }
        }
    }

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
        fetchAllCardSets(
            sorter = sortData,
            query = _uiState.value.searchQuery
        )
    }

    fun updateSearchQuery(query: String) {
        _uiState.update {
            it.copy(searchQuery = query)
        }
        fetchAllCardSets(
            sorter = _uiState.value.sortData,
            query = query
        )
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
