package com.deraesw.pokemoncards.extension

import com.deraesw.pokemoncards.core.core.model.inline.CardTypeKey
import org.jetbrains.compose.resources.DrawableResource
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.icon_colorless_attack
import pokemoncards.composeapp.generated.resources.icon_darkness_attack
import pokemoncards.composeapp.generated.resources.icon_fairy_attack
import pokemoncards.composeapp.generated.resources.icon_fighting_attack
import pokemoncards.composeapp.generated.resources.icon_fire_attack
import pokemoncards.composeapp.generated.resources.icon_grass_attack
import pokemoncards.composeapp.generated.resources.icon_lightning_attack
import pokemoncards.composeapp.generated.resources.icon_psychic_attack
import pokemoncards.composeapp.generated.resources.icon_water_attack

fun CardTypeKey.toDrawableResource(): DrawableResource? {
    return when (key()) {
        "COLORLESS" -> Res.drawable.icon_colorless_attack
        "DARKNESS" -> Res.drawable.icon_darkness_attack
        "FAIRY" -> Res.drawable.icon_fairy_attack
        "FIGHTING" -> Res.drawable.icon_fighting_attack
        "FIRE" -> Res.drawable.icon_fire_attack
        "GRASS" -> Res.drawable.icon_grass_attack
        "LIGHTNING" -> Res.drawable.icon_lightning_attack
        "PSYCHIC" -> Res.drawable.icon_psychic_attack
        "WATER" -> Res.drawable.icon_water_attack
        else -> null
    }
}
