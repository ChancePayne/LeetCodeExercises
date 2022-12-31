class DuplicateNumber {
    companion object {
        fun execute() {
            val solution = DuplicateNumber()
            println(solution.findDuplicate(intArrayOf(1,3,4,2,2)) == 2)
            println(solution.findDuplicate(intArrayOf(3,1,3,4,2)) == 3)
            println(solution.findDuplicate(intArrayOf(1,1)) == 1)
            println(solution.findDuplicate(intArrayOf(2,2,2,2,2)) == 2)
            println(solution.findDuplicate(intArrayOf(1,4,4,2,4)) == 4)
//            println(solution.findDuplicate(intArrayOf()) == -1)
        }
    }

    fun findDuplicate(nums: IntArray): Int {
        return floydsCycle(nums)
    }

    private fun floydsCycle(nums: IntArray): Int {
        var slow = 0
        var fast = 0

        do {
            slow = nums[slow]
            fast = nums[nums[fast]]
        } while (slow != fast)

        var slow2 = 0

        do {
            slow = nums[slow]
            slow2 = nums[slow2]
        } while (slow != slow2)

        return slow
    }

    private fun hashSet(nums: IntArray): Int {
        val hashSet = HashSet<Int>(nums.size)

        nums.forEach {
            if (hashSet.contains(it)) {
                return it
            } else {
                hashSet.add(it)
            }
        }
        return -1
    }

    private fun sorting(nums: IntArray): Int {
        val sortedArray = nums.sorted()
        for (i in sortedArray.indices) {
            if (i >= 1 && sortedArray[i] == sortedArray[i - 1]) {
                return sortedArray[i]
            }
        }
        return -1
    }

    private fun sumComparison(nums: IntArray): Int {
        val sum = ((nums.size) * ((nums.size - 1.0) / 2.0)).toInt()
        var calculatedSum = 0

        nums.forEach {
            calculatedSum += it
        }
        return if (calculatedSum != sum) calculatedSum - sum else nums[0]
    }
}