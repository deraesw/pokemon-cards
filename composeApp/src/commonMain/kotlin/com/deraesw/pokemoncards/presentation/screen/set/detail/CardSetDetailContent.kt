package com.deraesw.pokemoncards.presentation.screen.set.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.model.CardSetDetail
import com.deraesw.pokemoncards.presentation.screen.set.detail.compose.CardSetLogo
import com.deraesw.pokemoncards.presentation.screen.set.detail.compose.TileInfo
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import org.jetbrains.compose.resources.stringResource
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.total_cards

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardSetDetailContent(
    set: CardSetDetail,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            MediumTopAppBar(
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
                    Text(text = set.name)
                }
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .background(ColorPalette.Gray200)
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(width = 182.dp, height = 112.dp)
                    ) {
                        CardSetLogo(
                            logoUrl = set.imageLogo,
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(ColorPalette.Gray200)
                        ) {
                            TileInfo(
                                title = stringResource(Res.string.total_cards),
                                content = set.total.toString(),
                                modifier = Modifier.padding(
                                    horizontal = 8.dp,
                                    vertical = 16.dp
                                )
                            )
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(ColorPalette.Gray200)
                                .padding(end = 8.dp)
                        ) {

                            TileInfo(
                                title = stringResource(Res.string.total_cards),
                                content = set.formatedReleaseDate,
                                modifier = Modifier.padding(
                                    horizontal = 8.dp,
                                    vertical = 16.dp
                                )
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .background(Color.Red)
            ) { }
        }
    }
}
