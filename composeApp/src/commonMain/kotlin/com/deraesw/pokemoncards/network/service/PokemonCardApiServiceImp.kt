package com.deraesw.pokemoncards.network.service

import com.deraesw.pokemoncards.network.Constant
import com.deraesw.pokemoncards.network.model.CardSet
import com.deraesw.pokemoncards.network.model.ListDataModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.headers

class PokemonCardApiServiceImp(
    private val client: HttpClient
) : PokemonCardApiService {
    private val baseUrl = "https://api.pokemontcg.io/v2"

    override suspend fun getAllSets(): List<CardSet> {
        return runCatching {
            val response = client.getWithKey("$baseUrl/sets")
            val responseData = response.body<ListDataModel<CardSet>>()
            responseData.data
        }.onFailure {
            println("Error while processing the request: ${it.message}")
        }.getOrDefault(listOf())
    }

    private suspend fun HttpClient.getWithKey(path: String) = get(path) {
        headers {
            append("X-Api-Key", Constant.apiKey)
        }
    }
}
