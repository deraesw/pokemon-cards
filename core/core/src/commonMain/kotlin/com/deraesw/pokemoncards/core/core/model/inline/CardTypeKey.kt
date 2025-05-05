package com.deraesw.pokemoncards.core.core.model.inline

import kotlin.jvm.JvmInline

@JvmInline
value class CardTypeKey(
    private val value: String
) {
    fun key(): String = value.uppercase()
}
