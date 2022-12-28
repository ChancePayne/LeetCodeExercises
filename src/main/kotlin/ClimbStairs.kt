import kotlin.math.roundToInt

// 70. climbing stairs

class ClimbStairs {
    companion object {
        fun execute() {
            val solution = ClimbStairs()
            println(solution.climbStairs(1) == 1)
            println(solution.climbStairs(2) == 2)
            println(solution.climbStairs(3) == 3)
            println(solution.climbStairs(4) == 5)
            println(solution.climbStairs(5) == 8)
            println(solution.climbStairs(6) == 13)
            println(solution.climbStairs(35) == 14930352)
            println(solution.climbStairs(45) == 1836311903)
        }
    }

    fun climbStairs(n: Int): Int {
        return directFibonacci(n)
    }

    private fun stepFibonacci(n: Int): Int {
        var numWays = 1
        var prevNumWays = 0
        var tempNumWays = 0

        for (i in 1..n) {
            tempNumWays = numWays
            numWays += prevNumWays
            prevNumWays = tempNumWays
        }

        return numWays
    }

    private fun directFibonacci(n: Int): Int {
        return Math.round(((Math.pow((Math.pow(5.0, 0.5) + 1) / 2, (n + 1).toDouble())) - Math.pow((Math.pow(5.0, 0.5) - 1) / 2, (n + 1).toDouble())) / Math.pow(5.0, 0.5)).toInt()
    }
}