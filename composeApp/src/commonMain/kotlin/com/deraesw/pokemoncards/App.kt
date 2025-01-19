package com.deraesw.pokemoncards

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.deraesw.pokemoncards.data.repository.CardSetRepository
import com.deraesw.pokemoncards.network.service.PokemonCardApiService
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        var content by remember { mutableStateOf("") }
        var content1 by remember { mutableStateOf("") }
        val corountine = rememberCoroutineScope()
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }

            Button(onClick = {
                corountine.launch {
                    val response = MyComponent().pokemonCardApiService.getAllSets()
                    println(response)
                    content = response.first().toString()
                }
            }) {
                Text("Click me! sets")
            }
            Text(content)

            Button(onClick = {
                corountine.launch {
                    val response = MyComponent().pokemonCardApiService.getSetCards("base1")
                    println(response)
                    content1 = response.first().toString()
                }
            }) {
                Text("Click me! card base 1")
            }

            Button(onClick = {
                corountine.launch {
                    val response = MyComponent().cardSetRepository.getAllSets()
                    println(response)
                    content1 = response.firstOrNull()?.toString() ?: "no data"
                }
            }) {
                Text("Click me! databse")
            }
            Text(content1)
        }
    }
}

class MyComponent : KoinComponent {
    val pokemonCardApiService: PokemonCardApiService by inject()
    val cardSetRepository: CardSetRepository by inject()
}