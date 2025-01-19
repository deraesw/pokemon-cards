package com.deraesw.pokemoncards.network.service

import com.deraesw.pokemoncards.network.Constant
import com.deraesw.pokemoncards.network.NetworkClient
import com.deraesw.pokemoncards.network.model.CardDataModel
import com.deraesw.pokemoncards.network.model.ListDataModel
import com.deraesw.pokemoncards.network.model.NetworkCardSet
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.headers

class PokemonCardApiServiceImp(
    private val networkClient: NetworkClient
) : PokemonCardApiService {
    private val baseUrl = "https://api.pokemontcg.io/v2"

    override suspend fun getAllSets(): List<NetworkCardSet> {
        println("getAllSets")
        return runCatching {
            println("getAllSets runCatching")
            val response = networkClient
                .client
                .getWithKey("$baseUrl/sets")
            val responseData = response.body<ListDataModel<NetworkCardSet>>()
            println("getAllSets runCatching 1")
            networkClient.client.close()
            println("getAllSets runCatching 2")
            responseData.data
        }.onFailure {
            println("Error while processing the request: ${it.message}")
        }.getOrDefault(listOf())
    }

    override suspend fun getSetCards(
        baseId: String
    ): List<CardDataModel> {
        return runCatching {
            val response = networkClient
                .client
                .getWithKey("$baseUrl/cards") {
                    url {
                        parameters.append("q", "set.id:$baseId")
                    }
                }
            val responseData = response.body<ListDataModel<CardDataModel>>()
            networkClient.client.close()
            responseData.data
        }.onFailure {
            println("Error while processing the request: ${it.message}")
        }.getOrDefault(listOf())
    }

    private suspend fun HttpClient.getWithKey(
        path: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ) = get(path) {
        headers {
            append("X-Api-Key", Constant.apiKey)
        }
        block()
    }
}
