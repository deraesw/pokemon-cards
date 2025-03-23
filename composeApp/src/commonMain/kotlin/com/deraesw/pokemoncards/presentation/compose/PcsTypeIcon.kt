package com.deraesw.pokemoncards.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.core.core.model.CardTypeKey
import com.deraesw.pokemoncards.extension.toDrawableResource
import com.deraesw.pokemoncards.presentation.theme.colorCardType
import com.deraesw.pokemoncards.presentation.theme.colorOverlayCardType
import org.jetbrains.compose.resources.painterResource

@Composable
fun PcsTypeIcon(
    type: CardTypeKey,
    modifier: Modifier = Modifier,
    size: Dp = 28.dp
) {
    val typeIcon = remember { type.toDrawableResource() }
    val colorCardType = remember { colorCardType(type.key()) }
    val colorOverlayCardType = remember { colorOverlayCardType(type.key()) }

    Box(
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(50))
            .background(
                brush = Brush.radialGradient(
                    0.1f to colorCardType,
                    1.0f to colorOverlayCardType,
                    tileMode = TileMode.Repeated
                )
            )
    ) {
        if (typeIcon != null) {
            Image(
                painter = painterResource(typeIcon),
                contentDescription = "icon type $type",
                modifier = Modifier
                    .matchParentSize()
                    .align(Alignment.Center)
            )
        }
    }
}
