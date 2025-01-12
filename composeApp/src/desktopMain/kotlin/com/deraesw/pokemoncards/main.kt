package com.deraesw.pokemoncards

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "PokemonCards",
    ) {
        App()
    }
}