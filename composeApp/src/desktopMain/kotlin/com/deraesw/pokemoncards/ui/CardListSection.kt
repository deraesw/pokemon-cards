package com.deraesw.pokemoncards.ui

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.screen.card.detail.CardContent
import com.deraesw.pokemoncards.presentation.screen.card.list.CardListContent
import com.deraesw.pokemoncards.presentation.screen.card.list.CardListViewModel

@Composable
fun CardListSection(
    cardListViewModel: CardListViewModel,
    modifier: Modifier = Modifier
) {
    val state: LazyGridState = rememberLazyGridState()
    val uiState by cardListViewModel.uiState.collectAsState()
    val cardDetail by cardListViewModel.cardDetail.collectAsState()

    Box(
        modifier = modifier,
    ) {
        CardListContent(
            cards = uiState.cardList,
            modifier = Modifier.fillMaxSize().padding(16.dp),
            state = state,
            onCardClick = cardListViewModel::selectCard,
            scrollIndicatorSlot = {
                VerticalScrollbar(
                    adapter = rememberScrollbarAdapter(state),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .fillMaxHeight()
                )
            }
        )

        cardDetail?.apply {
            CardContent(
                cardDetail = this,
                onDismiss = cardListViewModel::dismissSelectedCard,
                onRefresh = {
                    cardListViewModel.reSyncCard(cardId = this.id)
                }
            )
        }
    }
}
