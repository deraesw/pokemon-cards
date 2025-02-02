package com.deraesw.pokemoncards.presentation.cardset

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deraesw.pokemoncards.data.repository.CardSetRepository
import com.deraesw.pokemoncards.model.CardSet
import com.deraesw.pokemoncards.model.SortData
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CardSetViewModel(
    private val cardSetRepository: CardSetRepository
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
        sorter: SortData
    ) {
        println("This: $this")
        job?.cancel()
        job = viewModelScope.launch {
            cardSetRepository
                .allCardSets(
                    sorter = sorter
                )
                .collect { list ->
                    _uiState.update {
                        it.copy(cardSetList = list)
                    }
                }
        }
    }

    fun setSelectedCardSet(id: String) {
        _uiState.update {
            it.copy(selectedCardSetId = id)
        }
    }

    fun setSortData(sortData: SortData) {
        println("This: $this")
        _uiState.update {
            it.copy(sortData = sortData)
        }
        fetchAllCardSets(sortData)
    }
}

data class CardSetState(
    val cardSetList: List<CardSet> = listOf(),
    val selectedCardSetId: String? = null,
    val sortData: SortData = SortData.NAME
)
