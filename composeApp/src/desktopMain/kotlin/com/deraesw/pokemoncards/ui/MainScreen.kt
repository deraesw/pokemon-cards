package com.deraesw.pokemoncards.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.core.core.model.CardSet
import com.deraesw.pokemoncards.core.core.model.SortCardData
import com.deraesw.pokemoncards.presentation.compose.CardSortButton
import com.deraesw.pokemoncards.presentation.compose.PcsSearchComponent
import com.deraesw.pokemoncards.presentation.screen.card.list.CardListViewModel
import com.deraesw.pokemoncards.presentation.screen.set.detail.CardSetDetailContent
import com.deraesw.pokemoncards.presentation.screen.set.detail.CardSetDetailState
import com.deraesw.pokemoncards.presentation.screen.set.detail.CardSetDetailViewModel
import com.deraesw.pokemoncards.presentation.screen.set.list.CardSetViewModel
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.select_card_set

@Composable
fun MainScreen(
    viewModel: MainViewModel = koinInject()
) {
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
    val uiCardState by cardListViewModel.uiState.collectAsState()
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
                            sortCardData = uiCardState.sortCardData,
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
    sortCardData: SortCardData,
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
        ) {
            CardListActionSection(
                sortCardData = sortCardData,
                onClickReSync = {
                    cardListViewModel.reSyncCardList()
                },
                onSortSelect = {
                    cardListViewModel.setSortCardData(it)
                }
            )
        }
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

@Composable
fun CardListActionSection(
    sortCardData: SortCardData,
    onClickReSync: () -> Unit,
    onSortSelect: (SortCardData) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxSize()
    ) {
        PcsSearchComponent(
            onClearQuery = {},
            onQueryChange = {},
            placeholder = "Search card list on this set...",
            modifier = Modifier.width(256.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            CardSortButton(
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                size = DpSize(128.dp, 32.dp),
                sortCardData = sortCardData,
                onSortSelect = onSortSelect
            )

            Box(
                modifier = Modifier
                    .border(1.dp, ColorPalette.Gray400, RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .clickable {
                        onClickReSync()
                    }
//                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Icon(
                    Icons.Default.AccountBox,
                    contentDescription = "Temp",
                    tint = ColorPalette.Gray500,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
