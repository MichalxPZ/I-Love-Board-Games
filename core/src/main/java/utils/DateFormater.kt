package utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateFormater {

    fun dateToString(date: LocalDate): String? {
        val year = date.year.toString()
        val monthInt = date.monthValue
        var monthString = date.monthValue.toString()
        if (monthInt < 10) monthString = "0$monthString"

        val dayInt = date.dayOfMonth
        var dayString = date.dayOfMonth.toString()
        if (dayInt < 10) dayString = "0$dayString"
        return "$year-$monthString-$dayString"
    }

    fun stringToDate(value: String): LocalDate? {
        return LocalDate.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }
}