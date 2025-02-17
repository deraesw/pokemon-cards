package com.deraesw.pokemoncards.presentation.card.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import com.deraesw.pokemoncards.presentation.compose.images.PcsImage
import com.deraesw.pokemoncards.presentation.model.CardDetail
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
            .fillMaxSize()
            .border(2.dp, Color.Red)
            .padding(16.dp)
            .background(Color.White)
            .clip(RoundedCornerShape(8.dp))
            .border(2.dp, Color.Blue)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    onDismiss()
                }
        ) {
            Icon(
                Icons.Default.Close,
                contentDescription = "Close",
//                modifier = Modifier.padding(8.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CardImageSection(
                imageUrl = cardDetail.imageLarge,
                modifier = Modifier.weight(1f)
            )
            CardInformationSection(
                cardDetail = cardDetail,
                modifier = Modifier.weight(1f)
            )
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
    ) {
        if (imageUrl != null) {
            PcsImage(
                imageUrl = imageUrl,
                contentScale = ContentScale.FillHeight,
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
        modifier = modifier.fillMaxSize()
    ) {
        Column {
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
                    text = stringResource(Res.string.pokemon_card_hp, "90"),
                    style = PokemonCardTheme.typography.headlineMedium,
                    color = ColorPalette.Gray700
                )

                cardDetail.types.forEach {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(ColorPalette.Gray200)
                    ) {
                        Text(
                            text = it,
                            style = PokemonCardTheme.typography.bodyMedium,
                            modifier = Modifier.padding(horizontal = 16.dp)
//                            color = ColorPalette.Gray700
                        )
                    }
                }
            }
        }
    }
}
