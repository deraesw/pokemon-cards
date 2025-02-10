package com.deraesw.pokemoncards.util

actual object Logger {
    actual fun debug(tag: String, message: String) {
        println("Debug - $tag: $message")
    }

    actual fun error(tag: String, message: String, throwable: Throwable?) {
        println("Error - $tag: $message")
        println(throwable?.printStackTrace())
    }

    actual fun info(tag: String, message: String) {
        println("Info - $tag: $message")
    }
}
