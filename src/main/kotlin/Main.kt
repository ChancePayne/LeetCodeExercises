val shouldPrint = false

fun printDebug(text: String) {
    if (shouldPrint)
        println(text)
}

fun main() {
    val start = System.currentTimeMillis()

    BinaryTreeLowestCommonAncestor.execute()

    println()
    println((System.currentTimeMillis() - start))
}

