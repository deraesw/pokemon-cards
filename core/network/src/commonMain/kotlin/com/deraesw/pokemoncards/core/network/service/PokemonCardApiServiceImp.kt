package com.deraesw.pokemoncards.core.network.service

import com.deraesw.pokemoncards.core.core.model.Card
import com.deraesw.pokemoncards.core.core.model.CardSet
import com.deraesw.pokemoncards.core.core.util.Constant
import com.deraesw.pokemoncards.core.core.util.Logger
import com.deraesw.pokemoncards.core.network.client.NetworkClient
import com.deraesw.pokemoncards.core.network.mapper.NetworkToModel.toCardList
import com.deraesw.pokemoncards.core.network.mapper.NetworkToModel.toCardSetList
import com.deraesw.pokemoncards.core.network.model.ListDataModel
import com.deraesw.pokemoncards.core.network.model.ListSimpleModel
import com.deraesw.pokemoncards.core.network.model.NetworkCardData
import com.deraesw.pokemoncards.core.network.model.NetworkCardSet
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.headers

class PokemonCardApiServiceImp(
    private val networkClient: NetworkClient
) : PokemonCardApiService {
    private val baseUrl = "https://api.pokemontcg.io/v2"

    override suspend fun getAllSets(): List<CardSet> {
        Logger.debug("PokemonCardApiService", "getAllSets")
        return runCatching {
            val response = networkClient
                .client
                .getWithKey("$baseUrl/sets")
            val responseData = response.body<ListDataModel<NetworkCardSet>>()
            Logger.debug("PokemonCardApiService", "getAllSets response: $responseData")
            networkClient.client.close()
            responseData.data.toCardSetList()
        }.onFailure {
            Logger.error(
                "PokemonCardApiService",
                "Error while processing the request: ${it.message}",
                it
            )
        }.getOrDefault(listOf())
    }

    override suspend fun getSetCards(
        baseId: String
    ): List<Card> {
        Logger.debug("PokemonCardApiService", "getSetCards")
        return runCatching {
            val response = networkClient
                .client
                .getWithKey("$baseUrl/cards") {
                    url {
                        parameters.append("q", "set.id:$baseId")
                    }
                }
            val responseData = response.body<ListDataModel<NetworkCardData>>()
            Logger.debug("PokemonCardApiService", "getSetCards response: $responseData")
            networkClient.client.close()
            responseData.data.toCardList()
        }.onFailure {
            Logger.error(
                "PokemonCardApiService",
                "Error while processing the request: ${it.message}",
                it
            )
        }.getOrDefault(listOf())
    }

    override suspend fun getCardTypes(): List<String> {
        return runCatching {
            val response = networkClient
                .client
                .getWithKey("$baseUrl/types")
            val responseData = response.body<ListSimpleModel<String>>()
            networkClient.client.close()
            responseData.data
        }.onFailure {
            Logger.error(
                "PokemonCardApiService",
                "Error while processing the request: ${it.message}",
                it
            )
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
