class HouseRobber {
    companion object {
        fun execute() {
            val solution = HouseRobber()
            println(solution.rob(intArrayOf(226,174,214,16,218,48,153,131,128,17,157,142,88,43,37,157,43,221,191,68,206,23,225,82,54,118,111,46,80,49,245,63,25,194,72,80,143,55,209,18,55,122,65,66,177,101,63,201,172,130,103,225,142,46,86,185,62,138,212,192,125,77,223,188,99,228,90,25,193,211,84,239,119,234,85,83,123,120,131,203,219,10,82,35,120,180,249,106,37,169,225,54,103,55,166,124)) == 7102)
            println(
                solution.rob(
                    intArrayOf(
                        155,
                        44,
                        52,
                        58,
                        250,
                        225,
                        109,
                        118,
                        211,
                        73,
                        137,
                        96,
                        137,
                        89,
                        174,
                        66,
                        134,
                        26,
                        25,
                        205,
                        239,
                        85,
                        146,
                        73,
                        55,
                        6,
                        122,
                        196,
                        128,
                        50,
                        61,
                        230,
                        94,
                        208,
                        46,
                        243,
                        105,
                        81,
                        157,
                        89,
                        205,
                        78,
                        249,
                        203,
                        238,
                        239,
                        217,
                        212,
                        241,
                        242,
                        157,
                        79,
                        133,
                        66,
                        36,
                        165
                    )
                ) == 4517
            )
            println(
                solution.rob(
                    intArrayOf(
                        114,
                        117,
                        207,
                        117,
                        235,
                        82,
                        90,
                        67,
                        143,
                        146,
                        53,
                        108,
                        200,
                        91,
                        80,
                        223,
                        58,
                        170,
                        110,
                        236,
                        81,
                        90,
                        222,
                        160,
                        165,
                        195,
                        187,
                        199,
                        114,
                        235,
                        197,
                        187,
                        69,
                        129,
                        64,
                        214,
                        228,
                        78,
                        188,
                        67,
                        205,
                        94,
                        205,
                        169,
                        241,
                        202,
                        144,
                        240
                    )
                ) == 4173
            )
            println(solution.rob(intArrayOf()) == 0)
            println(solution.rob(intArrayOf(1)) == 1)
            println(solution.rob(intArrayOf(1, 2, 3, 1)) == 4)
            println(solution.rob(intArrayOf(2, 7, 9, 3, 1)) == 12)
            println(solution.rob(intArrayOf(2, 7, 9, 3, 2, 7, 9, 3, 2, 7, 9, 3, 2, 7, 9, 3, 2, 7, 9, 3, 1)) == 56)
            println(
                solution.rob(
                    intArrayOf(
                        1,
                        2,
                        3,
                        13,
                        4,
                        5,
                        6,
                        78,
                        8,
                        6,
                        4,
                        3,
                        7,
                        55,
                        4,
                        5,
                        89,
                        3,
                        7,
                        9,
                        5,
                        3,
                        45,
                        6,
                        7,
                        6
                    )
                ) == 315
            )
            println(solution.rob(intArrayOf(2, 7, 9, 3, 2, 7, 9, 3, 2, 7, 9, 3, 2, 7, 9, 3, 1)) == 45)
            println(solution.rob(intArrayOf(5, 10, 7, 1, 3, 5, 8, 9, 5, 6, 8, 9, 10, 1, 3)) == 49)
        }
    }

    fun rob(nums: IntArray): Int {
        return leapFrogArray(nums)
    }

    private fun threeLines(nums: IntArray): Int {
        var last = 0
        var now = 0
        var temp: Int

        nums.forEach {
            temp = last
            last = now
            now = Math.max(temp + it, now)
        }
        return now
    }

    private fun leapFrogArray(nums: IntArray): Int {
        return when (nums.size) {
            0 -> 0
            1 -> nums.first()
            else -> {
                val window = IntArray(nums.size)

                for (index in nums.indices) {
                    if (index < 2) {
                        window[index] = nums[index]
                    } else if (index == 2) {
                        val early = window[0] + nums[index]
                        val late = nums[index]
                        window[index] = Math.max(early, late)
                    } else {
                        val early = window[index - 3] + nums[index]
                        val late = window[index - 2] + nums[index]
                        window[index] = Math.max(early, late)
                    }
                }
                Math.max(window[window.size - 2], window[window.size - 1])
            }
        }
    }

    private fun leapFrog(nums: IntArray): Int {
        return when (nums.size) {
            0 -> 0
            1 -> nums.first()
            else -> {
                val window = mutableListOf<Int>()

                for (index in nums.indices) {
                    if (window.size < 2) {
                        window.add(nums[index])
                    } else if (window.size == 2) {
                        window.add(Math.max(window[0] + nums[index], nums[index]))
                    } else {
                        window.add(Math.max(window[0] + nums[index], window[1] + nums[index]))
                    }
                    if (window.size > 3) {
                        window.removeAt(0)
                    }
                }
                Math.max(window[window.size - 2], window[window.size - 1])
            }
        }
    }

    private fun bruteForceWhen(nums: IntArray): Int {
        return when (nums.size) {
            0 -> 0
            1 -> nums.first()
            else -> {
                Math.max(
                    checkSteps(nums.copyOfRange(0, nums.size)),
                    checkSteps(nums.copyOfRange(1, nums.size))
                )
            }
        }
    }

    fun checkSteps(subNums: IntArray): Int {
        return Math.max(
            if (2 < subNums.size) checkSteps(subNums.copyOfRange(2, subNums.size)) + subNums[0] else subNums[0],
            if (3 < subNums.size) checkSteps(subNums.copyOfRange(3, subNums.size)) + subNums[0] else subNums[0]
        )
    }

    private fun bruteForceLoops(nums: IntArray): Int {
        var totalMoney = 0

        for(index in 0..Math.min(2, nums.size - 1)) {
            totalMoney = Math.max(totalMoney, checkStepsLoops(nums.copyOfRange(index, nums.size)))
        }
        return totalMoney
    }

    fun checkStepsLoops(subNums: IntArray): Int {
        var step = 2
        var value = subNums[0]

        while(step < Math.min(subNums.size, 4)) {
            value = Math.max(value, checkStepsLoops(subNums.copyOfRange(step, subNums.size)) + subNums[0])
            step++
        }
        return value
    }
}