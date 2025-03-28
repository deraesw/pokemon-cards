package com.deraesw.pokemoncards.presentation.model

data class CardListItem(
    val id: String,
    val name: String,
    val imageSmall: String? = null,
    val superType: SuperType = SuperType.POKEMON,
)
