class TopKFrequent {
    companion object {
        fun execute() {
            val solution = TopKFrequent()
            println(solution.topKFrequent(intArrayOf(1,1,1,2,2,3), 2).contentEquals(intArrayOf(1,2)))
            println(solution.topKFrequent(intArrayOf(1), 1).contentEquals(intArrayOf(1)))
            println(solution.topKFrequent(intArrayOf(4,1,-1,2,-1,2,3), 2).contentEquals(intArrayOf(-1,2)))
        }
    }

    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val countMap = HashMap<Int, Int>()

        nums.forEach {
            val count = countMap[it]
            if (count != null) {
                countMap[it] = count + 1
            } else {
                countMap[it] = 1
            }
        }

        val sortedList = countMap.toList().sortedByDescending { it.second }

        val resultArray = IntArray(k)
        for(i in resultArray.indices) {
            resultArray[i] = sortedList[i].first
        }

        return resultArray
    }
}