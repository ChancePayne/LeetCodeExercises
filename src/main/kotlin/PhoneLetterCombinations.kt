class PhoneLetterCombinations {
    companion object {
        fun execute() {
            val solution = PhoneLetterCombinations()
            println(solution.letterCombinations("23"))
            println(solution.letterCombinations(""))
            println(solution.letterCombinations("2"))
        }
    }

    val LETTER_SETS = arrayOf(
        " ",
        "",
        "abc",
        "def",
        "ghi",
        "jkl",
        "mno",
        "pqrs",
        "tuv",
        "wxyz"
    )

    fun letterCombinations(digits: String): List<String> {
        var oldComboList: MutableList<String>
        var newComboList = mutableListOf<String>()

        digits.forEach { digit ->
            oldComboList = newComboList
            newComboList = mutableListOf()
            LETTER_SETS[digit.digitToInt()].forEach { letter ->
                if (oldComboList.isNotEmpty()) {
                    oldComboList.forEach {combo ->
                        newComboList.add(combo + letter)
                    }
                } else {
                    newComboList.add(letter.toString())
                }
            }
        }
        return newComboList
    }
}