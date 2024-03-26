package problems

import kotlin.math.abs

class DiagonalDifference {
    companion object {
        fun execute() {
            val solution = DiagonalDifference()

            println(solution.diagonalDifference(
                arrayOf(
                    arrayOf(3),
                    arrayOf(11, 2, 4),
                    arrayOf(4, 5, 6),
                    arrayOf(10, 8, -12),
                )
            ))
            println(solution.diagonalDifference(
                arrayOf(
                    arrayOf(1, 2, 3),
                    arrayOf(4, 5, 6),
                    arrayOf(7, 8, 9),
                )
            ))
        }
    }

    fun diagonalDifference(arr: Array<Array<Int>>): Int {
        val squareSize = arr.first().first()

        var row = 1
        var primarySum = 0
        var secondarySum = 0

        do {
            primarySum += arr[row][row - 1]
            secondarySum += arr[row][squareSize - row]
            row++
        } while (row <= squareSize)

        return abs(primarySum - secondarySum)
    }
}