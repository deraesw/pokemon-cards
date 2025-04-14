package com.deraesw.pokemoncards.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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

fun NavGraphBuilder.cardSetDetail(
    onNavigateBack: () -> Unit,
) {
    composable(
        route = "cardSetDetail/{id}",
        arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
            }
        )
    ) {
        val setId = it.arguments?.getString("id") ?: return@composable
        CardSetDetailScreen(
            cardSetId = setId,
            onNavigateBack = onNavigateBack
        )
    }
}
