package com.deraesw.pokemoncards.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.theme.ColorPalette

@Composable
fun PcsInfoBox(
    modifier: Modifier = Modifier,
    slot: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(ColorPalette.Gray100)
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        slot()
    }
}
