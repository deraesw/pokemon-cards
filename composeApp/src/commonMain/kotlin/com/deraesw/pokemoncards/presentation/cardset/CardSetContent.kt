package com.deraesw.pokemoncards.presentation.cardset

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CardSetContent(
    modifier: Modifier = Modifier,
    viewModel: CardSetViewModel = koinInject()
) {
    val state = viewModel.uiState.collectAsState().value

    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = state.cardSetList,
            key = { cardSet -> cardSet.id }
        ) { cardSet ->
            Card(
//                backgroundColor = Color.Red,
                modifier = Modifier
//                    .height(58.dp)
                    .width(128.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text("Card : ${cardSet.name}")
            }
            Divider(
                color = Color.Black,
                thickness = 1.dp
            )
        }
    }
}