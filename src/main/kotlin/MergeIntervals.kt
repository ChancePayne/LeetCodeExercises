import Utils.Companion.print2dArray

class MergeIntervals {
    companion object {
        fun execute() {
            val solution = MergeIntervals()
//            print2dArray(solution.merge(arrayOf(intArrayOf(1,3),intArrayOf(2,6),intArrayOf(8,10),intArrayOf(15,18))))
//            print2dArray(solution.merge(arrayOf(intArrayOf(1,4),intArrayOf(4,5))))
//            print2dArray(solution.merge(arrayOf(intArrayOf(1,4),intArrayOf(2,3))))
            print2dArray(solution.merge(arrayOf(intArrayOf(2,3),intArrayOf(4,5),intArrayOf(6,7),intArrayOf(8,9),intArrayOf(1,1))))
        }
    }

    // incomplete
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val newIntervals = mutableListOf<IntArray>()
        var foundInterval: IntArray
        var shouldAddInterval = true
        intervals.forEach { a ->
            foundInterval = intArrayOf()
            for (bi in newIntervals.indices) {
                val b = newIntervals[bi]
                if (a[0] <= b[0] && a[1] >= b[0]) {
                    b[0] = a[0]
                    foundInterval = intArrayOf(b[0], b[1])
                    shouldAddInterval = false
                }
                if (b[1] <= a[1] && b[1] >= a[0]) {
                    b[1] = a[1]
                    foundInterval = intArrayOf(b[0], b[1])
                    shouldAddInterval = false
                }
                if (b[0] <= a[0] && b[1] >= a[1]) {
                    foundInterval = intArrayOf()
                    shouldAddInterval = false
                }
                if (shouldAddInterval) {
                    break
                }
            }
            if (shouldAddInterval) {
                newIntervals.add(a)
            }
        }
        return newIntervals.toTypedArray()
    }
}