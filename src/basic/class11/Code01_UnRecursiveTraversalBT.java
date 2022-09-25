package basic.class11;

import java.util.Stack;

/**
 * 非递归方式遍历二叉树
 *
 * @author zhangling  2021/9/12 16:30
 */
public class Code01_UnRecursiveTraversalBT {
    public static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    // 先序
    public static void pre(TreeNode head) {
        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.add(head);
            while (!stack.isEmpty()) {
                TreeNode cur = stack.pop();
                System.out.print(cur.value + " ");
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }
        System.out.println();
    }

    // 中序
    public static void in(TreeNode head) {
        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode cur = head;
            while (!stack.isEmpty() || cur != null) {
                if (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }else {
                    cur = stack.pop();
                    System.out.print(cur.value + " ");
                    cur = cur.right;
                }

            }

        }
        System.out.println();
    }

    // 后序 头右左 -> 左右头
    public static void pos(TreeNode head) {
        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            Stack<TreeNode> resStack = new Stack<>();
            stack.add(head);
            while (!stack.isEmpty()) {
                TreeNode cur = stack.pop();
                resStack.push(cur); // 头 右 左
                if (cur.left != null) {
                    stack.push(cur.left);
                }
                if (cur.right != null) {
                    stack.push(cur.right);
                }
            }
            // 左 右 头
            while (!resStack.isEmpty()) {
                System.out.print(resStack.pop().value + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        pre(root);
        in(root);
        pos(root);
    }
}
