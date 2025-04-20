package com.deraesw.pokemoncards.presentation.screen.card.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.compose.CardSynchronization
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListScreen(
    cardSetId: String,
    onNavigateBack: () -> Unit,
    onNavigateToCardDetail: (String) -> Unit = {},
    modifier: Modifier = Modifier,
    cardListViewModel: CardListViewModel = koinInject(
        parameters = { parametersOf(cardSetId) }
    ),
) {
    val uiState by cardListViewModel.uiState.collectAsState()
    val loadingState by cardListViewModel.loadingState.collectAsState()
    val syncState by cardListViewModel.syncState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = ""
                        )
                    }
                },
                title = {
                    Text(text = "Pokemon Cards")
                },
            )
        }
    ) { innerPadding ->
        Box(
            modifier = modifier.padding(innerPadding).padding(16.dp),
        ) {
            AnimatedVisibility(
                visible = loadingState,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        CircularProgressIndicator()
                        Text(
                            text = "Loading...",
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }

            CardGridContent(
                columns = GridCells.Fixed(2),
                cards = uiState.cardList,
                spacingBetweenItems = 8.dp,
                hoveredEnabled = false,
                onCardClick = onNavigateToCardDetail
            )

            AnimatedVisibility(
                visible = syncState.isSyncInProgress,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                Box(
                    modifier = Modifier
                        .background(ColorPalette.Gray200)
                        .fillMaxSize()
                ) {
                    CardSynchronization(
                        syncType = syncState.syncType,
                        message = syncState.message,
                        onDismiss = cardListViewModel::dismissSync,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                            .padding(32.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}
