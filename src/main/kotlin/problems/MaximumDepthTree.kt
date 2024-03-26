package problems

class MaximumDepthTree {
    companion object {
        fun execute() {
            val solution = MaximumDepthTree()
            println(solution.maxDepth(TreeNode.buildTreeNode(arrayOf(3, 9, 20, null, null, 15, 7), 0)))
            println(solution.maxDepth(TreeNode.buildTreeNode(arrayOf(1, null, 2), 0)))
        }
    }

    fun maxDepth(root: TreeNode?): Int {
        return traverse(root, 0)
    }

    fun traverse(root: TreeNode?, level: Int): Int {
        if (root != null) {
            return Math.max(
                traverse(root.left, level + 1),
                traverse(root.right, level + 1))
        } else {
            return level
        }
    }
}