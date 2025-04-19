package com.deraesw.pokemoncards.presentation.screen.set.detail.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme

@Composable
fun CardSetInfoBox(
    title: String,
    content: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(ColorPalette.Gray200)
            .padding(16.dp)
    ) {
        Column(
            modifier = modifier
        ) {
            Text(
                text = title,
                style = PokemonCardTheme.typography.labelMedium,
                color = ColorPalette.Gray500,
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = content,
                style = PokemonCardTheme.typography.titleLarge,
                modifier = Modifier.basicMarquee()
            )
        }
    }
}
