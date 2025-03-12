package com.deraesw.pokemoncards.presentation.screen.card.detail.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.model.CardDetail
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme

@Composable
fun CardDetailSection(
    cardDetail: CardDetail,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Card detail",
            style = PokemonCardTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.size(8.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            TileInfo(
                title = "Rarity",
                content = cardDetail.rarity,
                modifier = Modifier.weight(1f)
            )
            TileInfo(
                title = "Artist",
                content = cardDetail.artist,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.size(4.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            TileInfo(
                title = "Sub-type",
                content = cardDetail.subTypes,
                modifier = Modifier.weight(1f)
            )
            if (cardDetail.evolvesFrom.isNotEmpty()) {
                TileInfo(
                    title = "Evolve from",
                    content = cardDetail.evolvesFrom,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
    }
}

@Composable
private fun TileInfo(
    title: String,
    content: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ) {
        Text(
            text = title,
            style = PokemonCardTheme.typography.labelLarge,
            color = ColorPalette.Gray500,
        )
        Text(
            text = content,
            style = PokemonCardTheme.typography.labelLarge,
        )
    }
}
