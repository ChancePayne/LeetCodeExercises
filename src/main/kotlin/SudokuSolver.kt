import kotlin.random.Random

class SudokuSolver {
    companion object {
        fun execute() {
            val solution = SudokuSolver()
            println(solution.solveSudoku(
                arrayOf(
                charArrayOf('5','3','.','.','7','.','.','.','.'),
                charArrayOf('6','.','.','1','9','5','.','.','.'),
                charArrayOf('.','9','8','.','.','.','.','6','.'),
                charArrayOf('8','.','.','.','6','.','.','.','3'),
                charArrayOf('4','.','.','8','.','3','.','.','1'),
                charArrayOf('7','.','.','.','2','.','.','.','6'),
                charArrayOf('.','6','.','.','.','.','2','8','.'),
                charArrayOf('.','.','.','4','1','9','.','.','5'),
                charArrayOf('.','.','.','.','8','.','.','7','9')
                )
            ))
            /*println(solution.solveSudoku(
                arrayOf(
                    charArrayOf('.','.','9','7','4','8','.','.','.'),
                    charArrayOf('7','.','.','.','.','.','.','.','.'),
                    charArrayOf('.','2','.','1','.','9','.','.','.'),
                    charArrayOf('.','.','7','.','.','.','2','4','.'),
                    charArrayOf('.','6','4','.','1','.','5','9','.'),
                    charArrayOf('.','9','8','.','.','.','3','.','.'),
                    charArrayOf('.','.','.','8','.','3','.','2','.'),
                    charArrayOf('.','.','.','.','.','.','.','.','6'),
                    charArrayOf('.','.','.','2','7','5','9','.','.')
                )
            ))*/
        }
    }

    var SUM_COMPLETE_SET = -1
    var POSSIBILITIES = HashSet<Int>()

    fun solveSudoku(board: Array<CharArray>): Unit {
        val (intBoard, count) = charArrayToIntArray(board)

        repopulateCharArray(board, solver(intBoard, count))

        print2DArray(board)
    }

    private fun print2DArray(board: Array<CharArray>) {
        board.forEach { row ->
            row.forEach { print("$it,") }
            println()
        }
    }

    private fun solver(board: Array<IntArray>, startCount: Int): Array<IntArray> {
        val previousSteps = mutableListOf<Triple<Int, Int, Int>>()
        var numGuesses = 0
        var count = startCount
        SUM_COMPLETE_SET = ((board.size + 1.0) * ((board.size) / 2.0)).toInt()
        for(i in 1..board.size) {
            POSSIBILITIES.add(i)
        }

        val edgeSize = Math.sqrt(board.size.toDouble()).toInt()

        var previousCount = count
        var failedLoops = 0
        while (count > 0 && failedLoops < 2) {
            /*// check rows
            board.forEachIndexed { r, row ->
                val pair = checkLine(row)
                if (pair.first != -1 && pair.second != -1 && board[r][pair.second] == 0) {
                    assignCell(board, r, pair.second, pair.first, previousSteps)
                    count--
                }
            }

            // check columns
            for (c in board[0].indices) {
                val pair = checkLine(getColumn(board, c))
                if (pair.first != -1 && pair.second != -1 && board[pair.second][c] == 0) {
                    assignCell(board, pair.second, c, pair.first, previousSteps)
                    count--
                }
            }

            // check squares
            for (r in 0 until edgeSize) {
                for (c in 0 until edgeSize) {
                    val triple = checkSquare(board, r * edgeSize, c * edgeSize, edgeSize)
                    if (triple.first != -1 && triple.second != -1 && board[triple.second][triple.third] == 0) {
                        assignCell(board, triple.second, triple.third, triple.first, previousSteps)
                        count--
                    }
                }
            }

            // check intersection
            for (r in 0 until edgeSize) {
                for (c in 0 until edgeSize) {
                    val tripleList = checkIntersection(board, r * edgeSize, c * edgeSize, edgeSize)
                    tripleList.forEach { triple ->
                        if (board[triple.second][triple.third] == 0) {
                            assignCell(board, triple.second, triple.third, triple.first, previousSteps)
                            count--
                        }
                    }
                }
            }*/

            // cell elimination
            // look in square, row, column for 1-9 if only 1 possibility, replace
            val hashOfOptions = HashSet<Int>()
            val rowOptions = Array<HashSet<Int>>(board.size) { hashSetOf<Int>() }
            for(r in board.indices) {
                buildLineOptions(rowOptions[r], board[r])
            }
            val colOptions = Array<HashSet<Int>>(board.size) { hashSetOf<Int>() }
            for (c in board[0].indices) {
                buildLineOptions(colOptions[c], getColumn(board, c))
            }

            for(r in board.indices) {
                for (c in board[0].indices) {
                    if (board[r][c] == 0) {
                        hashOfOptions.addAll(POSSIBILITIES)
                        clearSquareOptions(hashOfOptions, board, (r / edgeSize) * edgeSize, (c / edgeSize) * edgeSize, edgeSize)
                        hashOfOptions.removeAll(rowOptions[r].toSet())
                        hashOfOptions.removeAll(colOptions[c].toSet())
                        if (hashOfOptions.size == 1) {
                            assignCell(board, r, c, hashOfOptions.first(), previousSteps)
                            count--
                        } else if (hashOfOptions.size == 1 + failedLoops && Random.nextInt(5) == 0) {
                            val value = hashOfOptions.toArray()[Random.nextInt(1 + failedLoops)] as Int
                            previousSteps.clear()
                            assignCell(board, r, c, value, previousSteps)
                            count--
                            numGuesses++
                            failedLoops = 0
                        }
                        hashOfOptions.clear()
                    }
                }
            }
            if (previousCount == count) {
                failedLoops++
            } else {
                previousCount = count
                failedLoops = 0
            }
            if (failedLoops > 0 && numGuesses > 0) {
                unwindSteps(board, previousSteps)
                numGuesses = 0
                failedLoops = 0
                count += previousSteps.size
                previousSteps.clear()
            }
        }
        return board
    }

