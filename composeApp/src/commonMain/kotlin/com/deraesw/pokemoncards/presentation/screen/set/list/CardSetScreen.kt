package com.deraesw.pokemoncards.presentation.screen.set.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.compose.PcsSearchComponent
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.search_card_set

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardSetScreen(
    onCardSetClick: (String) -> Unit = {},
    viewModel: CardSetViewModel = koinInject(),
) {
    val uiState = viewModel.uiState.collectAsState().value

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
            ) {
                Column(
                    modifier = Modifier
                        .windowInsetsPadding(WindowInsets.statusBars)
                        .clipToBounds()
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "Pokemon Cards",
                            style = PokemonCardTheme.typography.titleLarge,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    PcsSearchComponent(
                        placeholder = stringResource(Res.string.search_card_set),
                        onQueryChange = {
                            viewModel.updateSearchQuery(it)
                        },
                        onClearQuery = {
                            viewModel.updateSearchQuery("")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

        }
    ) { paddingValue ->
        Box(modifier = Modifier.padding(paddingValue)) {
            CardSetContent(
                cardSetModelList = uiState.cardSetModelList,
                cardSetSelected = uiState.selectedCardSetId,
                selectorIndicator = false,
                onCardSetClick = {
                    viewModel.setSelectedCardSet(it)
                    onCardSetClick(it)
                }
            )
        }
    }
}
