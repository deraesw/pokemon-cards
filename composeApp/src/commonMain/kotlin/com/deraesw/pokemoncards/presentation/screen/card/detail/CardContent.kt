package com.deraesw.pokemoncards.presentation.screen.card.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.compose.CardTypeBox
import com.deraesw.pokemoncards.presentation.compose.PcsInfoBox
import com.deraesw.pokemoncards.presentation.compose.PcsPill
import com.deraesw.pokemoncards.presentation.compose.divider.PcsHorDivider
import com.deraesw.pokemoncards.presentation.compose.images.PcsImage
import com.deraesw.pokemoncards.presentation.model.CardDetail
import com.deraesw.pokemoncards.presentation.model.SuperType
import com.deraesw.pokemoncards.presentation.screen.card.detail.compose.AttacksSection
import com.deraesw.pokemoncards.presentation.screen.card.detail.compose.BattleStatsSection
import com.deraesw.pokemoncards.presentation.screen.card.detail.compose.CardDetailSection
import com.deraesw.pokemoncards.presentation.screen.card.detail.compose.FlavorBox
import com.deraesw.pokemoncards.presentation.screen.card.detail.compose.RulesBox
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import org.jetbrains.compose.resources.stringResource
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.pokemon_card_hp
import pokemoncards.composeapp.generated.resources.pokemon_card_number

@Composable
fun CardContent(
    cardDetail: CardDetail,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {},
    onRefresh: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .background(ColorPalette.GrimBlack)
            .fillMaxSize()
            .clickable(interactionSource = null, indication = null) {
                // No click
            }
    ) {
        Column(
            modifier = Modifier
                .padding(32.dp)
                .width(928.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .align(Alignment.Center)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Row {
                    Icon(
                        Icons.Default.Refresh,
                        contentDescription = "Refresh",
                        modifier = Modifier.clickable {
                            onRefresh()
                        }
                    )
                }
                Icon(
                    Icons.Default.Close,
                    contentDescription = "Close",
                    modifier = Modifier.clickable {
                        onDismiss()
                    }
                )
            }
            PcsHorDivider()
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,

                ) {
                CardImageSection(
                    imageUrl = cardDetail.imageLarge,
                    modifier = Modifier.width(464.dp).padding(start = 16.dp, end = 8.dp)
                )
                CardInformationSection(
                    cardDetail = cardDetail,
                    modifier = Modifier.width(464.dp)
                        .padding(start = 8.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                )
            }
        }
    }
}

@Composable
fun CardImageSection(
    imageUrl: String? = null,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
    ) {
        if (imageUrl != null) {
            PcsImage(
                imageUrl = imageUrl,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterEnd)
                    .padding(end = 8.dp)
            )
        } else {
            // TODO - Add placeholder
        }
    }
}

@Composable
fun CardInformationSection(
    cardDetail: CardDetail,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = cardDetail.name,
                    style = PokemonCardTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = stringResource(Res.string.pokemon_card_number, cardDetail.number),
                    style = PokemonCardTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = ColorPalette.Gray500
                )
            }

            if (cardDetail.isPokemon) {
                PokemonCardSubTitleSection(
                    hp = cardDetail.hp,
                    types = cardDetail.types
                )

                PcsHorDivider(modifier = Modifier.padding(vertical = 8.dp))
                AttacksSection(cardDetail.attacks)
                PcsHorDivider(modifier = Modifier.padding(vertical = 8.dp))
                BattleStatsSection(cardDetail = cardDetail)
                PcsHorDivider(modifier = Modifier.padding(vertical = 8.dp))
            } else {
                OtherSubTitleSection(
                    superType = cardDetail.superType,
                    subType = cardDetail.subTypes,
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                Column {
                    Text(
                        text = "Card rules",
                        style = PokemonCardTheme.typography.titleMedium,
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    cardDetail.rules.forEach {
                        PcsInfoBox(
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Text(
                                text = it,
                                style = PokemonCardTheme.typography.bodyMedium,
                                color = ColorPalette.Gray800,
                            )
                        }
                    }
                }
            }
            CardDetailSection(
                cardDetail = cardDetail,
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            if (cardDetail.isPokemon) {
                PcsHorDivider(modifier = Modifier.padding(vertical = 8.dp))
                if (cardDetail.flavorText.isNotEmpty()) {
                    FlavorBox(flavorText = cardDetail.flavorText)
                }

                if (cardDetail.pokemonRule.isNotEmpty()) {
                    RulesBox(rules = cardDetail.pokemonRule)
                }
            }
        }
    }
}

@Composable
private fun PokemonCardSubTitleSection(
    hp: String,
    types: List<String>,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        Text(
            text = stringResource(Res.string.pokemon_card_hp, hp),
            style = PokemonCardTheme.typography.headlineSmall,
            color = ColorPalette.Gray700,
        )

        types.forEach { type ->
            CardTypeBox(type = type)
        }
    }
}

@Composable
private fun OtherSubTitleSection(
    superType: SuperType,
    subType: String = "",
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        PcsPill(
            text = superType.type,
            backgroundColor = ColorPalette.Purple200,
            contentColor = ColorPalette.Purple800,
            icon = {
                Image(
                    Icons.Default.Person,
                    contentDescription = "Person icon",
                    modifier = Modifier.size(16.dp),
                    colorFilter = tint(ColorPalette.Purple800)
                )
            }
        )
        if (subType.isNotEmpty()) {
            PcsPill(
                text = subType,
                backgroundColor = ColorPalette.Blue200,
                contentColor = ColorPalette.Blue800,
                icon = {
                    Image(
                        Icons.Default.Build,
                        contentDescription = "Build icon",
                        modifier = Modifier.size(16.dp),
                        colorFilter = tint(ColorPalette.Blue800)
                    )
                }
            )
        }
    }
}
