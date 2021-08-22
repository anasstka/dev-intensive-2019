package ru.skillbranch.devintensive.extensions

fun String.truncate(number: Int = 16): String {
    val str = this.trim()
    return "${str.subSequence(0, str.length.coerceAtMost(number)).trim()}" +
            if (str.length > number) "..." else ""
}

fun String.stripHtml(): String {
    return this
        .replace(Regex("&[\\w\\d#]+;"), "")
        .replace(Regex("<.*?>"), "")
        .replace(Regex(" +"), " ")
}