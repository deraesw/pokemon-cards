package com.deraesw.pokemoncards.presentation.screen.card.detail.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.deraesw.pokemoncards.presentation.compose.PcsInfoBox
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme

@Composable
fun RulesBox(
    rules: String,
    modifier: Modifier = Modifier
) {
    PcsInfoBox(modifier = modifier) {
        Column {
            Text(
                text = "Rules",
                style = PokemonCardTheme.typography.labelLarge,
                color = ColorPalette.Blue700,
            )
            Text(
                text = rules,
                style = PokemonCardTheme.typography.labelMedium,
            )
        }
    }
}
