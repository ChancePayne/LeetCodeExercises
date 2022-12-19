import kotlin.math.max

class LongestCommonSubsequence {
    companion object {
        fun execute() {
            val solution = LongestCommonSubsequence()
            println(solution.longestCommonSubsequenceSample("abcde", "ace")) // 3
//    println(solution.longestCommonSubsequence("abc", "abc")) // 3
//    println(solution.longestCommonSubsequence("abc", "def")) // 0
//    println(solution.longestCommonSubsequence("abcdefgh", "ceg")) // 3
//    println(solution.longestCommonSubsequence("abcdefgh", "cep")) // 2
//    println(solution.longestCommonSubsequence("abcdefghij", "cdij")) // 4
//    println(solution.longestCommonSubsequence("ezupkr", "ubmrapg")) // 2
//    println(solution.longestCommonSubsequence("cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc", "cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc")) //
//    println(solution.longestCommonSubsequenceSample("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
//        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")) // 210
//    println(solution.longestCommonSubsequence("pcbmdupybalwpkbidypqbwhefijytypwdwbsharqdurkrslqlqla", "jodcpirubsryvudgpwncrmtypatunqpkhehuhkdmbctyxghsfktaz")) //
//    println(solution.longestCommonSubsequence("zelohidwdcxilkvytazgfozonwrkbalcpizgtmzuhkbsfefshmtctuvc", "rwjmzoncvihmlmvgdujopwrajuxmjefonivyvkncnwnkjaxkritkporsj")) //
        }
    }
    fun longestCommonSubsequenceSample(text1: String, text2: String): Int {
        val text1Size = text1.length; val text2Size = text2.length

        // dp[i][j] = the length of their longest common subsequence for text1 [0:i) and text2 [0:j)

        // dp table for "abcde" (5 + 1) and "ace" (3 + 1)

        //   0 1 2 3 4 5
        // 0
        // 1   1 1 1 1 1
        // 2   1 1 2 2 2
        // 3   1 1 2 2 3

        val dp = Array(text1Size + 1) { IntArray(text2Size + 1) }

        dp.forEach {
            it.forEach { print("$it, ") }
            println()
        }
        println()


        for (i in 1..text1Size) {
            for (j in 1..text2Size) {
                if (text1[i - 1] == text2[j - 1])
                    dp[i][j] = dp[i - 1][j - 1] + 1
                else
                    dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1])
            }
        }

        dp.forEach {
            it.forEach { print("$it, ") }
            println()
        }
        println()



        return dp[text1Size][text2Size]
    }

    fun longestCommonSubsequence(text1: String, text2: String): Int {
        return if (text1.contains(text2) || text2.contains(text1)) {
            Math.min(text1.length, text2.length)
        } else {
            findNextMatchingLetter(text1.toCharArray(), text2.toCharArray(), 0)
        }
    }

    private fun findNextMatchingLetter(text1: CharArray, text2: CharArray, subsequenceCount: Int): Int {
        var index1 = 0
        var stepNumber = subsequenceCount
        var maxStep = stepNumber
        printDebug("$stepNumber: $text1, $text2")

        if(text1.isEmpty() || text2.isEmpty()) {
            printDebug("return empty")
            return stepNumber
        }

        while(index1 < text1.size) {
            var index2 = 0
            while(index2 < text2.size) {
                if(text1[index1] == text2[index2]) {
                    printDebug("${text1[index1]} - $index1, $index2")
                    stepNumber++
                    maxStep = max(findNextMatchingLetter(text1.copyOfRange(index1 + 1, text1.size), text2.copyOfRange(index2 + 1, text2.size), stepNumber), maxStep)
                    stepNumber--
                }
                index2++
            }
            index1++
        }

        printDebug("return $stepNumber")
        return maxStep
    }
}