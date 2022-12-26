//236. Lowest Common Ancestor of a Binary Tree
class BinaryTreeLowestCommonAncestor {
    companion object {
        fun execute() {
            val solution = BinaryTreeLowestCommonAncestor()
            println(solution.lowestCommonAncestor(
                TreeNode.buildTreeNode(arrayOf(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4), 0),
                TreeNode(5),
                TreeNode(1)
            )?.`val` == 3
            )
            println(
                solution.lowestCommonAncestor(
                    TreeNode.buildTreeNode(arrayOf(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4), 0),
                    TreeNode(5),
                    TreeNode(4)
                )?.`val` == 5
            )
            println(
                solution.lowestCommonAncestor(
                    TreeNode.buildTreeNode(arrayOf(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4), 0),
                    TreeNode(7),
                    TreeNode(4)
                )?.`val` == 2
            )
            println(
                solution.lowestCommonAncestor(
                    TreeNode.buildTreeNode(arrayOf(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4), 0),
                    TreeNode(6),
                    TreeNode(8)
                )?.`val` == 3
            )
            println(
                solution.lowestCommonAncestor(
                    TreeNode.buildTreeNode(arrayOf(1, 2), 0),
                    TreeNode(1),
                    TreeNode(2)
                )?.`val` == 1
            )
        }
    }

    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        return searchNode(root, p, q).node
    }

    class FoundValues(var p: Boolean = false, var q: Boolean = false) {
        var node: TreeNode? = null

        fun bothFound() = p && q
    }

    fun searchNode(root: TreeNode?, p: TreeNode?, q: TreeNode?): FoundValues {
        val foundValues = FoundValues()

        if (foundValues.node == null) {
            val left = root?.left?.let { searchNode(it, p, q) }
            val right = root?.right?.let { searchNode(it, p, q) }

            if (left?.node != null) {
                return left
            } else if (right?. node != null) {
                return right
            } else {
                foundValues.q = left?.q ?: false || right?.q ?: false
                foundValues.p = left?.p ?: false || right?.p ?: false

                if (root?.`val` == p!!.`val`) {
                    foundValues.p = true
                }

                if (root?.`val` == q!!.`val`) {
                    foundValues.q = true
                }

                if (foundValues.bothFound()) {
                    foundValues.node = root
                }
            }
        }
        return foundValues
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

    companion object {
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
}