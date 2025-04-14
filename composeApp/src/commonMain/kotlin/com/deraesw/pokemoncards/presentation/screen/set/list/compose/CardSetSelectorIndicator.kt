package com.deraesw.pokemoncards.presentation.screen.set.list.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CardSetSelectorIndicator() {
    Spacer(
        modifier = Modifier
            .fillMaxHeight()
            .width(8.dp)
            .background(Color.Black)
    )
}
