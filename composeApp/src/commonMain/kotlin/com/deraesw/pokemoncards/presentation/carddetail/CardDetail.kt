package com.deraesw.pokemoncards.presentation.carddetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.model.CardSet
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import org.koin.compose.koinInject

@Composable
fun CardSetDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: CardSetDetailViewModel = koinInject()
) {
    val uiState = viewModel.uiState.collectAsState()

    when (val state = uiState.value) {
        is CardSetDetailState.Success -> {
            CardSetDetailContent(
                set = state.cardSet,
                modifier = modifier
            )
        }

        else -> {}
    }
}

@Composable
fun CardSetDetailContent(
    set: CardSet,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(width = 182.dp, height = 96.dp)
                .align(Alignment.CenterVertically)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                CoilImage(
                    imageModel = { set.imageLogo },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center,
                    ),
                    loading = @Composable {
                        Box(modifier = Modifier.size(48.dp)) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    },
                )
            }
        }
        Column {
            Text(
                text = set.name
            )
            Text(
                text = set.series
            )
        }
    }
}
