package com.deraesw.pokemoncards.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier

expect fun Modifier.customHoverEffect(onHover: (Boolean) -> Unit = {}): Modifier

fun Modifier.deactivateClick(): Modifier = this.clickable(
    interactionSource = null,
    indication = null,
    onClick = {}
)
