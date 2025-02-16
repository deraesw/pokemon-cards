package com.deraesw.pokemoncards.presentation.set.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.core.core.model.CardSet
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import org.jetbrains.compose.resources.stringResource
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.format_legality
import pokemoncards.composeapp.generated.resources.last_updated_at
import pokemoncards.composeapp.generated.resources.printed_cards
import pokemoncards.composeapp.generated.resources.release_date_label
import pokemoncards.composeapp.generated.resources.set_code
import pokemoncards.composeapp.generated.resources.total_cards

@Composable
fun CardSetDetailContent(
    set: CardSet,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        HeaderSection(
            set = set
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                MainSetInformationSection(
                    set = set,
                )
                Spacer(modifier = Modifier.size(8.dp))
                StatSection(
                    set = set
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(2.dp)
                    .background(ColorPalette.Gray200)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(168.dp)
            ) {
                Column {
                    Text(
                        text = stringResource(Res.string.format_legality),
                        style = PokemonCardTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun HeaderSection(
    set: CardSet,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(width = 182.dp, height = 96.dp)
//                    .align(Alignment.CenterVertically)
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
        Row {
            Text(
                text = stringResource(Res.string.last_updated_at),
                style = PokemonCardTheme.typography.labelSmall,
                color = ColorPalette.Gray500,
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = set.formatedUpdatedAt(),
                style = PokemonCardTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun MainSetInformationSection(
    set: CardSet,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = set.name,
            style = PokemonCardTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = set.series,
            style = PokemonCardTheme.typography.titleMedium,
            color = ColorPalette.Gray600,
        )
        Text(
            text = stringResource(Res.string.release_date_label, set.formatedReleaseDate),
            style = PokemonCardTheme.typography.titleSmall,
            color = ColorPalette.Gray600,
        )
    }
}

@Composable
fun StatSection(
    set: CardSet,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        TileInfo(
            title = stringResource(Res.string.total_cards),
            content = set.total.toString()
        )
        Spacer(modifier = Modifier.size(8.dp))
        TileInfo(
            title = stringResource(Res.string.printed_cards),
            content = set.printedTotal.toString()
        )
        Spacer(modifier = Modifier.size(8.dp))
        TileInfo(
            title = stringResource(Res.string.set_code),
            content = ""
        )
    }
}


@Composable
fun TileInfo(
    title: String,
    content: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = PokemonCardTheme.typography.labelMedium,
            color = ColorPalette.Gray500,
        )
        Text(
            text = content,
            style = PokemonCardTheme.typography.titleMedium,
//            color = ColorPalette.Gray600,
        )
    }
}
