package problems

class PalindromeNumber {
    companion object {
        fun execute() {
            val solution = PalindromeNumber()
            println(solution.isPalindrome(121))
            println(solution.isPalindrome(-121))
            println(solution.isPalindrome(10))
            println(solution.isPalindrome(Math.pow(2.0, 31.0).toInt() - 1))
        }
    }

    fun isPalindrome(x: Int): Boolean {
        return searchChars(x)
    }

    private fun searchChars(x: Int): Boolean {
        val xString = x.toString()

        for (i in xString.indices) {
            if (xString[i] != xString[xString.length - 1 - i]) {
                return false
            }
        }
        return true
    }

    private fun searchInt(x: Int): Boolean {
        val xDigits = mutableListOf<Int>()

        var remainingX = x
        while (remainingX > 0) {
            xDigits.add(remainingX % 10)
            remainingX /= 10
        }

        for (i in xDigits.indices) {
            if (xDigits[i] != xDigits[xDigits.size - 1 - i]) {
                return false
            }
        }
        return true
    }
}