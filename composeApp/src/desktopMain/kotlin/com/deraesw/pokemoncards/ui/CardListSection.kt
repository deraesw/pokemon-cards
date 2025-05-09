package com.deraesw.pokemoncards.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.screen.card.detail.CardContent
import com.deraesw.pokemoncards.presentation.screen.card.list.CardListContent
import com.deraesw.pokemoncards.presentation.screen.card.list.CardListViewModel
import com.deraesw.pokemoncards.presentation.screen.card.list.SyncState
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme

@Composable
fun CardListSection(
    cardListViewModel: CardListViewModel,
    modifier: Modifier = Modifier
) {
    val state: LazyGridState = rememberLazyGridState()
    val uiState by cardListViewModel.uiState.collectAsState()
    val cardDetail by cardListViewModel.cardDetail.collectAsState()
    val loadingState by cardListViewModel.loadingState.collectAsState()
    val syncState by cardListViewModel.syncState.collectAsState()

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
            if (uiState.showEmptySearch) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Image(
                            Icons.Default.Search,
                            contentDescription = "Search icon",
                            colorFilter = tint(ColorPalette.Gray600),
                            modifier = Modifier.size(64.dp).padding(bottom = 16.dp)
                        )
                        Text(
                            text = "No result found!",
                            style = PokemonCardTheme.typography.titleMedium,
                        )
                        Text(
                            text = "We couldn't find what you're looking for. Please try again with different search terms.",
                            style = PokemonCardTheme.typography.bodyMedium,
                        )
                    }
                }
            } else {
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
            }

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

        AnimatedVisibility(
            visible = syncState.isSyncInProgress,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            Box(
                modifier = Modifier
                    .background(ColorPalette.GrimBlack)
                    .fillMaxSize()
                    .clickable(interactionSource = null, indication = null) {
                        // No click
                    }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .padding(32.dp)
                        .align(Alignment.Center)
                ) {
                    Text(
                        text = "Synchronization in progress",
                        style = PokemonCardTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    if (syncState.syncType != SyncState.Type.COMPLETE) {
                        LinearProgressIndicator()
                        if (syncState.message.isNotEmpty()) {
                            Text(
                                text = syncState.message,
                                style = PokemonCardTheme.typography.bodyMedium,
                                modifier = Modifier.padding(vertical = 16.dp)
                            )
                        }
                        TextButton(
                            onClick = {
                                cardListViewModel.dismissSync()
                            }
                        ) {
                            Text(text = "Cancel")
                        }
                    } else {
                        TextButton(
                            onClick = {
                                cardListViewModel.dismissSync()
                            }
                        ) {
                            Text(text = "Dismiss")
                        }
                    }
                }
            }
        }
    }
}
