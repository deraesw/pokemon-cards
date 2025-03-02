package com.deraesw.pokemoncards.presentation.screen.card.detail.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.core.core.model.CardAttacks
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import com.deraesw.pokemoncards.presentation.theme.colorCardType
import com.deraesw.pokemoncards.presentation.theme.colorOverlayCardType

@Composable
fun AttacksSection(
    attacks: List<CardAttacks>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Attaks",
            style = PokemonCardTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.size(8.dp))
        attacks.forEach { attack ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(ColorPalette.Gray100)
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                attack.cost.forEach {
                                    AttackTypeIcon(type = it.key())
                                }
                            }
                        }
                        Text(
                            attack.name,
                            style = PokemonCardTheme.typography.titleMedium,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(1f),
                        )
                        Text(
                            attack.damage,
                            style = PokemonCardTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End,
                            modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                        )
                    }
                    Text(
                        attack.description,
                        style = PokemonCardTheme.typography.titleSmall,
                        fontWeight = FontWeight.Thin,
                        color = ColorPalette.Gray800,
                    )
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}

@Composable
fun AttackTypeIcon(
    type: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(28.dp)
            .clip(RoundedCornerShape(50))
            .background(
                brush = Brush.radialGradient(
                    0.1f to colorCardType(type),
                    1.0f to colorOverlayCardType(type),
                    tileMode = TileMode.Repeated
                )
            )
    )
}
