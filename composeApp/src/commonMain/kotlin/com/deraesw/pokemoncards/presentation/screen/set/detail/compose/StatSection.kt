package com.deraesw.pokemoncards.presentation.screen.set.detail.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import org.jetbrains.compose.resources.stringResource
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.printed_cards
import pokemoncards.composeapp.generated.resources.set_code
import pokemoncards.composeapp.generated.resources.total_cards

@Composable
fun StatSection(
    totalCards: String,
    printedCards: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        TileInfo(
            title = stringResource(Res.string.total_cards),
            content = totalCards
        )
        Spacer(modifier = Modifier.size(8.dp))
        TileInfo(
            title = stringResource(Res.string.printed_cards),
            content = printedCards
        )
        Spacer(modifier = Modifier.size(8.dp))
        TileInfo(
            title = stringResource(Res.string.set_code),
            content = ""
        )
    }
}

@Composable
fun TileInfo(
    title: String,
    content: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = PokemonCardTheme.typography.labelMedium,
            color = ColorPalette.Gray500,
        )
        Text(
            text = content,
            style = PokemonCardTheme.typography.titleMedium,
        )
    }
}
