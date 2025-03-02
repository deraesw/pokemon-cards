package com.deraesw.pokemoncards.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.deraesw.pokemoncards.presentation.screen.card.detail.CardContent
import com.deraesw.pokemoncards.presentation.screen.card.list.CardListContent
import com.deraesw.pokemoncards.presentation.screen.card.list.CardListViewModel

@Composable
fun CardListSection(
    cardListViewModel: CardListViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by cardListViewModel.uiState.collectAsState()
    val cardDetail by cardListViewModel.cardDetail.collectAsState()

    Box(
        modifier = modifier,
    ) {
        CardListContent(
            cards = uiState.cardList,
            modifier = Modifier.fillMaxSize(),
            onCardClick = cardListViewModel::selectCard
        )
        if (cardDetail != null) {
            CardContent(
                cardDetail = cardDetail!!,
                onDismiss = cardListViewModel::dismissSelectedCard,
//                    modifier = Modifier
//                        .align(Alignment.Center)
            )
        }
//        if (uiState is CardListState.Success) {
//
//        }
    }
}