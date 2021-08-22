package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs
import kotlin.math.min

private val declension = mapOf(
        0L to listOf("секунд", "минут", "часов", "дней"),
        1L to listOf("секунду", "минуту", "час", "день"),
        2L to listOf("секунды", "минуты", "часа", "дня"),
        3L to listOf("секунды", "минуты", "часа", "дня"),
        4L to listOf("секунды", "минуты", "часа", "дня"),
        5L to listOf("секунд", "минут", "часов", "дней"),
        6L to listOf("секунд", "минут", "часов", "дней"),
        7L to listOf("секунд", "минут", "часов", "дней"),
        8L to listOf("секунд", "минут", "часов", "дней"),
        9L to listOf("секунд", "минут", "часов", "дней")
)

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val diff = date.time - this.time
    val seconds: Long = diff / 1000
    val minutes: Long = seconds / 60
    val hours: Long = minutes / 60
    val days: Long = hours / 24
    return when {
        seconds in -1..1 -> "только что"

        seconds in 1..45 -> "несколько секунд назад"
        seconds in -45..-1 -> "через несколько секунд"

        seconds in 45..75 -> "минуту назад"
        seconds in -75..-45 -> "через минуту"

        seconds in 75..2700 -> "${TimeUnits.MINUTE.plural(minutes)} назад"
        seconds in -2700..-75 -> "через ${TimeUnits.MINUTE.plural(minutes)}"

        minutes in 45..75 -> "час назад"
        minutes in -75..-45 -> "через час"

        minutes in 75..1320 -> "${TimeUnits.HOUR.plural(hours)} назад"
        minutes in -1320..-75 -> "через ${TimeUnits.HOUR.plural(hours)}"

        hours in 22..26 -> "день назад"
        hours in -26..-22 -> "через день"

        hours in 26..8640 -> "${TimeUnits.DAY.plural(days)} назад"
        hours in -8640..-26 -> "через ${TimeUnits.DAY.plural(days)}"

        days > 360 -> "более года назад"
        days < -360 -> "более чем через год"

        else -> ""
    }
}

enum class TimeUnits(private val time: Int) {
    SECOND(0),
    MINUTE(1),
    HOUR(2),
    DAY(3);

    fun plural(value: Long) : String {
        if (abs(value % 100) in 11..19)
            return "${abs(value)} ${declension[9]?.get(time)}"
        return "${abs(value)} ${declension[abs(value % 10)]?.get(time)}"
    }
}