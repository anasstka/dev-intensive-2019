package ru.skillbranch.devintensive.extensions

fun String.truncate(number: Int = 16): String {
    return "${this.subSequence(0, number).trim()}${if (this.trim().length > number) "..." else ""}"
}

fun String.stripHtml() : String {
    return this
            .substringAfter(">")
            .substringBeforeLast("<")
            .replace( Regex("&<>'\""), "")
            .replace(Regex("\\s+"), " ")
}