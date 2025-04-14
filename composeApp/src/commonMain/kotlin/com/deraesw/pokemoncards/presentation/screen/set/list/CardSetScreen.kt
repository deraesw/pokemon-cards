package com.deraesw.pokemoncards.presentation.screen.set.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
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

    Scaffold { paddingValue ->
        CardSetContent(
            modifier = modifier.padding(paddingValue),
            cardSetModelList = uiState.cardSetModelList,
            cardSetSelected = uiState.selectedCardSetId,
            onCardSetClick = {
                viewModel.setSelectedCardSet(it)
                onCardSetClick(it)
            }
        )
    }
}
