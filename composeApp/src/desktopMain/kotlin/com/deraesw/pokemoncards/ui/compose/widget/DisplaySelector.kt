package com.deraesw.pokemoncards.ui.compose.widget

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.data.DisplaySelectorData
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import org.jetbrains.compose.resources.vectorResource
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.window_24px

@Composable
fun DisplaySelector(
    onClick: () -> Unit = {},
    selector: DisplaySelectorData,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .size(width = 112.dp, height = 40.dp)
            .border(1.dp, ColorPalette.Gray400, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(4.dp)
            .clickable {
                onClick()
            }
    ) {
        val halfWith = remember { (maxWidth / 2).value }
        val offset by animateFloatAsState(
            targetValue = if (selector == DisplaySelectorData.Grid) 0f else halfWith
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    drawRoundRect(
                        topLeft = Offset(
                            x = offset,
                            y = 0f
                        ),
                        color = Color.Black,
                        cornerRadius = CornerRadius(8.dp.toPx()),
                        size = Size(56.dp.toPx(), 32.dp.toPx())
                    )
                }
        ) {
            Box(modifier = Modifier.weight(1f)) {
                Icon(
                    imageVector = vectorResource(Res.drawable.window_24px),
                    contentDescription = "Temp",
                    tint = ColorPalette.Gray500,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                Icon(
                    Icons.AutoMirrored.Filled.List,
                    contentDescription = "Temp",
                    tint = ColorPalette.Gray500,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
