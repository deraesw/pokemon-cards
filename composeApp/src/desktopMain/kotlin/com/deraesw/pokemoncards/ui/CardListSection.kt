package com.deraesw.pokemoncards.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.deraesw.pokemoncards.presentation.screen.card.detail.CardContent
import com.deraesw.pokemoncards.presentation.screen.card.list.CardListContent
import com.deraesw.pokemoncards.presentation.screen.card.list.CardListState
import com.deraesw.pokemoncards.presentation.screen.card.list.CardListViewModel

@Composable
fun CardListSection(
    cardListViewModel: CardListViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by cardListViewModel.uiState.collectAsState()

    Box(
        modifier = modifier,
    ) {
        if (uiState is CardListState.Success) {
            CardListContent(
                cards = (uiState as CardListState.Success).cardList,
                modifier = Modifier.fillMaxSize(),
                onCardClick = cardListViewModel::selectCard
            )

            if ((uiState as CardListState.Success).selectedCard != null) {
                CardContent(
                    cardDetail = (uiState as CardListState.Success).selectedCard!!,
                    onDismiss = cardListViewModel::dismissSelectedCard,
//                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}