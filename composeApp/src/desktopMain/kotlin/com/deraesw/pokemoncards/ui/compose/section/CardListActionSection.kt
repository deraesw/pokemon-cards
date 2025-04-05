package com.deraesw.pokemoncards.ui.compose.section

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.core.core.model.SortCardData
import com.deraesw.pokemoncards.data.DisplaySelectorData
import com.deraesw.pokemoncards.presentation.compose.CardSortButton
import com.deraesw.pokemoncards.presentation.compose.PcsSearchComponent
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.ui.compose.widget.DisplaySelector

@Composable
fun CardListActionSection(
    sortCardData: SortCardData,
    displaySelector: DisplaySelectorData,
    onClickReSync: () -> Unit,
    onSortSelect: (SortCardData) -> Unit,
    onClearQuery: () -> Unit,
    onQueryChange: (String) -> Unit,
    onClickDisplaySelector: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxSize()
    ) {
        PcsSearchComponent(
            onClearQuery = onClearQuery,
            onQueryChange = onQueryChange,
            placeholder = "Search card list on this set...",
            background = Color.White,
            modifier = Modifier.width(256.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            DisplaySelector(
                selector = displaySelector,
                onClick = onClickDisplaySelector
            )
            CardSortButton(
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                size = DpSize(128.dp, 32.dp),
                sortCardData = sortCardData,
                onSortSelect = onSortSelect
            )

            Box(
                modifier = Modifier
                    .border(1.dp, ColorPalette.Gray400, RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .clickable {
                        onClickReSync()
                    }
            ) {
                Icon(
                    Icons.Default.AccountBox,
                    contentDescription = "Temp",
                    tint = ColorPalette.Gray500,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
