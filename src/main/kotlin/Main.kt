val shouldPrint = false

fun printDebug(text: String) {
    if (shouldPrint)
        println(text)
}

fun main() {
    val start = System.currentTimeMillis()

    ClimbStairs.execute()

    println()
    println((System.currentTimeMillis() - start))
}

