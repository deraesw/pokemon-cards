package com.deraesw.pokemoncards.presentation.screen.set.list.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.compose.images.PcsImage
import com.deraesw.pokemoncards.presentation.theme.ColorPalette

@Composable
fun CardSetSymbolImage(
    imageSymbol: String?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    drawRoundRect(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                ColorPalette.Gray100,
                                ColorPalette.Gray400,
                                ColorPalette.Gray100
                            )
                        ),
                        cornerRadius = CornerRadius(8.dp.toPx())
                    )
                }
        )
        if (imageSymbol != null) {
            PcsImage(
                imageUrl = imageSymbol,
                requestSize = IntSize(32, 32),
                modifier = Modifier.size(32.dp).align(Alignment.Center)
            )
        }
    }
}
