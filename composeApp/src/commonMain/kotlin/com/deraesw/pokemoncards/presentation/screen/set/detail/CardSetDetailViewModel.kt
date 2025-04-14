package com.deraesw.pokemoncards.presentation.screen.set.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deraesw.pokemoncards.core.core.model.CardSetModel
import com.deraesw.pokemoncards.core.data.repository.CardSetRepository
import com.deraesw.pokemoncards.presentation.model.CardSetDetail
import com.deraesw.pokemoncards.presentation.model.mapper.toCardSetDetail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalCoroutinesApi::class)
class CardSetDetailViewModel(
    private val cardSetRepository: CardSetRepository,
    providedSetId: String? = null
) : ViewModel() {

    private val setId = MutableStateFlow(providedSetId)

    val uiState: StateFlow<CardSetDetailState> = setId
        .flatMapLatest { id ->
            val cardSet = retrieveCardSet(id)
            val state = if (cardSet != null) {
                CardSetDetailState(cardSetDetail = cardSet.toCardSetDetail())
            } else {
                CardSetDetailState()
            }
            flowOf(state)
        }.stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5000),
            initialValue = CardSetDetailState()
        )

    private fun retrieveCardSet(id: String?): CardSetModel? {
        return if (id != null) cardSetRepository.getSet(id) else null
    }

    fun getCardSet(id: String) {
        setId.update { id }
    }
}

data class CardSetDetailState(
    val cardSetDetail: CardSetDetail? = null
)
