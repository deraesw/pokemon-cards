package com.deraesw.pokemoncards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.deraesw.pokemoncards.presentation.navigation.cardList
import com.deraesw.pokemoncards.presentation.navigation.cardSetDetail
import com.deraesw.pokemoncards.presentation.navigation.cardSetList
import com.deraesw.pokemoncards.presentation.screen.set.list.CardSetViewModel
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
@Preview
fun MobileApp(
    viewModel: CardSetViewModel = koinInject(),
) {
    SideEffect {
        viewModel.initialSync()
    }
    PokemonCardTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "cardSetList"
        ) {
            cardSetList(
                viewModel = viewModel,
                onCardSetClick = { setId ->
                    navController.navigate("cardSetDetail/$setId")
                }
            )

            cardSetDetail(
                onNavigateBack = {
                    navController.navigateUp()
                },
                onCardListClick = { setId ->
                    navController.navigate("cardList/$setId")
                }
            )

            cardList(
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}
