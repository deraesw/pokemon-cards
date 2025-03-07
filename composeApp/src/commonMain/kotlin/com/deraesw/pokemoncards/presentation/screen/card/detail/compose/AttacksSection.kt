package com.deraesw.pokemoncards.presentation.screen.card.detail.compose

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.remember
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
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.icon_colorless_attack
import pokemoncards.composeapp.generated.resources.icon_darkness_attack
import pokemoncards.composeapp.generated.resources.icon_fairy_attack
import pokemoncards.composeapp.generated.resources.icon_fighting_attack
import pokemoncards.composeapp.generated.resources.icon_fire_attack
import pokemoncards.composeapp.generated.resources.icon_grass_attack
import pokemoncards.composeapp.generated.resources.icon_lightning_attack
import pokemoncards.composeapp.generated.resources.icon_psychic_attack
import pokemoncards.composeapp.generated.resources.icon_water_attack

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
    val attackIcon = remember { getAttackIcon(type) }
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
    ) {
        if (attackIcon != null) {
            Image(
                painter = painterResource(attackIcon),
                contentDescription = "",
                modifier = Modifier
                    .matchParentSize()
                    .align(Alignment.Center)
            )
        }
    }
}

private fun getAttackIcon(type: String): DrawableResource? {
    return when (type.uppercase()) {
        "COLORLESS" -> Res.drawable.icon_colorless_attack
        "DARKNESS" -> Res.drawable.icon_darkness_attack
        "FAIRY" -> Res.drawable.icon_fairy_attack
        "FIGHTING" -> Res.drawable.icon_fighting_attack
        "FIRE" -> Res.drawable.icon_fire_attack
        "GRASS" -> Res.drawable.icon_grass_attack
        "LIGHTNING" -> Res.drawable.icon_lightning_attack
        "PSYCHIC" -> Res.drawable.icon_psychic_attack
        "WATER" -> Res.drawable.icon_water_attack
        else -> null
    }
}
