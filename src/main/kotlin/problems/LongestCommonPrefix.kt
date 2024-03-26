package problems

class LongestCommonPrefix {
    companion object {
        fun execute() {
            val solution = LongestCommonPrefix()
            println(solution.longestCommonPrefix(arrayOf("flower", "flow", "flight")))
            println(solution.longestCommonPrefix(arrayOf("dog","racecar","car")))
            println(solution.longestCommonPrefix(arrayOf("ab", "a")))
            println(solution.longestCommonPrefix(arrayOf("flower","flower","flower","flower")))
        }
    }
    fun longestCommonPrefix(strs: Array<String>): String {
        var prefixList = strs.first().toList()

        strs.copyOfRange(1, strs.size).forEach { string ->
            var foundEnd = false
            var index = 0
            while (!foundEnd && index < string.length) {
                val char = string[index]
                if (index >= prefixList.size) {
                    foundEnd = true
                } else if (char != prefixList[index]) {
                    foundEnd = true
                } else {
                    index++
                }
            }
            prefixList = prefixList.subList(0, index)
        }
        return String(prefixList.toCharArray())
    }
}