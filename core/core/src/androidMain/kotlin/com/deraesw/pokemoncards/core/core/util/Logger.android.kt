package com.deraesw.pokemoncards.core.core.util

import android.util.Log

actual object Logger {
    actual fun debug(tag: String, message: String) {
        Log.d(tag, message)
    }

    actual fun error(tag: String, message: String, throwable: Throwable?) {
    }

    actual fun info(tag: String, message: String) {
    }
}