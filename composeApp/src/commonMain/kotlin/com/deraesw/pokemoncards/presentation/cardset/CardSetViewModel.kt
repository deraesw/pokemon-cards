package com.deraesw.pokemoncards.presentation.cardset

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deraesw.pokemoncards.data.repository.CardSetRepository
import com.deraesw.pokemoncards.model.CardSet
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

    init {
        viewModelScope.launch {
            cardSetRepository
                .allCardSets()
                .collect { list ->
                    println("collect: $list")
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
}

data class CardSetState(
    val cardSetList: List<CardSet> = listOf(),
    val selectedCardSetId: String? = null
)
