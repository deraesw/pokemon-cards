package com.deraesw.pokemoncards.presentation.screen.set.detail.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.deraesw.pokemoncards.core.core.model.CardSet
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import org.jetbrains.compose.resources.stringResource
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.release_date_label

@Composable
fun MainSetInformationSection(
    set: CardSet,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = set.name,
            style = PokemonCardTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = set.series,
            style = PokemonCardTheme.typography.titleMedium,
            color = ColorPalette.Gray600,
        )
        Text(
            text = stringResource(Res.string.release_date_label, set.formatedReleaseDate),
            style = PokemonCardTheme.typography.titleSmall,
            color = ColorPalette.Gray600,
        )
    }
}
