class RotateImage {
    companion object {
        fun execute() {
            val solution = RotateImage()
            println(
                solution.rotate(
                    arrayOf(
                        intArrayOf(1, 2, 3),
                        intArrayOf(4, 5, 6),
                        intArrayOf(7, 8, 9)
                    )
                )
            )
            println(
                solution.rotate(
                    arrayOf(
                        intArrayOf(1, 2, 3, 4),
                        intArrayOf(5, 6, 7, 8),
                        intArrayOf(9, 10, 11, 12),
                        intArrayOf(13, 14, 15, 16)
                    )
                )
            )
            println(
                solution.rotate(
                    arrayOf(
                        intArrayOf(1,2,3,4,5),
                        intArrayOf(6,7,8,9,10),
                        intArrayOf(11,12,13,14,15),
                        intArrayOf(16,17,18,19,20),
                        intArrayOf(21,22,23,24,25)
                    )
                )
            )
        }
    }

    fun rotate(matrix: Array<IntArray>): Unit {
        var temp = matrix[0][0]
        var temp2 = temp
        var tempR = 0
        var count = 0

        var r = -1
        var c = 0

        while (count < matrix.size / 2 + 1) {
            val rotationSize = matrix.first().size - 1 - (c * 2)
            for (i in 0 until rotationSize) {
                r++
                for (side in 0 until 5) {
                    r %= matrix.size
                    c %= matrix.size
                    temp = matrix[r][c]
                    matrix[r][c] = temp2
                    temp2 = temp
                    tempR = r
                    r = c
                    c = matrix.size - 1 - tempR
                }
            }
            count++
            r = (r + 2) % matrix.size
            c = (c + 2) % matrix.size
        }

        matrix.forEach { it.forEach { print("$it,") }; println() }
    }
}