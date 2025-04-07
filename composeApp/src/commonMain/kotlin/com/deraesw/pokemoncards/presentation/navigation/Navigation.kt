package com.deraesw.pokemoncards.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.deraesw.pokemoncards.presentation.screen.set.detail.CardSetDetailScreen
import com.deraesw.pokemoncards.presentation.screen.set.list.CardSetScreen
import com.deraesw.pokemoncards.presentation.screen.set.list.CardSetViewModel

fun NavGraphBuilder.cardSetList(
    viewModel: CardSetViewModel,
    onCardSetClick: (String) -> Unit,
) {
    composable(
        route = "cardSetList",
    ) {
        CardSetScreen(
            onCardSetClick = onCardSetClick,
            viewModel = viewModel
        )
    }
}

fun NavGraphBuilder.cardSetDetail() {
    composable(
        route = "cardSetDetail",
    ) {
        CardSetDetailScreen()
    }
}