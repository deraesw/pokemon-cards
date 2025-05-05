package com.deraesw.pokemoncards.core.core.model.inline

import kotlin.test.Test
import kotlin.test.assertEquals

class CardTypeKeyTest {
    @Test
    fun `key - return upper case`() {
        assertEquals(CardTypeKey("fire").key(), "FIRE")
    }
}
