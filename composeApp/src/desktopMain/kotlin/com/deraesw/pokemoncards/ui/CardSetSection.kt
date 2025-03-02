package com.deraesw.pokemoncards.ui

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.core.core.model.SortData
import com.deraesw.pokemoncards.presentation.compose.PcsSearchComponent
import com.deraesw.pokemoncards.presentation.compose.divider.PcsHorDivider
import com.deraesw.pokemoncards.presentation.screen.set.list.CardSetContent
import com.deraesw.pokemoncards.presentation.screen.set.list.CardSetViewModel
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import org.jetbrains.compose.resources.stringResource
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.search_card_set
import pokemoncards.composeapp.generated.resources.set_title
import pokemoncards.composeapp.generated.resources.sort_by_card_count
import pokemoncards.composeapp.generated.resources.sort_by_name
import pokemoncards.composeapp.generated.resources.sort_by_release_date

@Composable
fun CardSetSection(
    cardSetViewModel: CardSetViewModel,
    modifier: Modifier = Modifier,
    onCardSetClick: (String) -> Unit = {}
) {
    val uiState by cardSetViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
    ) {
        val state = rememberLazyListState()
        CardSetSectionHeader(
            sortData = uiState.sortData,
            onSelected = {
                cardSetViewModel.setSortData(it)
            },
            onSearchQueryChanged = {
                cardSetViewModel.updateSearchQuery(it)
            }
        )
        PcsHorDivider()
        CardSetContent(
            cardSetList = uiState.cardSetList,
            cardSetSelected = uiState.selectedCardSetId,
            listState = state,
            onCardSetClick = {
                cardSetViewModel.setSelectedCardSet(it)
                onCardSetClick(it)
            },
            scrollableContent = {
                VerticalScrollbar(
                    adapter = rememberScrollbarAdapter(state),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .fillMaxHeight()
                )
            },
            modifier = Modifier.padding(end = 10.dp)
        )
    }
}

@Composable
private fun CardSetSectionHeader(
    sortData: SortData,
    onSelected: (SortData) -> Unit = {},
    onSearchQueryChanged: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(Res.string.set_title),
                style = PokemonCardTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.width(8.dp))
            CardSetSort(
                sortData = sortData,
                onSelected = onSelected
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        PcsSearchComponent(
            placeholder = stringResource(Res.string.search_card_set),
            onQueryChange = onSearchQueryChanged,
            onClearQuery = {
                onSearchQueryChanged("")
            },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
private fun CardSetSort(
    sortData: SortData,
    onSelected: (SortData) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    val items = remember {
        SortData.entries.toList()
    }

    Column {
        OutlinedButton(
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = ColorPalette.Gray200,
                contentColor = Color.Black
            ),
            contentPadding = PaddingValues(start = 16.dp),
            onClick = { expanded = !expanded },
            modifier = Modifier
                .width(128.dp)
                .defaultMinSize(
                    minWidth = ButtonDefaults.MinWidth,
                    minHeight = 24.dp
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(sortData.toStringResource())
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(item.toStringResource())
                    },
                    onClick = {
                        expanded = false
                        onSelected(item)
                    }
                )
            }
        }
    }
}

@Composable
fun SortData.toStringResource(): String {
    return when (this) {
        SortData.NAME -> stringResource(Res.string.sort_by_name)
        SortData.RELEASE_DATE -> stringResource(Res.string.sort_by_release_date)
        SortData.CARD_COUNT -> stringResource(Res.string.sort_by_card_count)
    }
}
