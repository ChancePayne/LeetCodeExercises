class ProductArray {
    companion object {
        fun execute() {
            val solution = ProductArray()
            println(solution.productExceptSelf(intArrayOf(1,2,3,4)).contentEquals(intArrayOf(24,12,8,6)))
            println(solution.productExceptSelf(intArrayOf(-1,1,0,-3,3)).contentEquals(intArrayOf(0,0,9,0,0)))
        }
    }
        fun productExceptSelf(nums: IntArray): IntArray {
            return crossPass(nums)
        }

    private fun crossPass(nums: IntArray): IntArray {
        val productArray = IntArray(nums.size)
        var product = 1

        nums.forEachIndexed { i, num ->
            productArray[i] = product
            product *= num
        }
        product = 1

        for (i in nums.indices.reversed()) {
            productArray[i] *= product
            product *= nums[i]
        }

        return productArray
    }
}