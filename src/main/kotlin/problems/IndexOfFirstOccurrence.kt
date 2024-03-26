package problems

class IndexOfFirstOccurrence {

    companion object {
        fun execute() {
            val solution = IndexOfFirstOccurrence()
            println(solution.strStr("mississippi", "issip") == 4)
            println(solution.strStr("sadbutsad", "sad") == 0)
            println(solution.strStr("sadbutsad", "but") == 3)
            println(solution.strStr("leetcode", "leeto") == -1)
            println(solution.strStr("aaa", "aa") == 0)
            println(solution.strStr("aaa", "aaaa") == -1)
            println(solution.strStr("aabaabbbaabbbbabaaab", "abaa") == 1)
        }
    }

    fun strStr(haystack: String, needle: String): Int {
        var startNeedle = -1
        var haystackPointer = 0
        var needlePointer = 0

        while (haystackPointer < haystack.length) {
            when (haystack[haystackPointer]) {
                needle[needlePointer] -> {
                    if (needlePointer == 0) startNeedle = haystackPointer
                    if (needlePointer == needle.length - 1) break
                    if (haystackPointer == haystack.length - 1) break
                    needlePointer++
                    haystackPointer++
                }
                else -> {
                    when (needlePointer) {
                        0 -> haystackPointer++
                        else -> haystackPointer = startNeedle + 1
                    }
                    needlePointer = 0
                }
            }
        }
        return if (needlePointer == needle.length - 1) startNeedle else -1
    }
}