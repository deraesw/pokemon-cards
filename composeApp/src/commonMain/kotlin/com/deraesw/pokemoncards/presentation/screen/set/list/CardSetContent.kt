package com.deraesw.pokemoncards.presentation.screen.set.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.compose.divider.PcsHorDivider
import com.deraesw.pokemoncards.presentation.model.CardSetListItem
import com.deraesw.pokemoncards.presentation.screen.set.list.compose.CardSetRowDetails
import com.deraesw.pokemoncards.presentation.screen.set.list.compose.CardSetSelectorIndicator
import com.deraesw.pokemoncards.presentation.screen.set.list.compose.CardSetSymbolImage
import com.deraesw.pokemoncards.presentation.theme.ColorPalette

@Composable
fun CardSetContent(
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    onCardSetClick: (String) -> Unit = {},
    cardSetModelList: List<CardSetListItem> = listOf(),
    cardSetSelected: String? = null,
    selectorIndicator: Boolean = false,
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
                        if (selectorIndicator) {
                            AnimatedVisibility(
                                visible = cardSetSelected == cardSet.id
                            ) {
                                CardSetSelectorIndicator()
                            }
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onCardSetClick(cardSet.id)
                                }
                                .drawBehind {
                                    if (selectorIndicator && cardSetSelected == cardSet.id) {
                                        drawRect(
                                            color = ColorPalette.Gray100,
                                        )
                                    }
                                }
                                .padding(16.dp)

                        ) {
                            CardSetSymbolImage(
                                imageSymbol = cardSet.imageSymbol
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            CardSetRowDetails(
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
