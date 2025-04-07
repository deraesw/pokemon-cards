package com.deraesw.pokemoncards.presentation.screen.set.detail

import androidx.compose.material.Scaffold
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

    Scaffold { paddingValue ->

    }
}
