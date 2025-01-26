package com.deraesw.pokemoncards.presentation.cardset

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import org.koin.compose.koinInject

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CardSetContent(
    modifier: Modifier = Modifier,
    onCardSetClick: (String) -> Unit = {},
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
                    .clickable { onCardSetClick(cardSet.id) }
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(50f))
                        .border(1.dp, Color.LightGray, RoundedCornerShape(50f))
                ) {
                    CoilImage(
                        imageModel = { cardSet.imageSymbol },
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center,
                            requestSize = IntSize(32, 32)
                        ),
                        loading = @Composable {
                            Box(modifier = Modifier.matchParentSize()) {
                                CircularProgressIndicator(
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        },
                        modifier = Modifier.size(32.dp).align(Alignment.Center)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = cardSet.name,
                        style = PokemonCardTheme.typography.titleMedium,
//                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = cardSet.series,
                        style = PokemonCardTheme.typography.titleSmall,
//                        modifier = Modifier.weight(1f)
                    )
                }
            }
            HorizontalDivider(
                color = Color.LightGray,
                thickness = 1.dp
            )
        }
    }
}