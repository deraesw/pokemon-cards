package com.deraesw.pokemoncards.model

data class CardDetail(
    val id: String,
    val name: String,
    val superType: String?,
    val level: String? = null,
    val hp: String? = null,
    // Todo to remove
    val imageSymbol: String? = null,
    val imageLogo: String? = null,
    val imageSmall: String? = null,
    val imageLarge: String? = null,
)
