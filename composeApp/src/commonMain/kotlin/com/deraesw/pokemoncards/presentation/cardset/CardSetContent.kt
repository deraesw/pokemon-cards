package com.deraesw.pokemoncards.presentation.cardset

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
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

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(50f))
                        .background(Color.Red)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = cardSet.name,
                    style = PokemonCardTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )
            }
            HorizontalDivider(
                color = Color.LightGray,
                thickness = 1.dp
            )
        }
    }
}