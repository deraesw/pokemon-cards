package com.deraesw.pokemoncards.presentation.screen.set.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.core.core.model.CardSet
import com.deraesw.pokemoncards.presentation.screen.set.detail.compose.CardSetLogo
import com.deraesw.pokemoncards.presentation.screen.set.detail.compose.ExpendButton
import com.deraesw.pokemoncards.presentation.screen.set.detail.compose.MainSetInformationSection
import com.deraesw.pokemoncards.presentation.screen.set.detail.compose.StatSection
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import org.jetbrains.compose.resources.stringResource
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.format_legality
import pokemoncards.composeapp.generated.resources.last_updated_at

@Composable
fun CardSetDetailContent(
    set: CardSet,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(true) }
    Column(
        modifier = modifier
            .animateContentSize()
    ) {
        HeaderSection(
            set = set,
            expanded = { expanded },
            clickExpand = { expanded = !expanded }
        )
        AnimatedVisibility(
            visible = expanded,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    MainSetInformationSection(set = set)
                    Spacer(modifier = Modifier.size(8.dp))
                    StatSection(set = set)
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
}

@Composable
fun HeaderSection(
    set: CardSet,
    expanded: () -> Boolean,
    clickExpand: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        if (expanded()) {
            Box(
                modifier = Modifier
                    .size(width = 182.dp, height = 96.dp)
            ) {
                CardSetLogo(
                    logoUrl = set.imageLogo,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        } else {
            Column(
                modifier = modifier
            ) {
                Text(
                    text = set.name,
                    style = PokemonCardTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = set.series,
                    style = PokemonCardTheme.typography.titleSmall,
                    color = ColorPalette.Gray600,
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
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
            Spacer(modifier = Modifier.size(8.dp))
            ExpendButton(
                expanded = expanded,
                clickExpand = clickExpand
            )
        }
    }
}
