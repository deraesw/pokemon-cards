package com.deraesw.pokemoncards.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.model.CardSet
import com.deraesw.pokemoncards.presentation.carddetail.CardSetDetailContent
import com.deraesw.pokemoncards.presentation.carddetail.CardSetDetailState
import com.deraesw.pokemoncards.presentation.carddetail.CardSetDetailViewModel
import com.deraesw.pokemoncards.presentation.cardlist.CardListViewModel
import com.deraesw.pokemoncards.presentation.cardset.CardSetViewModel
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.select_card_set

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
    val uiState = setDetailViewModel.uiState.collectAsState()

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        CardSetSection(
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
                targetState = uiState.value is CardSetDetailState.Success,
                modifier = Modifier.fillMaxSize()
            ) {
                when (val state = uiState.value) {
                    is CardSetDetailState.Success -> {
                        DetailSection(
                            cardSet = state.cardSet,
                            cardListViewModel = cardListViewModel,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }

                    else -> {
                        Text(
                            text = stringResource(Res.string.select_card_set),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DetailSection(
    cardSet: CardSet,
    cardListViewModel: CardListViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        CardSetDetailContent(
            set = cardSet,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        )
        HorizontalDivider(
            thickness = 1.dp,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(ColorPalette.Gray200)
        )
        HorizontalDivider(
            thickness = 1.dp,
        )
        CardListSection(
            cardListViewModel = cardListViewModel,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White)
        )
    }
}