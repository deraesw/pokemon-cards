package com.deraesw.pokemoncards.presentation.set.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.koin.compose.koinInject

@Composable
fun CardSetScreen(
    modifier: Modifier = Modifier,
    onCardSetClick: (String) -> Unit = {},
    viewModel: CardSetViewModel = koinInject(),
) {
    val uiState = viewModel.uiState.collectAsState().value

    CardSetContent(
        modifier = modifier,
        cardSetList = uiState.cardSetList,
        cardSetSelected = uiState.selectedCardSetId,
        onCardSetClick = {
            viewModel.setSelectedCardSet(it)
            onCardSetClick(it)
        }
    )
}
