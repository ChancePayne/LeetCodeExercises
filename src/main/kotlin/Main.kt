import kotlin.math.max

val shouldPrint = false

fun printDebug(text: String) {
    if (shouldPrint)
        println(text)
}

fun main(args: Array<String>) {
    val start = System.currentTimeMillis()

    SpiralOrder.execute()

    println((System.currentTimeMillis() - start))
}

