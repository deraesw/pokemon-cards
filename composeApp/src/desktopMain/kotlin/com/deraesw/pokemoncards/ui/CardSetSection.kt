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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.carddetail.CardSetDetailViewModel
import com.deraesw.pokemoncards.presentation.cardset.CardSetContent
import com.deraesw.pokemoncards.presentation.compose.divider.PcsHorDivider
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import org.jetbrains.compose.resources.stringResource
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.set_title

@Composable
fun CardSetSection(
    setDetailViewModel: CardSetDetailViewModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        val state = rememberLazyListState()
        CardSetSectionHeader()
        PcsHorDivider()
        CardSetContent(
            listState = state,
            onCardSetClick = {
                setDetailViewModel.getCardSet(it)
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
private fun CardSetSectionHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(Res.string.set_title),
                style = PokemonCardTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.width(8.dp))
            CardSetSort()
        }
    }
}

@Composable
private fun CardSetSort() {
    var expanded by remember { mutableStateOf(false) }
    var itemText by remember { mutableStateOf("Name") }
    val items = listOf("Name", "Release Date")

    Column {
        OutlinedButton(
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = ColorPalette.Gray200,
                contentColor = Color.Black
            ),
            contentPadding = PaddingValues(horizontal = 16.dp),
            onClick = { expanded = !expanded },
            modifier = Modifier
                .width(128.dp)
                .defaultMinSize(
                    minWidth = ButtonDefaults.MinWidth,
                    minHeight = 24.dp
                )
        ) {
            Row {
                Text("Sort by: $itemText")
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
                        Text(item)
                    },
                    onClick = {
                        expanded = false
                        itemText = item
                    }
                )
            }
        }
    }
}
