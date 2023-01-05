class Utils {
    companion object {
        fun print2dArray(array: Array<IntArray>) {
            array.forEach { it.forEach { print("$it, ") }; println() }
            println()
        }
    }
}