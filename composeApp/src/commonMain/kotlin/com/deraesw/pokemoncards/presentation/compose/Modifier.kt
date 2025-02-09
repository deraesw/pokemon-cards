package com.deraesw.pokemoncards.presentation.compose

import androidx.compose.ui.Modifier

expect fun Modifier.customHoverEffect(onHover: (Boolean) -> Unit = {}): Modifier