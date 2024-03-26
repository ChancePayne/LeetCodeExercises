package problems

class RomanToInteger {
    companion object {
        fun execute() {
            val solution = RomanToInteger()
            println(solution.romanToInt("III"))
            println(solution.romanToInt("LVIII"))
            println(solution.romanToInt("MCMXCIV"))
        }
    }

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
