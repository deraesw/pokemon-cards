package com.deraesw.pokemoncards.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.model.CardSet
import com.deraesw.pokemoncards.presentation.carddetail.CardSetDetailContent
import com.deraesw.pokemoncards.presentation.carddetail.CardSetDetailState
import com.deraesw.pokemoncards.presentation.carddetail.CardSetDetailViewModel
import com.deraesw.pokemoncards.presentation.cardset.CardSetContent
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.select_card_set

@Composable
fun MainScreen() {
    PokemonCardTheme {
        TwoPanelLayout()
    }
}

@Composable
fun TwoPanelLayout(
    setDetailViewModel: CardSetDetailViewModel = koinInject()
) {

    val uiState = setDetailViewModel.uiState.collectAsState()



    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        CardSetContent(
            modifier = Modifier.width(256.dp),
            onCardSetClick = {
                println("CardSetClick $it")
                setDetailViewModel.getCardSet(it)
            }
        )
        VerticalDivider()
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AnimatedContent(
                targetState = uiState.value is CardSetDetailState.Success,
                modifier = Modifier.fillMaxSize()
            ) {
                when (val state = uiState.value) {
                    is CardSetDetailState.Success -> {
                        DetailSection(
                            cardSet = state.cardSet,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    else -> {
                        Text(
                            text = stringResource(Res.string.select_card_set),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DetailSection(
    cardSet: CardSet,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        CardSetDetailContent(
            set = cardSet,
        )
        HorizontalDivider(
            thickness = 1.dp,
        )
    }
}