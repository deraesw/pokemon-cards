package com.deraesw.pokemoncards.util

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate

object DateUtil {
    val currentUtcDateTime: String = Clock.System.now().toString()

    fun convertDateToDisplayDate(date: String): String {
        val localDate = LocalDate.parse(date.replace("/", "-"))
        val month = localDate.month.name.lowercase().replaceFirstChar { it.uppercase() }
        val day = localDate.dayOfMonth.toString()
        val year = localDate.year.toString()
        return "$month $day, $year"
    }
}
