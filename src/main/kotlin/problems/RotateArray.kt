package problems

// Leetcode 189. Rotate Array
class RotateArray {
    companion object {
        fun execute() {
            val solution = RotateArray()
            var nums = intArrayOf(1, 2, 3, 4, 5, 6, 7)
            solution.rotate(nums, 3)
            println("${nums.toList()} - ${nums.contentEquals(intArrayOf(5,6,7,1,2,3,4))}")

            nums = intArrayOf(-1,-100,3,99)
            solution.rotate(nums, 2)
            println("${nums.toList()} - ${nums.contentEquals(intArrayOf(3,99,-1,-100))}")

            nums = intArrayOf(1, 2)
            solution.rotate(nums, 1)
            println("${nums.toList()} - ${nums.contentEquals(intArrayOf(2, 1))}")

            nums = intArrayOf(1,2,3,4,5,6)
            solution.rotate(nums, 2)
            println("${nums.toList()} - ${nums.contentEquals(intArrayOf(5,6,1,2,3,4))}")

            nums = intArrayOf(1,2,3)
            solution.rotate(nums, 3)
            println("${nums.toList()} - ${nums.contentEquals(intArrayOf(1,2,3))}")

            nums = intArrayOf(1,2,3,4,5,6)
            solution.rotate(nums, 3)
            println("${nums.toList()} - ${nums.contentEquals(intArrayOf(4,5,6,1,2,3))}")

            nums = intArrayOf(2,0,6,9,8,4,5,0,8,9,1,2,9,6,8,8,0,6,3,1,2,2,1,2,6,5,3,1,2,2,6,4,2,4,3,0,0,0,3,8,2,4,0,1,2,0,1,4,6,5,8,0,7,9,3,4,6,6,5,8,9,3,4,3,7,0,4,9,0,9,8,4,3,0,7,7,1,9,1,9,4,9,0,1,9,5,7,7,1,5,8,2,8,2,6,8,2,2,7,5,1,7,9,6,0,6,9,8,4,5,0,8,9,1,2,9,6,8,8,0,6,3,1,2,2,1,2,6,5,3,1,2,2,6,4,2,4,3,0,0,0,3,8,2,4,0,1,2,0,1,4,6,5,8,0,7,9,3,4,6,6,5,8,9,3,4,3,7,0,4,9,0,9,8,4,3,0,7,7,1,9,1,9,4,9,0,1,9,5,7,7,1,5,8,2,8,2,6,8,2,2,7,5,1,7,9,6,0,6,9,8,4,5,0,8,9,1,2,9,6,8,8,0,6,3,1,2,2,1,2,6,5,3,1,2,2,6,4,2,4,3,0,0,0,3,8,2,4,0,1,2,0,1,4,6,5,8,0,7,9,3,4,6,6,5,8,9,3,4,3,7,0,4,9,0,9,8,4,3,0,7,7,1,9,1,9,4,9,0,1,9,5,7,7,1,5,8,2,8,2,6,8,2,2,7,5,1,7,9,6)
            solution.rotate(nums, 25)
            println("${nums.toList()}")
        }
    }

    fun rotate(nums: IntArray, k: Int): Unit {
        val modifiedSteps = k % nums.size

        val lastHalf = nums.copyOfRange(0, nums.size - modifiedSteps)
        val firstHalf = nums.copyOfRange(nums.size - modifiedSteps, nums.size)

        for (i in nums.indices) {
            if (i in firstHalf.indices) {
                nums[i] = firstHalf[i]
            } else {
                nums[i] = lastHalf[i - firstHalf.size]
            }
        }

    }
}