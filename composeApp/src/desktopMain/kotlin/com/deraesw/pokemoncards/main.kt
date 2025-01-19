package com.deraesw.pokemoncards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.deraesw.pokemoncards.di.appModules
import com.deraesw.pokemoncards.presentation.cardset.CardSetContent
import org.koin.core.context.startKoin

fun main() = application {
    startKoin {
        modules(appModules)
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "PokemonCards",
    ) {
        MaterialTheme {
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