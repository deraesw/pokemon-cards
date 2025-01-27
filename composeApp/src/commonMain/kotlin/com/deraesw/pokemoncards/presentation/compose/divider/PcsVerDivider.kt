package com.deraesw.pokemoncards.presentation.compose.divider

import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.theme.ColorPalette

@Composable
fun PcsVerDivider() {
    VerticalDivider(
        color = ColorPalette.Gray100,
        thickness = 1.dp
    )
}
