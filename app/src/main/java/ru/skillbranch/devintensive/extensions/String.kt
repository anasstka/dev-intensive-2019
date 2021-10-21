package ru.skillbranch.devintensive.extensions

private val excludePath = arrayOf(
    "enterprise",
    "features",
    "topics",
    "collections",
    "trending",
    "events",
    "marketplace",
    "pricing",
    "nonprofit",
    "customer-stories",
    "security",
    "login",
    "join"
)


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

fun String.validRepository(): Boolean {
    return this.isEmpty() || (this.matches(Regex("""(https://|wwww\.|https://wwww\.)?github\.com/\w+"""))
            && (excludePath.filter { this.contains(it, true) }).isEmpty()
            && !this.contains("anydomain", true))
}