    private fun unwindSteps(board: Array<IntArray>, previousSteps: MutableList<Triple<Int, Int, Int>>) {
        previousSteps.forEach {
            board[it.first][it.second] = 0
        }
    }

    private fun assignCell(
        board: Array<IntArray>,
        r: Int,
        c: Int,
        value: Int,
        previousSteps: MutableList<Triple<Int, Int, Int>>
    ) {
        board[r][c] = value
        previousSteps.add(Triple(r, c, value))
    }

    private fun buildLineOptions(options: HashSet<Int>, line: IntArray) {
        line.forEach {
            if (it != 0) {
                options.add(it)
            }
        }
    }

    private fun clearSquareOptions(options: HashSet<Int>, board: Array<IntArray>, rowStart: Int, colStart: Int, edgeSize: Int) {
        for (r in rowStart until rowStart + edgeSize) {
            for (c in colStart until colStart + edgeSize) {
                if (board[r][c] != 0) {
                    options.remove(board[r][c])
                }
            }
        }
    }

    private fun checkIntersection(
        board: Array<IntArray>,
        rowStart: Int,
        colStart: Int,
        edgeSize: Int
    ): List<Triple<Int, Int, Int>> {
        val missingNumbers: LinkedHashSet<Int> = (1..board.size).toSet() as LinkedHashSet

        for (r in rowStart until rowStart + edgeSize) {
            for (c in colStart until colStart + edgeSize) {
                missingNumbers.remove(board[r][c])
            }
        }

        val rowLocations = HashSet<Int>()
        val columnLocations = HashSet<Int>()

        val foundNumberList = mutableListOf<Triple<Int, Int, Int>>()
        var emptySpaces = -1

        missingNumbers.forEach { missingNo ->
            for (r in rowStart until rowStart + edgeSize) {
                emptySpaces = edgeSize
                for (c in colStart until colStart + edgeSize) {
                    if (board[r][c] !=  0) {
                        emptySpaces--
                    }
                }
                if (emptySpaces > 0) {
                    rowLocations.add(r)
                }
            }
            for (c in colStart until colStart + edgeSize) {
                emptySpaces = edgeSize
                for (r in rowStart until rowStart + edgeSize) {
                    if (board[r][c] !=  0) {
                        emptySpaces--
                    }
                }
                if (emptySpaces > 0) {
                    columnLocations.add(c)
                }
            }

            for(r in rowStart until rowStart + edgeSize) {
                if (board[r].contains(missingNo)) {
                    rowLocations.remove(r)
                }
            }
            for(c in colStart until colStart + edgeSize) {
                for (r in board.indices) {
                    if (board[r][c] == missingNo) {
                        columnLocations.remove(c)
                    }
                }
            }
            if (rowLocations.size == 1 && columnLocations.size == 1) {
                foundNumberList.add(Triple(missingNo, rowLocations.first(), columnLocations.first()))
            }
            rowLocations.clear()
            columnLocations.clear()
        }
        return foundNumberList
    }

    private fun checkSquare(board: Array<IntArray>, rowStart: Int, colStart: Int, edgeSize: Int): Triple<Int, Int, Int> {
        var emptyIndexR = -1
        var emptyIndexC = -1
        val squareOptions = HashSet<Int>()
        squareOptions.addAll(POSSIBILITIES)

        for (r in rowStart until rowStart + edgeSize) {
            for (c in colStart until colStart + edgeSize) {
                if (board[r][c] == 0) {
                    emptyIndexC = c
                    emptyIndexR = r
                }
                squareOptions.remove(board[r][c])
            }
        }

        return if (squareOptions.size == 1) {
            Triple(squareOptions.first(), emptyIndexR, emptyIndexC)
        } else {
            Triple(-1, -1, -1)
        }
    }

    private fun checkLine(line: IntArray): Pair<Int, Int> {
        var emptyIndex = -1
        val lineOptions = HashSet<Int>()
        lineOptions.addAll(POSSIBILITIES)

        line.forEachIndexed { i, num ->
            if (num == 0) {
                emptyIndex = i
            } else {
                lineOptions.remove(num)
            }

        }

        return if (lineOptions.size == 1) {
            Pair(lineOptions.first(), emptyIndex)
        } else {
            Pair(-1, -1)
        }
    }

    private fun getColumn(board: Array<IntArray>, columnNo: Int): IntArray {
        val column = IntArray(board.size)

        board.forEachIndexed { index, row -> column[index] = row[columnNo] }

        return column
    }

    private fun charArrayToIntArray(board: Array<CharArray>): Pair<Array<IntArray>, Int> {
        val intArray = Array(board.size) { IntArray(board[0].size) { 0 } }
        var numMissing = 0

        board.forEachIndexed { r, row ->
            row.forEachIndexed { c, cell ->

                intArray[r][c] =
                    if (cell != '.') {
                        cell.toInt() - 48
                    } else {
                        numMissing++
                        0
                    }
            }
        }

        return Pair(intArray, numMissing)
    }
    private fun repopulateCharArray(board: Array<CharArray>, newBoard: Array<IntArray>) {
        newBoard.forEachIndexed { r, row ->
            row.forEachIndexed { c, _ ->
                board[r][c] = if (newBoard[r][c] != 0)
                    (newBoard[r][c] + 48).toChar()
                else
                    '.'
            }
        }
    }
}