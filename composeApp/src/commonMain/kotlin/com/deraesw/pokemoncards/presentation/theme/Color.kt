package com.deraesw.pokemoncards.presentation.theme

import androidx.compose.ui.graphics.Color

// Light Theme Colors
val LightPrimary = Color(0xFFFFCC00) // Pikachu Yellow
val LightSecondary = Color(0xFFEE1515) // Pokémon Red
val LightBackground = Color(0xFFF5F5F5) // Light Gray
val LightSurface = Color(0xFFFFFFFF) // White
val LightOnPrimary = Color(0xFF000000) // Black Text on Yellow
val LightOnSecondary = Color(0xFFFFFFFF) // White Text on Red

// Dark Theme Colors
val DarkPrimary = Color(0xFFFFCC00) // Pikachu Yellow (same for branding)
val DarkSecondary = Color(0xFFEE1515) // Pokémon Red (same for branding)
val DarkBackground = Color(0xFF121212) // Dark Gray
val DarkSurface = Color(0xFF1E1E1E) // Slightly Lighter Gray
val DarkOnPrimary = Color(0xFF000000) // Black Text on Yellow
val DarkOnSecondary = Color(0xFFFFFFFF) // White Text on Red

object ColorPalette {
    val Amber50 = Color(0xFFFFF8E1)
    val Amber100 = Color(0xFFFFECB3)
    val Amber200 = Color(0xFFFFE082)
    val Amber300 = Color(0xFFFFD54F)
    val Amber400 = Color(0xFFFFD740)
    val Amber500 = Color(0xFFFFC400)
    val Amber600 = Color(0xFFFFAB00)
    val Amber700 = Color(0xFFFF8A00)
    val Amber800 = Color(0xFFFF7000)
    val Amber900 = Color(0xFFFF5722)

    val Blue200 = Color(0xFF90CAF9)
    val Blue300 = Color(0xFF64B5F6)
    val Blue400 = Color(0xFF42A5F5)
    val Blue500 = Color(0xFF2196F3)
    val Blue600 = Color(0xFF1E88E5)
    val Blue700 = Color(0xFF1976D2)
    val Blue800 = Color(0xFF1565C0)
    val Blue900 = Color(0xFF01579B)

    val Brow400 = Color(0xFF8D6E63)
    val Brow600 = Color(0xFF6D4C41)
    val Brow800 = Color(0xFF4E342E)
    val Brow900 = Color(0xFF3E2723)

    val DeepPurple4005 = Color(0x887E57C2)
    val DeepPurple400 = Color(0xFF7E57C2)
    val DeepPurple800 = Color(0xFF4527A0)

    val GrimBlack = Color(0x44000000)

    val Gray050 = Color(0xFFFAFAFA)
    val Gray100 = Color(0xFFF5F5F5)
    val Gray200 = Color(0xFFEEEEEE)
    val Gray300 = Color(0xFFE0E0E0)
    val Gray400 = Color(0xFFBDBDBD)
    val Gray500 = Color(0xFF9E9E9E)
    val Gray600 = Color(0xFF757575)
    val Gray700 = Color(0xFF616161)
    val Gray800 = Color(0xFF424242)
    val Gray900 = Color(0xFF212121)

    val Green300 = Color(0xFF81C784)
    val Green700 = Color(0xFF388E3C)
    val Green900 = Color(0xFF1B5E20)

    val Indigo200 = Color(0xFF9FA8DA)
    val Indigo500 = Color(0xFF3F51B5)
    val Indigo600 = Color(0xFF303F9F)
    val Indigo700 = Color(0xFF283593)
    val Indigo800 = Color(0xFF283593)
    val Indigo900 = Color(0xFF1A237E)

    val Lime400 = Color(0xFFD4E157)
    val Lime800 = Color(0xFF9E9D24)

    val Orange600 = Color(0xFFFB8C00)
    val Orange900 = Color(0xFFE65100)

    val Pink200 = Color(0xFFF48FB1)
    val Pink400 = Color(0xFFEC407A)
    val Pink700 = Color(0xFFC2185B)
    val Pink900 = Color(0xFF880E4F)

    val Purple200 = Color(0xFFBB86FC)
    val Purple500 = Color(0xFF6200EE)
    val Purple600 = Color(0xFF5300A0)
    val Purple700 = Color(0xFF3700B3)
    val Purple800 = Color(0xFF30007D)
    val Purple900 = Color(0xFF311B92)
    val PurpleA100 = Color(0xFFEA80FC)

    val Red400 = Color(0xFFEF5350)
    val Red800 = Color(0xFFC62828)
    val Red900 = Color(0xFFBF360C)

    val Teal200 = Color(0xFF03DAC5)
    val Teal900 = Color(0xFF004D40)

    val Yellow400 = Color(0xFFFFEE58)
    val Yellow600 = Color(0xFFFDD835)
    val Yellow900 = Color(0xFFF57F17)
}

fun colorCardType(typeName: String): Color {
    return when (typeName.uppercase()) {
        "COLORLESS" -> ColorPalette.Gray200
        "DARKNESS" -> ColorPalette.Gray600
        "DRAGON" -> ColorPalette.Amber200
        "FIGHTING" -> ColorPalette.Orange600
        "FIRE" -> ColorPalette.Red400
        "GRASS" -> ColorPalette.Green300
        "LIGHTNING" -> ColorPalette.Yellow600
        "PSYCHIC" -> ColorPalette.Purple200
        "WATER" -> ColorPalette.Blue400


//        "POISON" -> ColorPalette.PurpleA100
//        "GROUND" -> ColorPalette.Orange600
//        "NORMAL" -> ColorPalette.Gray600
//        "BUG" -> ColorPalette.Green700
//        "FAIRY" -> ColorPalette.Pink200
//        "FLYING" -> ColorPalette.Indigo200
//        "ROCK" -> ColorPalette.Lime400
//        "ICE" -> ColorPalette.Blue200
//        "GHOST" -> ColorPalette.DeepPurple400
        else -> Color.White
    }
}

fun colorOverlayCardType(typeName: String): Color {
    return when (typeName.uppercase()) {
        "COLORLESS" -> ColorPalette.Gray600
        "DARKNESS" -> ColorPalette.Gray900
        "DRAGON" -> ColorPalette.Amber600
        "FIGHTING" -> ColorPalette.Orange900
        "FIRE" -> ColorPalette.Red800
        "GRASS" -> ColorPalette.Green700
        "LIGHTNING" -> ColorPalette.Yellow900
        "PSYCHIC" -> ColorPalette.Purple600
        "WATER" -> ColorPalette.Blue800


//        "POISON" -> ColorPalette.PurpleA100
//        "GROUND" -> ColorPalette.Orange600
//        "NORMAL" -> ColorPalette.Gray600
//        "BUG" -> ColorPalette.Green700
//        "FAIRY" -> ColorPalette.Pink200
//        "FLYING" -> ColorPalette.Indigo200
//        "ROCK" -> ColorPalette.Lime400
//        "ICE" -> ColorPalette.Blue200
//        "GHOST" -> ColorPalette.DeepPurple400
        else -> Color.White
    }
}
