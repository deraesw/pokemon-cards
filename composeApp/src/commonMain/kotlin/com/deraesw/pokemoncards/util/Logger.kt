package com.deraesw.pokemoncards.util

expect object Logger {
    fun debug(tag: String, message: String)
    fun error(tag: String, message: String, throwable: Throwable? = null)
    fun info(tag: String, message: String)
}
