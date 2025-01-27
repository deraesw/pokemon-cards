package com.deraesw.pokemoncards.presentation.compose.divider

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.theme.ColorPalette

@Composable
fun PcsHorDivider() {
    HorizontalDivider(
        color = ColorPalette.Gray100,
        thickness = 1.dp
    )
}
