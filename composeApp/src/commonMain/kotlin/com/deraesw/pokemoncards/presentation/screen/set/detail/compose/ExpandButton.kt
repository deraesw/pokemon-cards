package com.deraesw.pokemoncards.presentation.screen.set.detail.compose

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.theme.ColorPalette

@Composable
fun ExpendButton(
    expanded: () -> Boolean,
    clickExpand: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val arrowRotationDegree by animateFloatAsState(
        targetValue = if (expanded()) 180f else 0f,
    )
    Box(
        modifier = modifier
            .size(32.dp)
            .clip(RoundedCornerShape(50f))
            .clickable {
                clickExpand()
            }
    ) {
        Icon(
            Icons.Default.KeyboardArrowUp,
            contentDescription = "Expand button",
            tint = ColorPalette.Gray500,
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer {
                    rotationZ = arrowRotationDegree
                }
        )
    }
}
