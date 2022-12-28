class BuyAndSellStock {
    companion object {
        fun execute() {
            val solution = BuyAndSellStock()
            println(solution.maxProfit(intArrayOf(7, 1, 5, 3, 6, 4)) == 5)
            println(solution.maxProfit(intArrayOf(7, 6, 4, 3, 1)) == 0)
            println(solution.maxProfit(intArrayOf(2, 7, 1, 4)) == 5)
            println(
                solution.maxProfit(
                    intArrayOf(
                        45, 96, 97, 29, 79, 59, 23, 9, 81, 74, 7, 69, 71, 48, 73, 80, 21, 77, 32, 99, 70, 15, 44,
                        17, 36, 42, 55, 52, 24, 16, 82, 76, 22, 3, 72, 43, 25, 56, 51, 54, 27,
                        63, 41, 30, 47, 98, 4, 14, 91, 19, 34, 40, 8, 37, 20,
                        46, 61, 66, 5, 10, 93, 100, 6, 26, 12, 67, 90, 49,
                        33, 88, 53, 95, 50, 94, 92, 83, 31, 85, 13, 65, 64, 60, 35,
                        75, 86, 62, 89, 28, 2, 58, 78, 11, 87, 68, 38, 39, 1, 57, 84, 18
                    )
                )
            )
        }
    }

    fun maxProfit(prices: IntArray): Int {
        return singlePassReplacement(prices)
    }

    private fun singlePassReplacement(prices: IntArray): Int {
        var smallNumber = prices.first()
        var maxProfit = 0

        prices.forEach {
            smallNumber = Math.min(smallNumber, it)
            maxProfit = Math.max(maxProfit, it - smallNumber)
        }


        return maxProfit
    }

    private fun bruteForceSolution(prices: IntArray): Int {
        var maxProfit = 0

        prices.forEachIndexed { index, i ->
            prices.copyOfRange(index, prices.size).forEach {
                maxProfit = Math.max(maxProfit, it - i)
            }
        }

        return maxProfit
    }
}