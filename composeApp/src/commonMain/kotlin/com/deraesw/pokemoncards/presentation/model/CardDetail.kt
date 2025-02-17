package com.deraesw.pokemoncards.presentation.model

data class CardDetail(
    val id: String,
    val name: String,
    val number: String = "",
    val hp: String = "",
    val imageLarge: String? = null,
    val types: List<String> = emptyList()
)
