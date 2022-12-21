val shouldPrint = false

fun printDebug(text: String) {
    if (shouldPrint)
        println(text)
}

fun main() {
    val start = System.currentTimeMillis()

    LongestSubstring.execute()

    println((System.currentTimeMillis() - start))
}

