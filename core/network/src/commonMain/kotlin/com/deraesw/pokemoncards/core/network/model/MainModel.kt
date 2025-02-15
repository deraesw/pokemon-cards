package com.deraesw.pokemoncards.core.network.model

import kotlinx.serialization.Serializable

@Serializable
internal data class ListDataModel<T>(
    val data: List<T>,
    val page: Int,
    val pageSize: Int,
    val count: Int,
    val totalCount: Int
)

@Serializable
internal data class ListSimpleModel<T>(
    val data: List<T>,
)
