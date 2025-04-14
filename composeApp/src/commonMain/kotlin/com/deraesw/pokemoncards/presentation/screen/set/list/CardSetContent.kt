package com.deraesw.pokemoncards.presentation.screen.set.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.compose.divider.PcsHorDivider
import com.deraesw.pokemoncards.presentation.model.CardSetListItem
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage

@Composable
fun CardSetContent(
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    onCardSetClick: (String) -> Unit = {},
    cardSetModelList: List<CardSetListItem> = listOf(),
    cardSetSelected: String? = null,
    scrollableContent: @Composable (BoxScope.() -> Unit) = {}
) {
    if (cardSetModelList.isEmpty()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.fillMaxSize()
        ) {
            Text("No card sets found")
        }
    } else {
        Box {
            scrollableContent()
            LazyColumn(
                state = listState,
                modifier = modifier
            ) {
                items(
                    items = cardSetModelList,
                    key = { cardSet -> cardSet.id }
                ) { cardSet ->
                    Row(
                        modifier = modifier
                            .height(IntrinsicSize.Min)
                    ) {
                        AnimatedVisibility(
                            visible = cardSetSelected == cardSet.id
                        ) {
                            SelectorIndicator()
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onCardSetClick(cardSet.id)
                                }
                                .drawBehind {
                                    if (cardSetSelected == cardSet.id) {
                                        drawRect(
                                            color = ColorPalette.Gray100,
                                        )
                                    }
                                }
                                .padding(16.dp)

                        ) {
                            SymbolImage(
                                imageSymbol = cardSet.imageSymbol
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            RowDetails(
                                cardSet = cardSet,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                    PcsHorDivider()
                }
            }

        }
    }
}

@Composable
private fun SelectorIndicator() {
    Spacer(
        modifier = Modifier
            .fillMaxHeight()
            .width(8.dp)
            .background(Color.Black)
    )
}

@Composable
private fun SymbolImage(
    imageSymbol: String?
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            ColorPalette.Gray100,
                            ColorPalette.Gray400,
                            ColorPalette.Gray100
                        )
                    )
                )
        )
        if (imageSymbol != null) {
            CoilImage(
                imageModel = { imageSymbol },
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
    }
}

@Composable
private fun RowDetails(
    cardSet: CardSetListItem,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = cardSet.name,
            style = PokemonCardTheme.typography.titleMedium,
        )

        Text(
            text = "${cardSet.series} - ${cardSet.total} cards",
            style = PokemonCardTheme.typography.labelMedium,
            color = ColorPalette.Gray600,
        )

        Text(
            text = cardSet.formatedReleaseDate,
            style = PokemonCardTheme.typography.labelMedium,
            color = ColorPalette.Gray600,
        )
    }
}
