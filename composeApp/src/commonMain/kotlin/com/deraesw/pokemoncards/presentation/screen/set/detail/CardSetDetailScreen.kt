package com.deraesw.pokemoncards.presentation.screen.set.detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Composable
fun CardSetDetailScreen(
    cardSetId: String,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CardSetDetailViewModel = koinInject(
        parameters = { parametersOf(cardSetId) }
    )
) {
    val uiState by viewModel.uiState.collectAsState()

    uiState.cardSetDetail?.let { data ->
        CardSetDetailContent(
            set = data,
            onNavigateBack = onNavigateBack,
            modifier = modifier
        )
    } ?: run {
        Text(
            text = "No data",
            modifier = modifier
        )
    }
}
