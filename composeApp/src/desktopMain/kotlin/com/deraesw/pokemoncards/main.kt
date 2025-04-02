package com.deraesw.pokemoncards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.deraesw.pokemoncards.core.database.factory.DriverFactory
import com.deraesw.pokemoncards.di.pcsInitKoin
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import com.deraesw.pokemoncards.ui.MainScreen
import com.deraesw.pokemoncards.ui.MainViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.app_name

fun main() = application {
    pcsInitKoin {
        modules(
            module {
                single { DriverFactory() }
                viewModel { MainViewModel(get()) }
            }
        )
    }

    Navigator()
}

@Composable
private fun ApplicationScope.Navigator(
    mainViewModel: MainViewModel = koinInject()
) {
    val mainState by mainViewModel.uiState.collectAsState()

    if (mainState.isLoading) {
        launchApplication()
    } else {
        mainApplication()
    }
}

@Composable
private fun ApplicationScope.mainApplication() {
    Window(
        onCloseRequest = ::exitApplication,
        title = stringResource(Res.string.app_name),
        state = rememberWindowState(
            width = 1024.dp,
            height = 768.dp,
            placement = WindowPlacement.Floating,
            position = WindowPosition.Aligned(alignment = Alignment.Center)
        )
    ) {
        PokemonCardTheme {
            MainScreen()
        }
    }
}

@Composable
private fun ApplicationScope.launchApplication() {
    Window(
        onCloseRequest = ::exitApplication,
        title = stringResource(Res.string.app_name),
        undecorated = true,
        resizable = false,
        alwaysOnTop = true,
        state = rememberWindowState(
            width = 1024.dp,
            height = 768.dp,
            placement = WindowPlacement.Floating,
            position = WindowPosition.Aligned(alignment = Alignment.Center)
        )
    ) {
        PokemonCardTheme {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(ColorPalette.Indigo900)
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(
                        text = stringResource(Res.string.app_name),
                        style = PokemonCardTheme.typography.titleLarge,
                        color = Color.White
                    )
                    Text(
                        text = "Please wait while we are initializing the application...",
                        style = PokemonCardTheme.typography.bodyMedium,
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    LinearProgressIndicator()
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 8.dp)
                ) {
                    Text(
                        text = "Version 1.0.0",
                        style = PokemonCardTheme.typography.labelMedium,
                        color = Color.White
                    )
                    Text(
                        text = "© 2025 Pokemon Cards.",
                        style = PokemonCardTheme.typography.labelSmall,
                        color = Color.White
                    )
                }
            }
        }
    }
}
