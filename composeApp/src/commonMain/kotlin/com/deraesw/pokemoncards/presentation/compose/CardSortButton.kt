package com.deraesw.pokemoncards.presentation.compose

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import com.deraesw.pokemoncards.core.core.model.SortCardData

@Composable
fun CardSortButton(
    sortCardData: SortCardData,
    size: DpSize,
    onSortSelect: (SortCardData) -> Unit,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(),
) {
    PcsSortButton(
        selected = sortData.first { it.key == sortCardData.name },
        values = sortData,
        onSelected = { key ->
            onSortSelect(SortCardData.valueOf(key))
        },
        colors = colors,
        size = size,
        modifier = modifier
    )
}

val sortData = listOf(
    Sort(SortCardData.CARD_NUMBER.name, "Card Number"),
    Sort(SortCardData.NAME.name, "Name"),
    Sort(SortCardData.HP.name, "Hp"),
)
