// 3. Longest Substring Without Repeating Characters
class LongestSubstring {
    companion object {
        fun execute() {
            val solution = LongestSubstring()
            println(solution.lengthOfLongestSubstring("bbbbb") == 1)
            println(solution.lengthOfLongestSubstring("abcabcbb") == 3)
            println(solution.lengthOfLongestSubstring("pwwkew") == 3)
            println(solution.lengthOfLongestSubstring("aab") == 2)
            println(solution.lengthOfLongestSubstring("dvdf") == 3)
        }
    }

    fun lengthOfLongestSubstring(s: String): Int {
        val charList = mutableListOf<Char>()
        var maxLength = 0
        var currentLength = 0

        for (i in s.indices) {
            val element = s[i]
            if (charList.contains(element)) {
                do {
                    val char: Char = charList.removeAt(0)
                } while(char != element)
                currentLength = charList.size
            }
            charList.add(element)
            currentLength++
            maxLength = Math.max(currentLength, maxLength)
        }
        return maxLength
    }
}