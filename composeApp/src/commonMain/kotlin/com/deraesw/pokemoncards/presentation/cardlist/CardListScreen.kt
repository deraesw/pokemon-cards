package com.deraesw.pokemoncards.presentation.cardlist

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.model.CardListItem
import com.deraesw.pokemoncards.presentation.compose.customHoverEffect
import com.deraesw.pokemoncards.presentation.compose.images.PcsImage
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme

@Composable
fun CardListScreen(
    modifier: Modifier = Modifier,
) {

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CardListContent(
    cards: List<CardListItem>,
    modifier: Modifier = Modifier
) {
    var active by remember { mutableStateOf("") }

    FlowRow(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        cards.forEach { card ->
            val rotationYAnimation by animateFloatAsState(
                targetValue = if (active == card.id) 0f else 180f,
                label = "rotationY"
            )

            val rotationXAnimation by animateFloatAsState(
                targetValue = if (active == card.id) 0f else 50f,
                label = "rotationX"
            )

            val rotationZAnimation by animateFloatAsState(
                targetValue = if (active == card.id) 0f else 50f,
                label = "rotationZ"
            )

            Box(
                modifier = Modifier
                    .background(ColorPalette.Gray050)
                    .size(width = 180.dp, height = 240.dp)
                    .border(2.dp, ColorPalette.Gray200, RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    if (card.imageSmall != null) {
                        PcsImage(
                            imageUrl = card.imageSmall,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .graphicsLayer {
                                    rotationY = rotationYAnimation
                                    rotationZ = rotationZAnimation
                                    rotationX = rotationXAnimation
                                }
                                .customHoverEffect {
                                    active = card.id
                                }
                        )
                    }
                    Text(
                        text = card.name,
                        style = PokemonCardTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}