package com.deraesw.pokemoncards.presentation.model

// TODO because SuperType is from other module, need to adapt

data class CardListItem(
    val id: String,
    val name: String,
    val number: String,
    val imageSmall: String? = null,
    val imageLarge: String? = null,
    val mainType: String = "",
    val superType: SuperType = SuperType.POKEMON,
)
