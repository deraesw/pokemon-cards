package com.deraesw.pokemoncards.util

import kotlinx.datetime.Clock

object DateUtil {
    val currentUtcDateTime: String = Clock.System.now().toString()
}
