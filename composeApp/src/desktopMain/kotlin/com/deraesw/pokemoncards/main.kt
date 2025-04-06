package com.deraesw.pokemoncards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.deraesw.pokemoncards.core.core.util.Logger
import com.deraesw.pokemoncards.core.database.factory.DriverFactory
import com.deraesw.pokemoncards.di.pcsInitKoin
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import com.deraesw.pokemoncards.ui.compose.screen.LauncherScreen
import com.deraesw.pokemoncards.ui.compose.screen.MainScreen
import com.deraesw.pokemoncards.viewmodels.MainViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.app_name

fun main() = application {
    Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
        Logger.error(
            "",
            "Uncaught exception in thread ${thread.name}: ${throwable.message}",
            throwable
        )
    }
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
            LauncherScreen()
        }
    }
}
