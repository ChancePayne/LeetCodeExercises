//236. Lowest Common Ancestor of a Binary Tree
class BinaryTreeLowestCommonAncestor {
    companion object {
        fun execute() {
            val solution = BinaryTreeLowestCommonAncestor()
            println(solution.lowestCommonAncestor(
                solution.buildTreeNode(arrayOf(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4), 0),
                TreeNode(5),
                TreeNode(4)
            ))
        }
    }

    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {

        searchNode(root, p, q)

        return TreeNode()
    }

    fun searchNode(root: TreeNode?, p: TreeNode?, q: TreeNode?): Boolean {
        if (root == null) {
            return false
        }

        if (root.left?.`val` == p?.`val` || root.left?.`val` == q?.`val`) {
            println(root.`val`)
            return true
        } else {
            if (searchNode(root.left, p, q) && searchNode(root.right, p, q)) {
                println(root.`val`)
                return false
            }
        }
        return false
    }



    fun buildTreeNode(array: Array<Int?>, i: Int): TreeNode? {
        var root: TreeNode? = null
        if (i < array.size && array[i] != null) {
            root = TreeNode(array[i]!!)
            root.left = buildTreeNode(array, 2 * i + 1)
            root.right = buildTreeNode(array, 2 * i + 2)
        }
        return root
    }
}

class TreeNode(var `val`: Int = 0) {
        var left: TreeNode? = null
        var right: TreeNode? = null


    fun inOrder() {
        inOrder(this)
    }
    private fun inOrder(root: TreeNode?) {
        if (root != null) {
            print("${root.`val`} ")
            inOrder(root.left)
            inOrder(root.right)
        }
    }
}