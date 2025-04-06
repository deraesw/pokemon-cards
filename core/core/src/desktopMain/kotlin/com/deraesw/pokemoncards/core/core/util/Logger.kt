package com.deraesw.pokemoncards.core.core.util

import java.io.File
import java.io.FileWriter

actual object Logger {
    // TODO handle exceptions for file handling
    actual fun debug(
        tag: String,
        message: String
    ) {
        val fullMessage = "Debug - $tag: $message"
        println(fullMessage)
        writeLog(fullMessage)
    }

    actual fun error(
        tag: String,
        message: String,
        throwable: Throwable?
    ) {
        println("Error - $tag: $message")
        println(throwable?.printStackTrace())
        writeLog("Error - $tag: $message - ${throwable?.printStackTrace()}")
    }

    actual fun info(
        tag: String,
        message: String
    ) {
        val fullMessage = "Info - $tag: $message"
        println(fullMessage)
        writeLog(fullMessage)
    }

    private fun writeLog(message: String) {
        val fullMessage = "${DateUtil.currentUtcDateTime} - $message"
        FileWriter(getLogFile(), true).use {
            it.write("$fullMessage\n")
        }
    }

    private fun getLogFile(): File {
        val data = SystemUtil.getDataDirectoryPath()
        val logDir = File("$data/logs")

        if (!logDir.exists()) {
            logDir.mkdirs()
        }

        val date = DateUtil.getDateForLogs()
        val logFile = File("$logDir/log-$date.log")
        logFile.createNewFile()

        return logFile
    }
}
