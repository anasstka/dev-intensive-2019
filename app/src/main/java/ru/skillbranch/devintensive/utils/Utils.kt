package ru.skillbranch.devintensive.utils

import java.util.*

object Utils {
    private val transcriptions = mapOf(
            " " to " ",
            "а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sh'",
            "ъ" to "",
            "ы" to "i",
            "ь" to "",
            "э" to "e",
            "ю" to "yu",
            "я" to "ya"
    )

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parse: List<String>? = fullName?.split(" ")

        var firstName = parse?.getOrNull(0)
        var lastName = parse?.getOrNull(1)

        if (firstName.isNullOrEmpty())
            firstName = null
        if (lastName.isNullOrEmpty())
            lastName = null

        return Pair(firstName, lastName)
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var result = ""
        for (ch in payload.toLowerCase(Locale.ROOT).toCharArray()) {
            result += if (ch.toString() in transcriptions)
                transcriptions[ch.toString()]
            else ch.toString()
        }

        return firstUpperCase(result, divider)
    }

    private fun firstUpperCase(str: String, divider: String = " "): String {
        val parse: List<String> = str.split(" ")
        return "${parse.getOrNull(0)?.capitalize() ?: ""}$divider${parse.getOrNull(1)?.capitalize() ?: ""}"
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        if (firstName.isNullOrBlank() and lastName.isNullOrBlank())
            return null
        return "${firstName?.toUpperCase(Locale.ROOT)?.getOrNull(0) ?: ""}${lastName?.toUpperCase(Locale.ROOT)?.getOrNull(0) ?: ""}"
    }
}