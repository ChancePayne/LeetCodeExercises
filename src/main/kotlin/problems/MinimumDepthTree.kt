package problems

class MinimumDepthTree {
    companion object {
        fun execute() {
            val solution = MinimumDepthTree()
//            println(solution.minDepth(TreeNode.buildTreeNode(arrayOf(3,9,20,null,null,15,7), 0)))
//            println(solution.minDepth(TreeNode.buildTreeNode(arrayOf(2,null,3,null,4,null,5,null,6), 0)))
            println(solution.minDepth(TreeNode.buildTreeNode(arrayOf(1, 2, 3, 4, 5), 0)))
        }
    }

    fun minDepth(root: TreeNode?): Int {
        return if (root == null) {
            return 0
        } else {
            traverseNode(root, 0)
        }
    }

    private fun traverseNode(node: TreeNode, level: Int): Int {
        if (node.left == null && node.right == null) {
            return level + 1
        }
        return Math.min(node.left?.let { traverseNode(it, level + 1) } ?: Int.MAX_VALUE,
            node.right?.let { traverseNode(it, level + 1) } ?: Int.MAX_VALUE)
    }
}