package com.deraesw.pokemoncards.presentation.screen.card.list.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.compose.images.PcsImage
import com.deraesw.pokemoncards.presentation.model.CardListItem
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import com.deraesw.pokemoncards.presentation.theme.colorCardType
import com.deraesw.pokemoncards.presentation.theme.colorOverlayCardType

@Composable
fun CardItemForList(
    cardListItem: CardListItem,
    modifier: Modifier = Modifier,
    onCardClick: (String) -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onCardClick(cardListItem.id)
            }
            .drawBehind {
                drawRect(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.White,
                            colorCardType(cardListItem.mainType),
                            colorOverlayCardType(cardListItem.mainType)
                        ),
//                        startX = (size.width / 3)
                    )
                )
            }
    ) {
        Row {
            CardImage(
                imageUrl = cardListItem.imageSmall,
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = cardListItem.name,
                    style = PokemonCardTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Box(
            modifier = Modifier
                .clip(CutCornerShape(topStart = 16.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            ColorPalette.Gray400,
                            ColorPalette.Gray100,
                            ColorPalette.Gray400
                        )
                    )
                )
                .align(Alignment.BottomEnd)
        ) {
            Text(
                text = cardListItem.superType.type,
                style = PokemonCardTheme.typography.titleSmall,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun CardImage(
    imageUrl: String?,
) {
    Box(
        modifier = Modifier
            .size(width = 96.dp, height = 96.dp)
    ) {
        if (imageUrl != null) {
            PcsImage(
                imageUrl = imageUrl,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        } else {

        }
    }
}
