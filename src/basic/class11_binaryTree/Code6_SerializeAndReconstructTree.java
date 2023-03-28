package basic.class11_binaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树可以通过 先序、后序和按层遍历的方式序列化
 * 无法通过中序的方式实现序列化和反序列化
 */
public class Code6_SerializeAndReconstructTree {

    public static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    // 先序序列化
    public static Queue<String> preSerial(TreeNode root) {
        Queue<String> ans = new LinkedList<>();
        pres(root, ans);
        return ans;
    }

    private static void pres(TreeNode root, Queue<String> ans) {
        if (root == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(root.value));
            pres(root.left, ans);
            pres(root.right, ans);
        }
    }

    // 先序 反序列化
    public static TreeNode buildByPreQueue(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        return preb(queue);
    }

    private static TreeNode preb(Queue<String> queue) {
        String value = queue.poll();
        if (value == null) {
            return null;
        }
        TreeNode head = new TreeNode(Integer.valueOf(value));
        head.left = preb(queue);
        head.right = preb(queue);
        return head;
    }

    // 后序 序列化
    public static Queue<String> posSerial(TreeNode head) {
        Queue<String> ans = new LinkedList<>();
        poss(head,ans);
        return ans;

    }

    public static void poss(TreeNode head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        }else {
            poss(head.left,ans);
            poss(head.right,ans);
            ans.add(String.valueOf(head.value));
        }
    }

    // 后序 反序列化
    public static TreeNode buildByPosSerial(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        Stack<String> stack = new Stack<>();
        // 左右头 -> 头右左
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }
        return posb(stack);
    }

    private static TreeNode posb(Stack<String> stack) {
        String value = stack.pop();
        if (value == null) {
            return null;
        }
        TreeNode head = new TreeNode(Integer.valueOf(value));
        head.right= posb(stack);
        head.left = posb(stack);
        return head;
    }

    // 按层遍历 序列化
    public static Queue<String> levelSerial(TreeNode head) {
        Queue<String> ans = new LinkedList<>();
        if (head == null) {
            ans.add(null);
        }else {
            ans.add(String.valueOf(head.value));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                    ans.add(String.valueOf(node.left.value));
                }else {
                    ans.add(null);
                }
                if (node.right != null) {
                    queue.add(node.right);
                    ans.add(String.valueOf(node.right.value));
                }else {
                    ans.add(null);
                }
            }
        }
        return ans;
    }

    public static TreeNode buildByLevelSerial(Queue<String> levelList) {
        if (levelList.isEmpty()) {
            return null;
        }
        TreeNode head = generateNode(levelList.poll());
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        TreeNode cur = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            cur.left = generateNode(levelList.poll());
            cur.right = generateNode(levelList.poll());
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }

        return head;
    }

    public static TreeNode generateNode(String value) {
        if (value == null) {
            return null;
        }
        return new TreeNode(Integer.valueOf(value));
    }

    /*
             1
            / \
          2    4
         / \  / \
      null 3 5  null
     */
    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.left.right = new TreeNode(3);
        head.right = new TreeNode(4);
        head.right.left = new TreeNode(5);

        // 先序
        Queue<String> preSerial = preSerial(head);
        printQueue(preSerial);
        TreeNode root = buildByPreQueue(preSerial);

        // 后序 序列化
        Queue<String> posSerial = posSerial(head);
        printQueue(posSerial);
        TreeNode root2 = buildByPosSerial(posSerial);

        // 按层遍历
        Queue<String> levelList = levelSerial(head);
        printQueue(levelList);
        TreeNode root3 = buildByLevelSerial(levelList);
        System.out.println();


    }

    public static void printQueue(Queue<String> queue) {
        for (String s : queue) {
            System.out.print(s + " ");
        }
        System.out.println();
    }


}
