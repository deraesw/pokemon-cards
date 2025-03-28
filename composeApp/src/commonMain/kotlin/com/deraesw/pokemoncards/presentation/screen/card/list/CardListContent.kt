package com.deraesw.pokemoncards.presentation.screen.card.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.model.CardListItem
import com.deraesw.pokemoncards.presentation.screen.card.list.compose.CardListItemLarge

@Composable
fun CardListContent(
    cards: List<CardListItem>,
    modifier: Modifier = Modifier,
    state: LazyGridState = rememberLazyGridState(),
    onCardClick: (String) -> Unit = {},
    scrollIndicatorSlot: @Composable () -> Unit = {}
) {
    var active by remember { mutableStateOf("") }

    Box {
        scrollIndicatorSlot()
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 180.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = state,
            modifier = modifier
        ) {
            items(
                items = cards,
                key = { card -> card.id }
            ) { card ->
                CardListItemLarge(
                    card = card,
                    hovered = active,
                    onHover = { active = it },
                    onCardClick = onCardClick,
                )
            }
        }
    }
}
