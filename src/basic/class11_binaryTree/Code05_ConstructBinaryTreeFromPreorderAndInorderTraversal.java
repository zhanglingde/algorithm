package basic.class11_binaryTree;

/**
 * 知道一棵二叉树的先序遍历和中序遍历，且先序遍历和中序遍历中没有重复值
 * 构建二叉树并返回头节点
 *
 * @author zhangling  2021/9/7 23:17
 */
//测试链接：https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
public class Code05_ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    public static TreeNode buildTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        return f(pre, 0, pre.length - 1, in, 0, in.length - 1);

    }

    public static TreeNode f(int[] pre, int L1, int R1, int[] in, int L2, int R2) {
        if (L1 > R1) {      // 递归退出条件
            return null;
        }
        if (L1 == R1) {
            return new TreeNode(pre[L1]);
        }
        TreeNode head = new TreeNode(pre[L1]);
        int find = L2;
        while (in[find] != pre[L1]) {
            find++;
        }

        // 注意边界
        head.left = f(pre, L1 + 1, L1 + find - L2, in, L2, find - 1);
        head.right = f(pre, L1 + find - L2 + 1, R1, in, find + 1, R2);

        return head;
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 5, 3, 6, 7};
        int[] in = {4, 2, 5, 1, 6, 3, 7};

        TreeNode head = buildTree(pre, in);
        System.out.println("end...");
    }


}
