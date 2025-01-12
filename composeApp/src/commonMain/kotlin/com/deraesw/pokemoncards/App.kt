package com.deraesw.pokemoncards

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.deraesw.pokemoncards.network.NetworkClient
import com.deraesw.pokemoncards.network.createHttpEngine
import com.deraesw.pokemoncards.network.service.PokemonCardApiServiceImp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        var content by remember { mutableStateOf("") }
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
                    val response = PokemonCardApiServiceImp(NetworkClient(createHttpEngine()).newInstance()).getAllSets()
                    println(response)
                    content = response.first().toString()
                }
            }) {
                Text("Click me!")
            }
            Text(content)
        }
    }
}