// 70. climbing stairs

class ClimbStairs {
    companion object {
        fun execute() {
            val solution = ClimbStairs()
            println(solution.climbStairs(2))
            println(solution.climbStairs(3))
            println(solution.climbStairs(6))
            println(solution.climbStairs(4))
            println(solution.climbStairs(1))
        }
    }

    fun climbStairs(n: Int): Int {
        var numWays = 0

        numWays += n % 2 + 1
        val numTwos = n / 2
        numWays += Math.pow(2.0, numTwos.toDouble()).toInt() - 1

        return numWays
    }
}