package com.deraesw.pokemoncards.presentation.compose.divider

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.theme.ColorPalette

@Composable
fun PcsHorDivider(
    modifier: Modifier = Modifier
) {
    HorizontalDivider(
        color = ColorPalette.Gray100,
        thickness = 1.dp,
        modifier = modifier
    )
}
