package class06;

/**
 * 求一棵树的最大深度
 */
// 测试链接：https://leetcode.com/problems/maximum-depth-of-binary-tree
public class Code04_MaximumDepthOfBinaryTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    // 返回一棵树的最大高度
    public static int maxDepth(TreeNode head) {
        if (head == null) {
            return 0;
        }

        return Math.max(maxDepth(head.left), maxDepth(head.right)) + 1;
    }
}
