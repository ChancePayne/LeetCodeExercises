val shouldPrint = false

fun printDebug(text: String) {
    if (shouldPrint)
        println(text)
}

fun main() {
    val start = System.currentTimeMillis()

    MergeTwoSortedLists.execute()

    println()
    println((System.currentTimeMillis() - start))
}

