package com.deraesw.pokemoncards.presentation.screen.card.detail.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.compose.PcsInfoBox
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme

@Composable
fun FlavorBox(
    flavorText: String,
    modifier: Modifier = Modifier
) {
    PcsInfoBox(modifier = modifier) {
        Row {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "info",
                tint = ColorPalette.Blue500,
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = flavorText,
                style = PokemonCardTheme.typography.labelMedium,
            )
        }
    }
}
