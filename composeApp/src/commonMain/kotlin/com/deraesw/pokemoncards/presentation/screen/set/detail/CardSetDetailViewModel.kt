package com.deraesw.pokemoncards.presentation.screen.set.detail

import androidx.lifecycle.ViewModel
import com.deraesw.pokemoncards.core.data.repository.CardSetRepository
import com.deraesw.pokemoncards.presentation.model.CardSetDetail
import com.deraesw.pokemoncards.presentation.model.mapper.toCardSetDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CardSetDetailViewModel(
    private val cardSetRepository: CardSetRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CardSetDetailState())
    val uiState: StateFlow<CardSetDetailState> = _uiState.asStateFlow()

    fun getCardSet(id: String) {
        val cardSet = cardSetRepository.getSet(id) ?: return

        _uiState.update {
            CardSetDetailState(
                cardSetDetail = cardSet.toCardSetDetail()
            )
        }
    }
}

data class CardSetDetailState(
    val cardSetDetail: CardSetDetail? = null
)
