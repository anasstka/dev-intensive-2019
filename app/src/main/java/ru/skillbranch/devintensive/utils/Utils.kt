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
        val name = if (fullName.isNullOrBlank()) null else fullName.trim()
        val parse: List<String>? = name?.trim()?.split(" ")

        val firstName = parse?.getOrNull(0)
        val lastName = parse?.getOrNull(1)

        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var result = ""
        for (ch in payload) {
            val isUpperCase: Boolean = ch.isUpperCase()
            var char = if (ch.toString().toLowerCase(Locale.ROOT) in transcriptions)
                transcriptions[ch.toString().toLowerCase(Locale.ROOT)]
            else ch.toString()
            char = if (isUpperCase) char?.capitalize() else char
            result += char
        }

        return result.trim().replace(" ", divider)
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        if (firstName.isNullOrBlank() and lastName.isNullOrBlank())
            return null
        return "${firstName?.trim()?.toUpperCase(Locale.ROOT)?.getOrNull(0) ?: ""}${lastName?.trim()
            ?.toUpperCase(Locale.ROOT)?.getOrNull(0) ?: ""}"
    }
}