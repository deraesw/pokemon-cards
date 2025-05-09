package com.deraesw.pokemoncards.core.network.client

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO

actual fun createHttpEngine(): HttpClientEngine {
    return CIO.create()
}
