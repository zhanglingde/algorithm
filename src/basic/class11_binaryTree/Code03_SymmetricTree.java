package basic.class11_binaryTree;


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
    public static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    public static boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    public static boolean isMirror(TreeNode t1, TreeNode t2) {
        if ((t1 == null && t2 != null) || (t1 != null && t2 == null)) {
            return false;
        }
        if (t1 == null && t2 == null) {
            return true;
        }
        return t1.value == t2.value && isMirror(t1.left,t2.right) && isMirror(t1.right,t2.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(3);

        System.out.println("isSymmetric(root) = " + isSymmetric(root));
    }
}
