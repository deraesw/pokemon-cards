package com.deraesw.pokemoncards.presentation.screen.set.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.model.CardSetDetail
import com.deraesw.pokemoncards.presentation.screen.set.detail.compose.CardSetInfoBox
import com.deraesw.pokemoncards.presentation.screen.set.detail.compose.CardSetLogo
import com.deraesw.pokemoncards.presentation.screen.set.detail.compose.CardSetViewCardButton
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import org.jetbrains.compose.resources.stringResource
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.release_date
import pokemoncards.composeapp.generated.resources.total_cards

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardSetDetailContent(
    set: CardSetDetail,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
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
                },
                scrollBehavior = scrollBehavior
            )
        },
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
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
                            .border(
                                1.dp,
                                ColorPalette.Gray400,
//                                RoundedCornerShape(8.dp)
                            )
                    ) {
                        CardSetLogo(
                            logoUrl = set.imageLogo,
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                    PrimaryInfoRow(
                        total = set.total.toString(),
                        formatedReleaseDate = set.formatedReleaseDate
                    )
                    SeriesSectionRow(
                        series = set.series
                    )
                    LegaciesSectionRow()
                    Spacer(modifier = Modifier.size(8.dp))
                    FinalInfoRow(
                        totalPrinted = set.printedTotal.toString(),
                        code = ""
                    )
                }
            }
            BottomSection()
        }
    }
}

@Composable
private fun PrimaryInfoRow(
    total: String,
    formatedReleaseDate: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(84.dp)
    ) {
        CardSetInfoBox(
            title = stringResource(Res.string.total_cards),
            content = total,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.size(8.dp))
        CardSetInfoBox(
            title = stringResource(Res.string.release_date),
            content = formatedReleaseDate,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun FinalInfoRow(
    totalPrinted: String,
    code: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(84.dp)
    ) {
        CardSetInfoBox(
            title = "Printed total",
            content = totalPrinted,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.size(8.dp))
        CardSetInfoBox(
            title = "Ptcgo Code",
            content = code,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun SeriesSectionRow(
    series: String,
) {
    Column(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Series",
            style = PokemonCardTheme.typography.titleSmall,
            color = ColorPalette.Gray500,
        )
        Spacer(modifier = Modifier.size(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(ColorPalette.Gray200)
                .padding(16.dp)
        ) {
            Text(
                text = series,
                style = PokemonCardTheme.typography.titleLarge,
                modifier = Modifier.basicMarquee()
            )
        }
    }
}

@Composable
private fun LegaciesSectionRow() {
    Column(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Legacies",
            style = PokemonCardTheme.typography.titleSmall,
            color = ColorPalette.Gray500,
        )
        Spacer(modifier = Modifier.size(8.dp))
        LegaciesRow(
            text = "Standard",
            isLegal = true
        )
        Spacer(modifier = Modifier.size(8.dp))
        LegaciesRow(
            text = "Expanded ",
            isLegal = true
        )
        Spacer(modifier = Modifier.size(8.dp))
        LegaciesRow(
            text = "Unlimited ",
            isLegal = true
        )
    }
}

@Composable
private fun LegaciesRow(
    text: String,
    isLegal: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(ColorPalette.Gray200)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = text,
                style = PokemonCardTheme.typography.titleMedium,
                modifier = Modifier.basicMarquee()
            )

            Row {
                Icon(
                    Icons.Default.Check,
                    contentDescription = "Check icon",
                    tint = Color.White,
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(ColorPalette.Green700)
                )
            }
        }
    }
}

@Composable
private fun BottomSection(
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .height(72.dp)
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp)
    ) {
        CardSetViewCardButton(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
