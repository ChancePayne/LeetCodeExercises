// 234. Palindrome Linked List
class PalindromeLinkedList {
    companion object {
        fun execute() {
            val solution = PalindromeLinkedList()
//            println(solution.isPalindrome(ListNode.constructNodes(listOf(1, 2, 2, 1))))
//            println(solution.isPalindrome(ListNode.constructNodes(listOf(1, 2))))
//            println(solution.isPalindrome(ListNode.constructNodes(listOf(1, 2, 3))))
//            println(solution.isPalindrome(ListNode.constructNodes(listOf(1))))
            println(solution.isPalindrome(ListNode.constructNodes(listOf(1, 0, 1))))
        }
    }

    fun isPalindrome(head: ListNode?): Boolean {
        val list = mutableListOf<Int>()
        traverseNode(head!!, list)

        if (list.size == 1) {
            return true
        } else {
            if (list.size % 2 == 1) {
                val list1 = list.subList(0, list.size / 2 + 1)
                val list2 = list.subList(list.size / 2, list.size)

                return list1 == list2.reversed()
            } else {
                val list1 = list.subList(0, list.size / 2)
                val list2 = list.subList(list.size / 2, list.size)

                return list1 == list2.reversed()
            }
        }
    }

    fun traverseNode(node: ListNode, list: MutableList<Int>) {
        list.add(node.`val`)
        if (node.next == null) {
            return
        } else {
            traverseNode(node.next!!, list)
        }
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null

    companion object {
        fun constructNodes(intArray: List<Int>): ListNode? {
            if(intArray.isEmpty()) {
                return null
            } else {
                val node = ListNode(intArray.first())
                node.next = constructNodes(intArray.subList(1, intArray.size))
                return node
            }
        }
    }
}