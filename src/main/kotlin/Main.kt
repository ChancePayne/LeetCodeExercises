import kotlin.math.max

val shouldPrint = false

fun printDebug(text: String) {
    if (shouldPrint)
        println(text)
}

fun main(args: Array<String>) {
    val start = System.currentTimeMillis()

    HappyNumber.execute()

    println((System.currentTimeMillis() - start))
}

