package com.deraesw.pokemoncards.ui.compose.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.core.core.model.SortData
import com.deraesw.pokemoncards.presentation.compose.PcsSearchComponent
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import com.deraesw.pokemoncards.ui.compose.widget.CardSetSort
import org.jetbrains.compose.resources.stringResource
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.search_card_set
import pokemoncards.composeapp.generated.resources.set_title

@Composable
fun CardSetSectionHeader(
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
