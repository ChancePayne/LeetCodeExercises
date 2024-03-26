package problems

// 202. Easy
class HappyNumber {
    companion object {
        fun execute() {
            val solution = HappyNumber()
            println(solution.isHappy(1498149937))
            println(solution.isHappy(1138366084))
            println(solution.isHappy(1252030624))
            println(solution.isHappy(1363317348))
            println(solution.isHappy(1334741449))
            println(solution.isHappy(363336090))
            println(solution.isHappy(1639484248))
            println(solution.isHappy(813933544))
            println(solution.isHappy(1824705777))
            println(solution.isHappy(2132983744))
            println(solution.isHappy(896068774))
            println(solution.isHappy(25334656))
            println(solution.isHappy(1356266608))
            println(solution.isHappy(1888168452))
            println(solution.isHappy(182525862))
            println(solution.isHappy(572455600))
            println(solution.isHappy(1772939627))
            println(solution.isHappy(694945443))
            println(solution.isHappy(1196711740))
            println(solution.isHappy(1191467663))
            println(solution.isHappy(221267726))
            println(solution.isHappy(124171423))
            println(solution.isHappy(463691342))
            println(solution.isHappy(2103715897))
            println(solution.isHappy(1142460297))
            println(solution.isHappy(913934608))
            println(solution.isHappy(943788644))
            println(solution.isHappy(322685781))
            println(solution.isHappy(2102679397))
            println(solution.isHappy(1105311857))
            println(solution.isHappy(785269606))
            println(solution.isHappy(1183233441))
            println(solution.isHappy(2000192779))
            println(solution.isHappy(1192298880))
            println(solution.isHappy(1415062296))
            println(solution.isHappy(540104303))
            println(solution.isHappy(790708126))
            println(solution.isHappy(724939593))
            println(solution.isHappy(385689441))
            println(solution.isHappy(1125352469))
            println(solution.isHappy(1201110513))
            println(solution.isHappy(566508569))
            println(solution.isHappy(824426986))
            println(solution.isHappy(1502976631))
            println(solution.isHappy(1501845145))
            println(solution.isHappy(1468659167))
            println(solution.isHappy(590428874))
            println(solution.isHappy(1166431695))
            println(solution.isHappy(1732711240))
            println(solution.isHappy(1994491))
        }
    }

    fun isHappy(n: Int): Boolean {
        var sum = n
        val recentSums = IntArray(10)
        var index = 0

        while(sum != 1) {
            sum = getAndSumDigits(sum)

            if (recentSums.contains(sum)) {
               return false
            } else {
                recentSums[index++] = sum
                if (index == recentSums.size) {
                    index = 0
                }
            }
        }

        return true
    }

    private fun getAndSumDigits(n: Int): Int {
        var number = n
        var sum = 0

        while (number > 0) {
            sum += Math.pow((number % 10).toDouble(), 2.0).toInt()

            number /= 10
        }
        return sum
    }

    private fun getDigits(n: Int): IntArray {
        var number = n
        val digits = mutableListOf<Int>()

        while (number > 0) {
            digits.add(number % 10)

            number /= 10
        }
        return digits.toIntArray()
    }

    private fun sumDigits(digits: IntArray): Int {
        var sum = 0
        digits.forEach { sum += it * it }
        return sum
    }

}