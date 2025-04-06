package com.deraesw.pokemoncards.presentation.screen.card.list.compose

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.compose.customHoverEffect
import com.deraesw.pokemoncards.presentation.compose.images.PcsImage
import com.deraesw.pokemoncards.presentation.model.CardListItem
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import com.deraesw.pokemoncards.presentation.theme.colorCardType
import com.deraesw.pokemoncards.presentation.theme.colorOverlayCardType

@Composable
fun CardListItemLarge(
    card: CardListItem,
    modifier: Modifier = Modifier,
    hovered: String = "",
    onCardClick: (String) -> Unit = {},
    onHover: (String) -> Unit = {}
) {
    val rotationYAnimation by animateFloatAsState(
        targetValue = if (hovered == card.id) 0f else 180f,
        label = "rotationY"
    )

    val rotationXAnimation by animateFloatAsState(
        targetValue = if (hovered == card.id) 0f else 50f,
        label = "rotationX"
    )

    val rotationZAnimation by animateFloatAsState(
        targetValue = if (hovered == card.id) 0f else 50f,
        label = "rotationZ"
    )

    Box(
        modifier = modifier
            .size(width = 180.dp, height = 240.dp)
            .border(2.dp, ColorPalette.Gray200, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(ColorPalette.Gray050)
            .clickable {
                onCardClick(card.id)
            }.drawBehind {
                drawRect(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color.White,
                            colorCardType(card.mainType),
                            colorOverlayCardType(card.mainType)
                        ),
                        center = Offset(0f, 0f),
                        radius = (this.size.height * 1.5).toFloat()
                    )
                )
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            if (card.imageSmall != null) {
                PcsImage(
                    imageUrl = card.imageSmall,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(8.dp)
                        .graphicsLayer {
                            rotationY = rotationYAnimation
                            rotationZ = rotationZAnimation
                            rotationX = rotationXAnimation
                        }
                        .customHoverEffect {
                            onHover(card.id)
                        }
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    .background(Color.White)
            ) {
                Text(
                    text = card.name,
                    style = PokemonCardTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 4.dp)
                        .weight(1.1f)
                        .basicMarquee()
                )
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
                        .fillMaxHeight()
                        .weight(0.9f)
                ) {
                    Text(
                        text = card.superType.type,
                        style = PokemonCardTheme.typography.titleSmall,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}
