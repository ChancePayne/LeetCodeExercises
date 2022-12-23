// 1 Two Sum
class TwoSum {
    companion object {
        fun execute() {
            val solution = TwoSum()
//            println(solution.twoSum(intArrayOf(2,7,11,15), 9).stringify())
            println(solution.twoSum(intArrayOf(3,2,4), 6).stringify())
        }
    }
    fun twoSum(nums: IntArray, target: Int): IntArray {
        nums.forEachIndexed { index, i ->
            val secondIndex = nums.copyOfRange(index + 1, nums.size).indexOf(target - i)
            if (secondIndex != -1) {
                return intArrayOf(index, secondIndex + index + 1)
            }
        }
        return intArrayOf()
    }
}

fun IntArray.stringify(): String {
    var string = ""
    this.forEach { string += "$it, " }
    return string
}