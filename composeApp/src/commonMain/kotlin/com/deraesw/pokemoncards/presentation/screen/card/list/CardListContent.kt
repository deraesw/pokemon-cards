package com.deraesw.pokemoncards.presentation.screen.card.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.model.CardListItem
import com.deraesw.pokemoncards.presentation.screen.card.list.compose.CardItemForList

@Composable
fun CardListContent(
    cards: List<CardListItem>,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    onCardClick: (String) -> Unit = {},
    scrollIndicatorSlot: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier
    ) {
        scrollIndicatorSlot()
        LazyColumn(
            state = state,
        ) {
            items(
                items = cards,
                key = { card -> card.id }
            ) { card ->
                Spacer(modifier = Modifier.size(4.dp))
                CardItemForList(
                    cardListItem = card,
                    onCardClick = onCardClick
                )
                Spacer(modifier = Modifier.size(4.dp))
                HorizontalDivider()
            }
        }
    }
}
