package com.deraesw.pokemoncards.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import org.jetbrains.compose.resources.stringResource
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.description_search_icon
import pokemoncards.composeapp.generated.resources.no_result_found
import pokemoncards.composeapp.generated.resources.no_result_found_extra_info

@Composable
fun CardEmptySearch(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Image(
                Icons.Default.Search,
                contentDescription = stringResource(Res.string.description_search_icon),
                colorFilter = tint(ColorPalette.Gray600),
                modifier = Modifier
                    .size(64.dp)
                    .padding(bottom = 16.dp)
            )
            Text(
                text = stringResource(Res.string.no_result_found),
                style = PokemonCardTheme.typography.titleMedium,
            )
            Text(
                text = stringResource(Res.string.no_result_found_extra_info),
                style = PokemonCardTheme.typography.bodyMedium,
            )
        }
    }
}
