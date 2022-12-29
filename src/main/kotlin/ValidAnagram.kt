class ValidAnagram {
    companion object {
        fun execute() {
            val solution = ValidAnagram()
//            println(solution.isAnagram(s = "anagram", t = "nagaram") == true)
            println(solution.isAnagram(s = "rat", t = "car") == false)
//            println(solution.isAnagram(s = "aacc", t = "ccac") == false)
        }
    }

    fun isAnagram(s: String, t: String): Boolean {
        return sorting(s, t)
    }

    private fun hashMap(s: String, t: String): Boolean {
        val sSet = HashMap<Char, Int>()
        val tSet = HashMap<Char, Int>()

        return if (s.length == t.length) {
            for (i in s.indices) {
                sSet[s[i]] = sSet[s[i]]?.plus(1) ?: 1
                tSet[t[i]] = tSet[t[i]]?.plus(1) ?: 1
            }
            sSet == tSet
        } else {
            false
        }
    }
    private fun sorting(s: String, t: String): Boolean {
        return if (s.length == t.length) {
            val sArray = s.toCharArray()
            sArray.sort()
            val tArray = t.toCharArray()
            tArray.sort()
            var doStringsMatch = true
            for (i in s.indices) {
                if (sArray[i] != tArray[i]) {
                    doStringsMatch = false
                    break
                }
            }
            doStringsMatch
        } else {
            false
        }
    }
}