package com.deraesw.pokemoncards.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import com.deraesw.pokemoncards.presentation.theme.colorCardType

@Composable
fun CardTypeBox(
    type: String,
    modifier: Modifier = Modifier,
) {
    val color = remember { colorCardType(type) }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(Constant.PERCENT_50))
            .background(color)
    ) {
        Text(
            text = type,
            style = PokemonCardTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 16.dp),
            fontWeight = FontWeight.SemiBold
        )
    }
}
