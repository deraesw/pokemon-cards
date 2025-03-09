package com.deraesw.pokemoncards.presentation.screen.card.detail.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.model.CardDetail
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.battle_stats

@Composable
fun BattleStatsSection(
    cardDetail: CardDetail,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(Res.string.battle_stats),
            style = PokemonCardTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.size(8.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Weakness",
                    style = PokemonCardTheme.typography.labelLarge,
                    color = ColorPalette.Gray500,
                    modifier = Modifier
                )
                cardDetail.weaknesses.forEach {
                    val attackIcon = remember { getAttackIcon(it.typeKey) }
                    if (attackIcon != null) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Image(
                                painter = painterResource(attackIcon),
                                contentDescription = "icon attack of type ${it.typeKey}",
                                modifier = Modifier.size(28.dp)
                            )
                            Text(
                                text = it.value,
                                style = PokemonCardTheme.typography.titleLarge,
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Resistance",
                    style = PokemonCardTheme.typography.labelLarge,
                    color = ColorPalette.Gray500,
                )

                cardDetail.resistances.forEach {
                    val attackIcon = remember { getAttackIcon(it.typeKey) }
                    if (attackIcon != null) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Image(
                                painter = painterResource(attackIcon),
                                contentDescription = "icon attack of type ${it.typeKey}",
                                modifier = Modifier.size(28.dp)
                            )
                            Text(
                                text = it.value,
                                style = PokemonCardTheme.typography.titleLarge,
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Retreat cost",
                    style = PokemonCardTheme.typography.labelLarge,
                    color = ColorPalette.Gray500,
                )
            }
        }
    }
}
