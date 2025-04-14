package com.deraesw.pokemoncards.ui.compose.pane

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.compose.divider.PcsHorDivider
import com.deraesw.pokemoncards.presentation.screen.set.list.CardSetContent
import com.deraesw.pokemoncards.presentation.screen.set.list.CardSetViewModel
import com.deraesw.pokemoncards.ui.compose.section.CardSetSectionHeader

@Composable
fun CardSetPane(
    cardSetViewModel: CardSetViewModel,
    modifier: Modifier = Modifier,
    onCardSetClick: (String) -> Unit = {}
) {
    val uiState by cardSetViewModel.uiState.collectAsState()
    val state = rememberLazyListState()

    Column(
        modifier = modifier
    ) {
        CardSetSectionHeader(
            sortData = uiState.sortData,
            onSelected = {
                cardSetViewModel.setSortData(it)
            },
            onSearchQueryChanged = {
                cardSetViewModel.updateSearchQuery(it)
            }
        )
        PcsHorDivider()
        CardSetContent(
            cardSetModelList = uiState.cardSetModelList,
            cardSetSelected = uiState.selectedCardSetId,
            listState = state,
            onCardSetClick = {
                cardSetViewModel.setSelectedCardSet(it)
                onCardSetClick(it)
            },
            scrollableContent = {
                VerticalScrollbar(
                    adapter = rememberScrollbarAdapter(state),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .fillMaxHeight()
                )
            },
            selectorIndicator = true,
            modifier = Modifier.padding(end = 10.dp)
        )
    }
}
