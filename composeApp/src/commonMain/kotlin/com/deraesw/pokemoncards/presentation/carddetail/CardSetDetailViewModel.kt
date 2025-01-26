package com.deraesw.pokemoncards.presentation.carddetail

import androidx.lifecycle.ViewModel
import com.deraesw.pokemoncards.data.repository.CardSetRepository
import com.deraesw.pokemoncards.model.CardSet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CardSetDetailViewModel(
    private val cardSetRepository: CardSetRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CardSetDetailState>(CardSetDetailState.Empty)
    val uiState: StateFlow<CardSetDetailState> = _uiState.asStateFlow()

    fun getCardSet(id: String) {
        println("Set : $id")
        val cardSet = cardSetRepository.getSet(id) ?: return

        _uiState.update {
            CardSetDetailState.Success(cardSet)
        }
    }
}

sealed interface CardSetDetailState {
    data class Success(val cardSet: CardSet) : CardSetDetailState
    object Empty : CardSetDetailState
//    object Loading : CardSetDetailState
//    object Error : CardSetDetailState
}
