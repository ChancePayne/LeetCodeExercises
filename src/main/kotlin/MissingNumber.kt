class MissingNumber {
    companion object {
        fun execute() {
            val solution = MissingNumber()
            println(solution.missingNumber(intArrayOf(3,0,1)) == 2)
            println(solution.missingNumber(intArrayOf(0,1)) == 2)
            println(solution.missingNumber(intArrayOf(9,6,4,2,3,5,7,0,1)) == 8)
        }
    }

    fun missingNumber(nums: IntArray): Int {
        val numList = HashSet<Int>()
        for (i in 0 until nums.size + 1)
            numList.add(i)

        nums.forEach { numList.remove(it) }

        return numList.first()
    }
}