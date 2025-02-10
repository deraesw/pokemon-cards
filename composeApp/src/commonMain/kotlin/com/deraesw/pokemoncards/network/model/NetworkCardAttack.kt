package com.deraesw.pokemoncards.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkCardAttack(
    val name: String,
    val cost: List<String>,
    val convertedEnergyCost: Int,
    val damage: String? = null,
    val text: String? = null
)
