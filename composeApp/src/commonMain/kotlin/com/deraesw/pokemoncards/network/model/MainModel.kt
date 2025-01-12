package com.deraesw.pokemoncards.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ListDataModel<T>(
    val data: List<T>,
    val page: Int,
    val pageSize: Int,
    val count: Int,
    val totalCount: Int
)
