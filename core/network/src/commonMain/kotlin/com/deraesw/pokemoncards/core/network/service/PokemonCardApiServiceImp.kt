package com.deraesw.pokemoncards.core.network.service

import com.deraesw.pokemoncards.core.core.model.Card
import com.deraesw.pokemoncards.core.core.model.CardSetModel
import com.deraesw.pokemoncards.core.core.util.Constant
import com.deraesw.pokemoncards.core.core.util.Logger
import com.deraesw.pokemoncards.core.network.client.NetworkClient
import com.deraesw.pokemoncards.core.network.mapper.NetworkToModel.toCard
import com.deraesw.pokemoncards.core.network.mapper.NetworkToModel.toCardList
import com.deraesw.pokemoncards.core.network.mapper.NetworkToModel.toCardSetList
import com.deraesw.pokemoncards.core.network.model.ListDataModel
import com.deraesw.pokemoncards.core.network.model.ListSimpleModel
import com.deraesw.pokemoncards.core.network.model.NetworkCardData
import com.deraesw.pokemoncards.core.network.model.NetworkCardSet
import com.deraesw.pokemoncards.core.network.model.SimpleModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.headers

class PokemonCardApiServiceImp(
    private val networkClient: NetworkClient
) : PokemonCardApiService {
    private val tag = "PokemonCardApiService"
    private val baseUrl = "https://api.pokemontcg.io/v2"

    override suspend fun getAllSets(): List<CardSetModel> {
        Logger.debug(tag, "getAllSets")
        return runCatching {
            val response = networkClient
                .client
                .getWithKey("$baseUrl/sets")
            val responseData = response.body<ListDataModel<NetworkCardSet>>()
            Logger.debug(tag, "getAllSets response: $responseData")
            responseData.data.toCardSetList()
        }.onFailure {
            logError(it.message ?: "", it)
        }.getOrDefault(listOf())
    }

    override suspend fun getSetCards(
        baseId: String
    ): List<Card> {
        Logger.debug(tag, "getSetCards")
        return runCatching {
            val response = networkClient
                .client
                .getWithKey("$baseUrl/cards") {
                    url {
                        parameters.append("q", "set.id:$baseId")
                    }
                }
            val responseData = response.body<ListDataModel<NetworkCardData>>()
            Logger.debug(tag, "getSetCards response: $responseData")
            responseData.data.toCardList()
        }.onFailure {
            logError(it.message ?: "", it)
        }.getOrDefault(listOf())
    }

    override suspend fun getCards(
        cardId: String
    ): Card? {
        Logger.debug(tag, "getCards")
        return runCatching {
            val response = networkClient
                .client
                .getWithKey("$baseUrl/cards/$cardId")
            val responseData = response.body<SimpleModel<NetworkCardData>>()
            Logger.debug(tag, "getSetCards response: $responseData")
            responseData.data.toCard()
        }.onFailure {
            logError(it.message ?: "", it)
        }.getOrNull()
    }

    override suspend fun getCardTypes(): List<String> {
        return runCatching {
            val response = networkClient
                .client
                .getWithKey("$baseUrl/types")
            val responseData = response.body<ListSimpleModel<String>>()
            responseData.data
        }.onFailure {
            logError(it.message ?: "", it)
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

    private fun logError(
        message: String,
        throwable: Throwable
    ) {
        Logger.error(
            tag,
            "Error while processing the request: $message",
            throwable
        )
    }
}
