package problems

// 79. Word Search
class WordSearch {
    companion object {
        fun execute() {
            val solution = WordSearch()
            println(
                solution.exist(
                    arrayOf(
                        charArrayOf('A', 'B', 'C', 'E'),
                        charArrayOf('S', 'F', 'C', 'S'),
                        charArrayOf('A', 'D', 'E', 'E')
                    ),
                    "ABCCED"
                ) == true
            )
            println(
                solution.exist(
                    arrayOf(
                        charArrayOf('A', 'B', 'C', 'E'),
                        charArrayOf('S', 'F', 'C', 'S'),
                        charArrayOf('A', 'D', 'E', 'E')
                    ),
                    "SEE"
                ) == true
            )
            println(
                solution.exist(
                    arrayOf(
                        charArrayOf('A', 'B', 'C', 'E'),
                        charArrayOf('S', 'F', 'C', 'S'),
                        charArrayOf('A', 'D', 'E', 'E')
                    ),
                    "SEAL"
                ) == false
            )
            println(
                solution.exist(
                    arrayOf(
                        charArrayOf('A', 'B', 'C', 'E'),
                        charArrayOf('S', 'F', 'C', 'S'),
                        charArrayOf('A', 'D', 'E', 'E')
                    ),
                    "ABCB"
                ) == false
            )
            println(
                solution.exist(
                    arrayOf(
                        charArrayOf('a', 'b'),
                        charArrayOf('c', 'd')
                    ),
                    "abcd"
                ) == false
            )
            println(
                solution.exist(
                    arrayOf(
                        charArrayOf('A','B','C','E'),
                        charArrayOf('S','F','E','S'),
                        charArrayOf('A','D','E','E')
                    ),
                    "ABCEFSADEESE"
                ) == true
            )
            println(
                solution.exist(
                    arrayOf(
                        charArrayOf('A','B','E'),
                        charArrayOf('B','C','D')
                    ),
                    "ABCDEB"
                ) == true
            )
        }
    }

    fun exist(board: Array<CharArray>, word: String): Boolean {
        board.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { cellIndex, _ ->
                if (search(rowIndex, cellIndex, board, getNewBooleanArray(board), word.toCharArray()))
                    return true
            }
        }
        return false
    }

    private fun getNewBooleanArray(board: Array<CharArray>) =
        Array(board.size) { IntArray(board.first().size) { 0 } }

    private fun clearUnusedSearch(searched: Array<IntArray>) {
        searched.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { cellIndex, cell ->
                if (cell == 0) {
                    searched[rowIndex][cellIndex] = 0
                }
            }
        }
    }

    private fun search(r: Int, c: Int, board: Array<CharArray>, searched: Array<IntArray>, word: CharArray): Boolean {
        if (word.isEmpty()) {
            return true
        }

        if (r !in board.indices || c !in board.first().indices) {
            return false
        }

        if (searched[r][c] != 0) {
            return false
        }

        return if (board[r][c] == word.first() && searched[r][c] != 1) {
            searched[r][c] = 1
            var exists = search(r - 1, c, board, searched, word.copyOfRange(1, word.size)) // up
            exists = exists || search(r, c + 1, board, searched, word.copyOfRange(1, word.size)) // right
            exists = exists || search(r + 1, c, board, searched, word.copyOfRange(1, word.size)) // down
            exists = exists || search(r, c - 1, board, searched, word.copyOfRange(1, word.size)) // left
            if (!exists) {
                searched[r][c] = 0
            }
            exists
        } else {
            searched[r][c] = 0
            false
        }
    }
}