package com.deraesw.pokemoncards.presentation.screen.set.detail.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import org.jetbrains.compose.resources.stringResource
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.release_date_label

@Composable
fun MainSetInformationSection(
    setName: String,
    series: String,
    releaseDate: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = setName,
            style = PokemonCardTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = series,
            style = PokemonCardTheme.typography.titleMedium,
            color = ColorPalette.Gray600,
        )
        Text(
            text = stringResource(Res.string.release_date_label, releaseDate),
            style = PokemonCardTheme.typography.titleSmall,
            color = ColorPalette.Gray600,
        )
    }
}
