package com.deraesw.pokemoncards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
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
    ) {
        PokemonCardTheme {
            val corountine = rememberCoroutineScope()
            SideEffect {
                println("SideEffect")
                corountine.launch {
                    println("launch")
                    SyncManager.syncWhenNotDone()
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                CardSetContent(
                    modifier = Modifier.width(160.dp)
                )
            }
        }
    }
}