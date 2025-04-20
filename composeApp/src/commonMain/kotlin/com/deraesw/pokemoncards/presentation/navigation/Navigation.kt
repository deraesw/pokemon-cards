package com.deraesw.pokemoncards.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.deraesw.pokemoncards.presentation.screen.card.detail.CardDetailScreen
import com.deraesw.pokemoncards.presentation.screen.card.list.CardListScreen
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
    onCardListClick: (String) -> Unit,
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
            onNavigateBack = onNavigateBack,
            onCardListClick = onCardListClick
        )
    }
}

fun NavGraphBuilder.cardList(
    onNavigateBack: () -> Unit,
    onNavigateToCardDetail: (String) -> Unit,
) {
    composable(
        route = "cardList/{idSet}",
        arguments = listOf(
            navArgument("idSet") {
                type = NavType.StringType
            }
        )
    ) {
        val setId = it.arguments?.getString("idSet") ?: return@composable
        CardListScreen(
            cardSetId = setId,
            onNavigateBack = onNavigateBack,
            onNavigateToCardDetail = onNavigateToCardDetail
        )
    }
}

fun NavGraphBuilder.cardDetail(
    onNavigateBack: () -> Unit,
) {
    composable(
        route = "carddetail/{id}",
        arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
            }
        )
    ) {
        val cardId = it.arguments?.getString("id") ?: return@composable
        CardDetailScreen(
            cardId = cardId,
            onNavigateBack = onNavigateBack
        )
    }
}
