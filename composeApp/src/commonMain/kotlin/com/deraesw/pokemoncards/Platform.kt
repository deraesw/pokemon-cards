package com.deraesw.pokemoncards

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform