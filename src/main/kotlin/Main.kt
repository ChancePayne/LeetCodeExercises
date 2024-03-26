import problems.*

val shouldPrint = false

fun printDebug(text: String) {
    if (shouldPrint)
        println(text)
}

fun main() {
    val start = System.currentTimeMillis()

    RotateArray.execute()

    println()
    println((System.currentTimeMillis() - start))
}

