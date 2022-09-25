package primary.class06;

/**
 * 判断两棵树的结构相同
 */
// 测试链接：https://leetcode.com/problems/same-tree
public class Code02_SameTree {

    public static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null ^ q == null) { // 一个为空一个不为空
            return false;
        }
        if (p == null && q == null) {
            return true;
        }
        // 两棵树都不为空
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
