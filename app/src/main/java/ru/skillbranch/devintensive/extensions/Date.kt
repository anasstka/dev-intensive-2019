package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

private val declension = mapOf(
        0L to listOf("секунд", "минут", "часов", "дней"),
        1L to listOf("секунду", "минута", "час", "день"),
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
        seconds in 0..1 -> "только что"
        seconds in 1..45 -> "несколько секунд назад"
        seconds in 45..75 -> "минуту назад"
        seconds in 75..2700 -> "$minutes ${TimeUnits.MINUTE.plural(minutes)} назад"
        minutes in 45..75 -> "час назад"
        minutes in 75..1320 -> "$hours ${TimeUnits.HOUR.plural(hours)} назад"
        hours in 22..26 -> "день назад"
        hours in 26..8640 -> "$days ${TimeUnits.DAY.plural(days)} назад"
        else -> "более года назад"
    }
}

enum class TimeUnits(val time: Int) {
    SECOND(0),
    MINUTE(1),
    HOUR(2),
    DAY(3);

    fun plural(value: Long) : String {
        return "$value ${declension[value % 10]?.get(time)}"
    }
}