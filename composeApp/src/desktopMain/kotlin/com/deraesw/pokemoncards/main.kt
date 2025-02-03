package com.deraesw.pokemoncards

import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.deraesw.pokemoncards.di.pcsInitKoin
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import com.deraesw.pokemoncards.ui.MainScreen
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.app_name

fun main() = application {
    pcsInitKoin()
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
        val corountine = rememberCoroutineScope()
        SideEffect {
            println("SideEffect")
            corountine.launch {
                println("launch")
                SyncManager.initialSync()
            }
        }
        PokemonCardTheme {
            MainScreen()
        }
    }
}
