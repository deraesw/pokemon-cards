package com.deraesw.pokemoncards.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.deraesw.pokemoncards.presentation.card.list.CardListContent
import com.deraesw.pokemoncards.presentation.card.list.CardListState
import com.deraesw.pokemoncards.presentation.card.list.CardListViewModel

@Composable
fun CardListSection(
    cardListViewModel: CardListViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by cardListViewModel.uiState.collectAsState()

    if (uiState is CardListState.Success) {
        CardListContent(
            cards = (uiState as CardListState.Success).cardList,
            modifier = modifier,
            onCardClick = {

            }
        )
    }

}