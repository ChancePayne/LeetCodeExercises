package problems

class SpiralOrder {
    companion object {
        fun execute() {
            val solution = SpiralOrder()
            println(solution.spiralOrder(arrayOf(intArrayOf(2, 5, 8), intArrayOf(4, 0, -1))))
            println(solution.spiralOrder(arrayOf(intArrayOf(3), intArrayOf(2))))
            println(solution.spiralOrder(arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9))))
            println(solution.spiralOrder(arrayOf(intArrayOf(1,2,3,4),intArrayOf(5,6,7,8),intArrayOf(9,10,11,12))))
            println(solution.spiralOrder(arrayOf(intArrayOf(6, 9, 7))))

        }
    }

    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        val combinedList = mutableListOf<Int>()

        var indexX = 0
        var indexY = 0
        var lapCount = 0
        val sizeX = matrix[0].size
        val sizeY = matrix.size
        var direction = 0
        var pointer = 0

        while (listNotFinished(combinedList, matrix)) {
            when (direction) {
                0 -> {
                    while (indexY > lapCount) {
                        pointer = matrix[indexY][indexX]

                        combinedList.add(matrix[indexY][indexX])
                        indexY--
                        if (!listNotFinished(combinedList, matrix)) {
                            break
                        }
                    }
                    direction++
                }

                1 -> {
                    while (indexX < sizeX - lapCount) {
                        pointer = matrix[indexY][indexX]

                        combinedList.add(matrix[indexY][indexX])
                        indexX++
                        if (!listNotFinished(combinedList, matrix)) {
                            break
                        }
                    }
                    indexX--
                    direction++
                }

                2 -> {
                    indexY++
                    while (indexY <= sizeY - lapCount - 1) {
                        pointer = matrix[indexY][indexX]

                        combinedList.add(matrix[indexY][indexX])
                        indexY++
                        if (!listNotFinished(combinedList, matrix)) {
                            break
                        }
                    }
                    indexY--
                    direction++
                }

                3 -> {
                    indexX--
                    while (indexX > lapCount) {
                        pointer = matrix[indexY][indexX]

                        combinedList.add(matrix[indexY][indexX])
                        indexX--
                        if (!listNotFinished(combinedList, matrix)) {
                            break
                        }
                    }
                    direction = 0
                    lapCount++
                }
            }
        }

        return combinedList.toList()
    }

    private fun listNotFinished(
        combinedList: MutableList<Int>,
        matrix: Array<IntArray>
    ) = combinedList.size < matrix.size * matrix.first().size
}