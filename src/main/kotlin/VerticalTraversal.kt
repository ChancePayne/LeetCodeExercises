import Utils.Companion.print2dArray

class VerticalTraversal {
    companion object {
        fun execute() {
            val solution = VerticalTraversal()
            print2dArray(solution.verticalTraversal(TreeNode.buildTreeNode(arrayOf(3,9,20,null,null,15,7))))
            print2dArray(solution.verticalTraversal(TreeNode.buildTreeNode(arrayOf(1,2,3,4,6,5,7))))
        }
    }

    fun verticalTraversal(root: TreeNode?): List<List<Int>> {
        return if (root != null) {
            val map = HashMap<Int, MutableList<Int>>()
            val (min, max) = traverseTree(root, 0, map, Int.MAX_VALUE, Int.MIN_VALUE)

            val result = mutableListOf<List<Int>>()
            for(i in min..max) {
                map[i]?.let { result.add(it.sorted()) }
            }
            return result
        } else {
            emptyList<List<Int>>()
        }
    }

    fun traverseTree(node: TreeNode, position: Int, map: HashMap<Int, MutableList<Int>>, min: Int, max: Int): Pair<Int, Int> {
        var newMin = Math.min(min, position)
        var newMax = Math.max(max, position)

        if (map[position] == null) {
            map[position] = mutableListOf(node.`val`)
        } else {
            map[position]!!.add(node.`val`)
        }

        val (leftMin, leftMax) = node.left?.let { traverseTree(it, position - 1, map, newMin, newMax) } ?: Pair(Int.MAX_VALUE, Int.MIN_VALUE)
        val (rightMin, rightMax) = node.right?.let { traverseTree(it, position + 1, map, newMin, newMax) } ?: Pair(Int.MAX_VALUE, Int.MIN_VALUE)

        newMin = Math.min(Math.min(leftMin, rightMin), newMin)
        newMax = Math.max(Math.max(leftMax, rightMax), newMax)

        return Pair(newMin, newMax)
    }
}