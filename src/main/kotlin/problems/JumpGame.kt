package problems

class JumpGame {
    companion object {
        fun execute() {
            val solution = JumpGame()
            println(solution.canJump(intArrayOf(2,3,1,1,4)))
            println(solution.canJump(intArrayOf(3,2,1,0,4)))
            println(solution.canJump(intArrayOf(2,0,6,9,8,4,5,0,8,9,1,2,9,6,8,8,0,6,3,1,2,2,1,2,6,5,3,1,2,2,6,4,2,4,3,0,0,0,3,8,2,4,0,1,2,0,1,4,6,5,8,0,7,9,3,4,6,6,5,8,9,3,4,3,7,0,4,9,0,9,8,4,3,0,7,7,1,9,1,9,4,9,0,1,9,5,7,7,1,5,8,2,8,2,6,8,2,2,7,5,1,7,9,6)))
        }
    }

    fun canJump(nums: IntArray): Boolean {
        return testJump(nums, 0)
    }

    fun testJump(nums:IntArray, startIndex: Int): Boolean {
        if ((nums.size - startIndex) <= 1) return true

        var distance = nums[startIndex]

        while(distance > 0) {
            if (testJump(nums, startIndex + distance))
                return true
            else {
                nums[startIndex + distance] = 0
                distance--
            }
        }

        return false
    }

    fun canJumpOld(nums: IntArray): Boolean {
        if (nums.size == 1) return true

        var distance = nums.first()

        while(distance > 0) {
            if (canJump(nums.slice(distance..(nums.size - 1)).toIntArray()))
                return true
            else
                distance--
        }

        return false
    }
}