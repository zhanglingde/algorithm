package class06;

import java.util.HashMap;

/**
 * 知道一棵二叉树的先序遍历和中序遍历，且先序遍历和中序遍历中没有重复值
 * 构建二叉树并返回头节点
 *
 * @author zhangling 2021/3/5 13:59
 */
//测试链接：https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
public class Code05_ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * @param pre 先序遍历的数组
     * @param in  中序遍历的数组
     * @return
     */
    public static TreeNode buildTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        return f(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    /**
     * @param pre 先序结果是pre[L1...R1]
     * @param in  中序结果是in[L2...R2]
     * @return 返回整棵树的头节点
     */
    public static TreeNode f(int[] pre, int L1, int R1, int[] in, int L2, int R2) {
        /*
         *  1
         *   \
         *    2
         *     \
         *      3
         * 该树 先序：1，2，3；中序：1，2，3
         * */
        if (L1 > R1) {
            return null;  // 跳出递归的条件
        }
        if (L1 == R1) {  // 该节点没有子节点了，就是头节点，返回头节点
            return new TreeNode(pre[L1]);
        }
        TreeNode head = new TreeNode(pre[L1]);
        int find = L2;
        while (in[find] != pre[L1]) {       // 遍历中序数组，直到找到先序中的头节点
            find++;
        }

        head.left = f(pre, L1 + 1, L1 + find - L2, in, L2, find - 1);
        head.right = f(pre, L1 + find - L2 + 1, R1, in, find + 1, R2);
        return head;
    }


    // 使用hash表记录需要遍历的树的位置，以空间换时间，提高效率
    public static TreeNode buildTree2(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        HashMap<Integer,Integer> valueIndexMap = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            valueIndexMap.put(in[i], i);
        }
        return g(pre, 0, pre.length - 1, in, 0, in.length - 1,valueIndexMap);
    }

    public static TreeNode g(int[] pre, int L1, int R1, int[] in, int L2, int R2,HashMap<Integer,Integer> valueIndexMap) {
        /*
         *  1
         *   \
         *    2
         *     \
         *      3
         * 该树 先序：1，2，3；中序：1，2，3
         * */
        if (L1 > R1) {
            return null;  // 跳出递归的条件
        }
        if (L1 == R1) {  // 该节点没有子节点了，就是头节点，返回头节点
            return new TreeNode(pre[L1]);
        }
        TreeNode head = new TreeNode(pre[L1]);
        int find = valueIndexMap.get(head);       // 从中序中找到头节点位置

        head.left = g(pre, L1 + 1, L1 + find - L2, in, L2, find - 1,valueIndexMap);
        head.right = g(pre, L1 + find - L2 + 1, R1, in, find + 1, R2,valueIndexMap);
        return head;
    }
}
