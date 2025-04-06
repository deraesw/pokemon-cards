package com.deraesw.pokemoncards.ui.compose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import org.jetbrains.compose.resources.stringResource
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.app_name

@Composable
fun LauncherScreen() {
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
//            LinearProgressIndicator()
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
