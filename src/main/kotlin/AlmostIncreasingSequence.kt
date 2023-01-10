class AlmostIncreasingSequence {
    companion object {
        fun execute() {
            val solution = AlmostIncreasingSequence()
            println(solution.solution(mutableListOf(1, 2, 3, 4, 5, 3, 5, 6)) == false)
            println(solution.solution(mutableListOf(1, 2, 1, 2)) == false)
            println(solution.solution(mutableListOf(1, 2, 5, 3, 5)) == true)
            println(solution.solution(mutableListOf(1, 1)) == true)
            println(solution.solution(mutableListOf(1, 3, 2)) == true)
            println(solution.solution(mutableListOf(1, 3, 2, 1)) == false)
            println(solution.solution(mutableListOf(1, 2, 3, 4, 99, 5, 6)) == true)
            println(solution.solution(mutableListOf(10, 1, 2, 3, 4, 5)) == true)
        }
    }

    fun solution(sequence: MutableList<Int>): Boolean {
        return lookForward(sequence)
    }

    private fun lookForward(sequence: MutableList<Int>): Boolean {
        var hasRemovedValue = false

        var i = 0
        while (i < sequence.size) {
            if (i < sequence.size - 1) {
                if (sequence[i] >= sequence[i + 1]) {
                    if (hasRemovedValue) {
                        return false
                    }
                    hasRemovedValue = true
                    if ((i < sequence.size - 2 && sequence[i] >= sequence[i + 2]) && (i > 0 && sequence[i + 1] <= sequence[i - 1])) {
                            return false
                        }
                    }
            }
            i++
        }
        return true
    }
}