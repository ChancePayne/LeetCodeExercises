package problems

class MergeTwoSortedLists {
    companion object {
        fun execute() {
            val solution = MergeTwoSortedLists()
//            println(solution.mergeTwoLists(ListNode.fromArray(intArrayOf(1, 2, 4)), ListNode.fromArray(intArrayOf(1, 3, 4))))
//            println(solution.mergeTwoLists(ListNode.fromArray(intArrayOf()), ListNode.fromArray(intArrayOf())))
//            println(solution.mergeTwoLists(ListNode.fromArray(intArrayOf()), ListNode.fromArray(intArrayOf(0))))
            println(solution.mergeTwoLists(ListNode.fromArray(intArrayOf(1)), ListNode.fromArray(intArrayOf())))
        }
    }

    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        val finalList: ListNode?
        if (list1 == null && list2 != null) {
            finalList = ListNode(list2.`val`)
            traverse(finalList, list1, list2.next)
        } else if (list2 == null && list1 != null) {
            finalList = ListNode(list1.`val`)
            traverse(finalList, list1.next, list2)
        } else if (list1 == null && list2 == null) {
            finalList = null
        } else if ((list1!!.`val`) < (list2!!.`val`)) {
            finalList = ListNode(list1.`val`)
            traverse(finalList, list1.next, list2)
        } else {
            finalList = ListNode(list2.`val`)
            traverse(finalList, list1, list2.next)
        }
        return finalList
    }

    private fun traverse(finalList: ListNode, list1: ListNode?, list2: ListNode?) {
        if (list1 == null && list2 != null) {
            finalList.next = ListNode(list2.`val`)
            traverse(finalList.next!!, list1, list2.next)
        } else if (list2 == null && list1 != null) {
            finalList.next = ListNode(list1.`val`)
            traverse(finalList.next!!, list1.next, list2)
        } else if (list1 == null && list2 == null) {
            return
        } else if ((list1!!.`val`) < (list2!!.`val`)) {
            finalList.next = ListNode(list1.`val`)
            traverse(finalList.next!!, list1.next, list2)
        } else {
            finalList.next = ListNode(list2.`val`)
            traverse(finalList.next!!, list1, list2.next)
        }
    }

    class ListNode(var `val`: Int) {
        var next: ListNode? = null

        companion object {
            fun fromArray(array: IntArray): ListNode? {
                if (array.isEmpty()) {
                    return null
                }
                val listNode = ListNode(array.first())
                if (array.size > 1) {
                    traverse(listNode, array.copyOfRange(1, array.size))
                }
                return listNode
            }

            private fun traverse(node: ListNode, array: IntArray) {
                node.next = ListNode(array.first())
                if (array.size > 1) {
                    traverse(node.next!!, array.copyOfRange(1, array.size))
                }
            }
        }
    }
}