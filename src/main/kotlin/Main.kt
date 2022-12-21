val shouldPrint = false

fun printDebug(text: String) {
    if (shouldPrint)
        println(text)
}

fun main() {
    val start = System.currentTimeMillis()

    FindKthLargest.execute()

    println((System.currentTimeMillis() - start))
}

