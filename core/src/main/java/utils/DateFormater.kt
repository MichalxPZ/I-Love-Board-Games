package utils

import java.time.LocalDate

object DateFormater {

    fun dateToString(date: LocalDate): String? {
        return "${date.year}-${date.month}-${date.dayOfMonth}"
    }

    fun stringToDate(value: String): LocalDate? {
        return LocalDate.parse(value)
    }
}