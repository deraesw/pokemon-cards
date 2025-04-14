package com.deraesw.pokemoncards.presentation.screen.set.list.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.deraesw.pokemoncards.presentation.model.CardSetListItem
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme

@Composable
fun CardSetRowDetails(
    cardSet: CardSetListItem,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = cardSet.name,
            style = PokemonCardTheme.typography.titleMedium,
        )

        Text(
            text = "${cardSet.series} - ${cardSet.total} cards",
            style = PokemonCardTheme.typography.labelMedium,
            color = ColorPalette.Gray600,
        )

        Text(
            text = cardSet.formatedReleaseDate,
            style = PokemonCardTheme.typography.labelMedium,
            color = ColorPalette.Gray600,
        )
    }
}
