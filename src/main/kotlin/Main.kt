import kotlin.math.max

val shouldPrint = false

fun printDebug(text: String) {
    if (shouldPrint)
        println(text)
}

fun main(args: Array<String>) {
    val start = System.currentTimeMillis()

    /*val solution = RomanToInteger()
    println(solution.romanToInt("III"))
    println(solution.romanToInt("LVIII"))
    println(solution.romanToInt("MCMXCIV"))*/

    /*val solution = LongestCommonPrefix()
    println(solution.longestCommonPrefix(arrayOf("flower", "flow", "flight")))
    println(solution.longestCommonPrefix(arrayOf("dog","racecar","car")))
    println(solution.longestCommonPrefix(arrayOf("ab", "a")))
    println(solution.longestCommonPrefix(arrayOf("flower","flower","flower","flower")))*/

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

    println((System.currentTimeMillis() - start))
}

class LongestCommonSubsequence {
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

/*class ThreeSum {
    fun threeSum(nums: IntArray): List<List<Int>> {

    }
}*/

class LongestCommonPrefix {
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

class RomanToInteger {
    /*enum class RomanNumerals(val symbol: Char, val value: Int) {
        I('I', 1),
        V('V', 5),
        X('X', 10),
        L('L', 50),
        C('C', 100),
        D('D', 500),
        M('M', 1000);

        companion object {
            private val map = RomanNumerals.values().associateBy(RomanNumerals::symbol)
            fun fromChar(type: Char) = map[type]
        }
    }*/

    private fun getValueForNumeral(char: Char): Int {
        return when (char) {
            'I' -> 1
            'V' -> 5
            'X' -> 10
            'L' -> 50
            'C' -> 100
            'D' -> 500
            'M' -> 1000
            else -> 0
        }
    }

    fun romanToInt(s: String): Int {
        val symbols = s.toList()
        var value = 0

        symbols.forEachIndexed { index, char ->
            val symbol = getValueForNumeral(char)
            val nextSymbol = if (index + 1 < symbols.size)
                getValueForNumeral(symbols[index + 1])
            else
                0

            if (symbol != 0) {
                if (nextSymbol != 0 && symbol < nextSymbol) {
                    value -= symbol
                } else {
                    value += symbol
                }
            }
        }

        return value
    }
}
