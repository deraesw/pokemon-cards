package com.deraesw.pokemoncards.ui.compose.pane

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.core.core.model.CardSet
import com.deraesw.pokemoncards.core.core.model.SortCardData
import com.deraesw.pokemoncards.data.DisplaySelectorData
import com.deraesw.pokemoncards.presentation.screen.card.list.CardListViewModel
import com.deraesw.pokemoncards.presentation.screen.set.detail.CardSetDetailContent
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.ui.compose.section.CardListActionSection
import com.deraesw.pokemoncards.ui.compose.section.CardListSection

@Composable
fun CardSetDetailPane(
    sortCardData: SortCardData,
    cardSet: CardSet,
    cardListViewModel: CardListViewModel,
    modifier: Modifier = Modifier
) {
    var displaySelector by remember { mutableStateOf(DisplaySelectorData.Grid) }

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
                displaySelector = displaySelector,
                onClickDisplaySelector = {
                    displaySelector = when (displaySelector) {
                        DisplaySelectorData.Grid -> DisplaySelectorData.List
                        DisplaySelectorData.List -> DisplaySelectorData.Grid
                    }
                },
                onClickReSync = {
                    cardListViewModel.reSyncCardList()
                },
                onSortSelect = {
                    cardListViewModel.dismissSelectedCard()
                    cardListViewModel.setSortCardData(it)
                },
                onClearQuery = {
                    cardListViewModel.dismissSelectedCard()
                    cardListViewModel.updateSearchQuery("")
                },
                onQueryChange = {
                    cardListViewModel.dismissSelectedCard()
                    cardListViewModel.updateSearchQuery(it)
                }
            )
        }
        HorizontalDivider(
            thickness = 1.dp,
        )
        CardListSection(
            displaySelector = displaySelector,
            cardListViewModel = cardListViewModel,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White)
        )
    }
}
