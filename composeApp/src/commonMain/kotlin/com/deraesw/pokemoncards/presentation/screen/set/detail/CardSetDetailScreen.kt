package com.deraesw.pokemoncards.presentation.screen.set.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.koin.compose.koinInject

@Composable
fun CardSetDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: CardSetDetailViewModel = koinInject()
) {
    val uiState = viewModel.uiState.collectAsState()

    when (val state = uiState.value) {
        is CardSetDetailState.Success -> {
            CardSetDetailContent(
                set = state.cardSet,
                modifier = modifier
            )
        }

        else -> {}
    }
}
