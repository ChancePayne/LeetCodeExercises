package problems

import kotlin.math.min

class MaximumSubarray {
    companion object {
        fun execute() {
            val solution = MaximumSubarray()
            println(solution.maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)) == 6)
            println(solution.maxSubArray(intArrayOf(1)) == 1)
            println(solution.maxSubArray(intArrayOf(5, 4, -1, 7, 8)) == 23)
            println(solution.maxSubArray(intArrayOf(5, 4, -10000, 7, 8)) == 15)
            println(solution.maxSubArray(intArrayOf(-1)) == -1)
            println(solution.maxSubArray(intArrayOf(-2,1)) == 1)
            println(solution.maxSubArray(intArrayOf(-2,-1)) == -1)
        }
    }

    fun maxSubArray(nums: IntArray): Int {
        return kadanesAlgorithm(nums)
    }

    private fun kadanesAlgorithm(nums: IntArray): Int {
        var maxValue = 0
        var overallMax = Int.MIN_VALUE

        for(i in nums.indices) {
            maxValue = if (i == 0) {
                nums[i]
            } else {
                Math.max(nums[i], nums[i] + maxValue)
            }
            overallMax = Math.max(overallMax, maxValue)
        }

        return overallMax
    }

    private fun dualSums(nums: IntArray): Int {
        if (nums.size > 1) {
            val arrayTotal = nums.sum()
            var startCutArrayTotal = 0
            var endCutArrayTotal = 0
            var minStartCut = 0
            var minEndCut = 0
            var prevMinStartCut = 0
            var prevMinEndCut = 0
            var minStartIndex = 0
            var minEndIndex = nums.size - 1
            var tempMin = 0

            var maximumSubarray = Int.MIN_VALUE

            for (i in nums.indices) {
                startCutArrayTotal += nums[i]
                tempMin = Math.min(minStartCut, startCutArrayTotal)
                if (minStartCut != tempMin) {
                    minStartIndex = i
                    prevMinStartCut = minStartCut
                    minStartCut = tempMin
                }
                if (minStartIndex >= minEndIndex) {
                    if (minStartCut - prevMinStartCut > minEndCut - prevMinEndCut) {
                        minStartCut = prevMinStartCut
                    } else {
                        minEndCut = prevMinEndCut
                    }
                    maximumSubarray = arrayTotal - minEndCut - minStartCut
                    break
                }
                endCutArrayTotal += nums[nums.size - 1 - i]
                tempMin = Math.min(minEndCut, endCutArrayTotal)
                if (minEndCut != tempMin) {
                    minEndIndex = nums.size - 1 - i
                    prevMinEndCut = minEndCut
                    minEndCut = tempMin
                }
                if (minStartIndex >= minEndIndex) {
                    if (minStartCut - prevMinStartCut > minEndCut - prevMinEndCut) {
                        minStartCut = prevMinStartCut
                    } else {
                        minEndCut = prevMinEndCut
                    }
                    maximumSubarray = arrayTotal - minEndCut - minStartCut
                    break
                }
            }
            if (maximumSubarray == Int.MIN_VALUE) {
                maximumSubarray = arrayTotal - minEndCut - minStartCut
            }

            return maximumSubarray
        } else {
            return nums[0]
        }
    }

    private fun bruteForce(nums: IntArray): Int {
        val arrayTotal = nums.sum()
        var subArrayTotal = arrayTotal
        var maxSum = arrayTotal
        var startCutArrayTotal = 0
        var endCutArrayTotal = 0

        nums.forEachIndexed { index, _ ->
            startCutArrayTotal += nums.getOrNull(index - 1) ?: 0
            subArrayTotal = arrayTotal - startCutArrayTotal
            endCutArrayTotal = 0
            for (i in 0 until nums.size - index) {
                val endNumber = nums.getOrNull(nums.size - i) ?: 0
                endCutArrayTotal += endNumber
                maxSum = Math.max(maxSum, subArrayTotal - endCutArrayTotal)
            }
        }

        return maxSum
    }
}