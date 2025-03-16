package com.deraesw.pokemoncards.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
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
    val loadingState by cardListViewModel.loadingState.collectAsState()

    Box(
        modifier = modifier,
    ) {
        AnimatedVisibility(
            visible = loadingState,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    CircularProgressIndicator()
                    Text(
                        text = "Loading...",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }

        AnimatedVisibility(
            visible = loadingState.not(),
            enter = fadeIn(),
            exit = fadeOut(),
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
}
