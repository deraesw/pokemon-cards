package com.deraesw.pokemoncards.core.core.util

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object DateUtil {
    val currentUtcDateTime: String = Clock.System.now().toString()

    fun convertDateToDisplayDate(date: String): String {
        val localDate = LocalDate.parse(date.replace("/", "-"))
        val month = localDate.month.name.lowercase().replaceFirstChar { it.uppercase() }
        val day = localDate.dayOfMonth.toString()
        val year = localDate.year.toString()
        return "$month $day, $year"
    }

    fun convertToDateTimeDisplay(date: String): String {
        return runCatching {
            val localDate = LocalDateTime.parse(date.replace("/", "-").replace(" ", "T"))
            val month = localDate.month.name.take(3).lowercase().replaceFirstChar { it.uppercase() }
            val day = localDate.dayOfMonth.toString()
            val year = localDate.year.toString()
            val hour = localDate.hour.toString().padStart(2, '0')
            val minute = localDate.minute.toString().padStart(2, '0')
            "$month $day, $year $hour:$minute"
        }.onFailure {
            println("Error: ${it.message} - ${it.printStackTrace()}")
        }.getOrDefault("Unknown")
    }

    fun getDateForLogs(): String {
        val localDate = Clock.System.now().toLocalDateTime(TimeZone.UTC)
        return with(localDate) { "$monthNumber-$dayOfMonth-$year" }
    }
}