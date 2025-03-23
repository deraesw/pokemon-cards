package com.deraesw.pokemoncards.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme

@Composable
fun PcsPill(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Unspecified,
    contentColor: Color = Color.Unspecified,
    icon: @Composable () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .clip(RoundedCornerShape(Constant.PERCENT_50))
            .background(backgroundColor)
            .padding(horizontal = 16.dp)
    ) {
        icon()
        Text(
            text = text,
            style = PokemonCardTheme.typography.bodyMedium,
            color = contentColor,
            fontWeight = FontWeight.SemiBold
        )
    }
}
