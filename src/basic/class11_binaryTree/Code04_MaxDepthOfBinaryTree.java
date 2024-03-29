package basic.class11_binaryTree;

/**
 * 计算二叉树最大深度
 * @author zhangling  2021/9/7 22:41
 */
// 测试链接：https://leetcode.com/problems/maximum-depth-of-binary-tree
public class Code04_MaxDepthOfBinaryTree {
    public static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.left = new TreeNode(3);
        root.right.left.left = new TreeNode(3);


        System.out.println("maxDepth(root) = " + maxDepth(root));

    }
}
