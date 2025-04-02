package com.deraesw.pokemoncards.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.screen.card.list.CardListViewModel
import com.deraesw.pokemoncards.presentation.screen.set.detail.CardSetDetailState
import com.deraesw.pokemoncards.presentation.screen.set.detail.CardSetDetailViewModel
import com.deraesw.pokemoncards.presentation.screen.set.list.CardSetViewModel
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.ui.compose.pane.CardSetDetailPane
import com.deraesw.pokemoncards.ui.compose.pane.CardSetPane
import com.deraesw.pokemoncards.ui.compose.widget.NoCardSetSelected
import org.koin.compose.koinInject

@Composable
fun MainScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorPalette.Gray200)
    ) {
        TwoPanelLayout()
    }
}

@Composable
fun TwoPanelLayout(
    cardSetViewModel: CardSetViewModel = koinInject(),
    setDetailViewModel: CardSetDetailViewModel = koinInject(),
    cardListViewModel: CardListViewModel = koinInject(),
) {
    val uiState by setDetailViewModel.uiState.collectAsState()
    val uiCardState by cardListViewModel.uiState.collectAsState()

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        CardSetPane(
            cardSetViewModel = cardSetViewModel,
            onCardSetClick = {
                setDetailViewModel.getCardSet(it)
                cardListViewModel.selectCardSet(it)
            },
            modifier = Modifier
                .padding(16.dp)
                .width(320.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            AnimatedContent(
                targetState = uiState is CardSetDetailState.Success,
                modifier = Modifier.fillMaxSize(),
            ) {
                when (val state = uiState) {
                    is CardSetDetailState.Success -> {
                        CardSetDetailPane(
                            sortCardData = uiCardState.sortCardData,
                            cardSet = state.cardSet,
                            cardListViewModel = cardListViewModel,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    else -> {
                        NoCardSetSelected(
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}
