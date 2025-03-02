package com.deraesw.pokemoncards.presentation.screen.card.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.compose.CardTypeBox
import com.deraesw.pokemoncards.presentation.compose.divider.PcsHorDivider
import com.deraesw.pokemoncards.presentation.compose.images.PcsImage
import com.deraesw.pokemoncards.presentation.model.CardDetail
import com.deraesw.pokemoncards.presentation.screen.card.detail.compose.AttacksSection
import com.deraesw.pokemoncards.presentation.screen.card.detail.compose.CardDetailSection
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import org.jetbrains.compose.resources.stringResource
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.pokemon_card_hp
import pokemoncards.composeapp.generated.resources.pokemon_card_number

@Composable
fun CardContent(
    cardDetail: CardDetail,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .background(ColorPalette.GrimBlack)
            .fillMaxSize()
            .clickable(interactionSource = null, indication = null) {
                // No click
            }
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
            ) {
                CardImageSection(
                    imageUrl = cardDetail.imageLarge,
                    modifier = Modifier.width(464.dp).padding(start = 16.dp, end = 8.dp)
                )
                CardInformationSection(
                    cardDetail = cardDetail,
                    modifier = Modifier.width(464.dp).padding(start = 8.dp, end = 16.dp)
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .clickable {
                        onDismiss()
                    }
            ) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "Close",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }

}

@Composable
fun CardImageSection(
    imageUrl: String? = null,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
    ) {
        if (imageUrl != null) {
            PcsImage(
                imageUrl = imageUrl,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterEnd)
                    .padding(end = 8.dp)
            )
        } else {
            // TODO - Add placeholder
        }
    }
}

@Composable
fun CardInformationSection(
    cardDetail: CardDetail,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxHeight(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = cardDetail.name,
                    style = PokemonCardTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = stringResource(Res.string.pokemon_card_number, cardDetail.number),
                    style = PokemonCardTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = ColorPalette.Gray500
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = stringResource(Res.string.pokemon_card_hp, cardDetail.hp),
                    style = PokemonCardTheme.typography.headlineSmall,
                    color = ColorPalette.Gray700,
                )

                cardDetail.types.forEach { type ->
                    CardTypeBox(type = type)
                }
            }
            if (cardDetail.attacks.isNotEmpty()) {
                PcsHorDivider(modifier = Modifier.padding(vertical = 8.dp))
                AttacksSection(cardDetail.attacks)
            }
            PcsHorDivider(modifier = Modifier.padding(vertical = 8.dp))
            BattleStatsSection()
            PcsHorDivider(modifier = Modifier.padding(vertical = 8.dp))
            CardDetailSection(
                cardDetail = cardDetail,
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            PcsHorDivider(modifier = Modifier.padding(vertical = 8.dp))
            if (cardDetail.flavorText.isNotEmpty()) {
                Row {
                    Icon(
                        Icons.Default.Info,
                        contentDescription = "info",
                        tint = ColorPalette.Blue500,
                    )
                    Text(
                        text = cardDetail.flavorText,
                        style = PokemonCardTheme.typography.labelLarge,
                    )
                }
            }
        }
    }
}

@Composable
fun BattleStatsSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Battle stats",
            style = PokemonCardTheme.typography.titleMedium,
//            color = ColorPalette.Gray700,
        )
    }
}

