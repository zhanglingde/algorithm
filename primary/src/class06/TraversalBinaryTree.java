package class06;

/**
 * 遍历二叉树
 */
public class TraversalBinaryTree {

    // 二叉树结构
    public static class Node{
        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    // 先序遍历：头左右
    private static void pre(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.val);
        pre(head.left);
        pre(head.right);
    }

    // 中序遍历：左头右
    public static void in(Node head) {
        if (head == null) {
            return;
        }
        in(head.left);
        System.out.println(head.val);
        in(head.right);
    }

    // 后序遍历：左右头
    public static void pos(Node head) {
        if (head == null) {
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.println(head.val);
    }


    /**
     *     1
     *    / \
     *   2   3
     *  /\  /\
     * 4 5 6 7
     * @param args
     */
    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        // 遍历
        pre(head);
        System.out.println("============");
        in(head);
        System.out.println("============");
        pos(head);
        System.out.println("============");
    }


}
