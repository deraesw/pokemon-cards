package com.deraesw.pokemoncards

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.deraesw.pokemoncards.di.appModules
import org.koin.core.context.startKoin

fun main() = application {
    startKoin {
        modules(appModules)
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "PokemonCards",
    ) {
        App()
    }
}