package primary.class06;

/**
 * 判断一棵树是否是镜面树
 *      1
 *     /\
 *    2  2
 *   /\ /\
 *  3 1 1 3
 */
// 测试链接：https://leetcode.com/problems/symmetric-tree
public class Code03_SymmetricTree {

    public static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static boolean isSymmetric(TreeNode root) {
        return isMirror(root,root);
    }

    private static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null ^ t2 == null) {  // 一棵树左右节点不同
            return false;
        }
        if (t1 == null && t2 == null) {
            return true;
        }
        return t1.val == t2.val && isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }
}
