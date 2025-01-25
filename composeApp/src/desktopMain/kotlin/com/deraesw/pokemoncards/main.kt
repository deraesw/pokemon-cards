package com.deraesw.pokemoncards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.deraesw.pokemoncards.di.appModules
import com.deraesw.pokemoncards.presentation.cardset.CardSetContent
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import kotlinx.coroutines.launch
import org.koin.core.context.startKoin

fun main() = application {
    startKoin {
        modules(appModules)
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "PokemonCards",
        state = rememberWindowState(
            width = 1024.dp,
            height = 768.dp
        )
    ) {
        PokemonCardTheme {
            val corountine = rememberCoroutineScope()
            SideEffect {
                println("SideEffect")
                corountine.launch {
                    println("launch")
                    SyncManager.initialSync()
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                CardSetContent(
                    modifier = Modifier.width(256.dp)
                )
                VerticalDivider()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}
