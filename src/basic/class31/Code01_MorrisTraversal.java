package basic.class31;

/**
 * Morris 遍历
 * <p>
 * 假设来到当前节点 cur，开始时 cur 来到头节点位置
 * 1）如果 cur 没有左孩子，cur 向右移动(cur = cur.right)
 * 2）如果 cur 有左孩子，找到左子树上最右的节点 mostRight：
 *      a.如果 mostRight 的右指针指向空，让其指向 cur，然后 cur 向左移动(cur = cur.left)
 *      b.如果 mostRight 的右指针指向 cur，让其指向 null，然后 cur 向右移动(cur = cur.right)
 * 3）cur 为空时遍历停止
 */
public class Code01_MorrisTraversal {

    public static class Node {
        public int value;
        Node left;
        Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void process(Node root) {
        if (root == null) {
            return;
        }
        // 1. 左子树递归
        process(root.left);
        // 2. 右子树递归
        process(root.right);
        // 3
    }

    // Morris 遍历
    public static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            // 1. 找到 cur 节点的左子树上最右的节点 mostRight
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 2. 如果 mostRight.right 为空，说明是第一次遍历到 cur 节点，则将 mostRight.right 指向 cur 节点(方便后序恢复)，并将 cur 节点左移
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    // 3. 如果mostRight.right不为空，说明是第二次遍历到cur节点，则把mostRight.right置为空，并将cur节点右移
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }

    // Morris 先序遍历
    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight;     // 当前节点的左子树上的最右节点
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight == null) {
                // 1. 当前节点没有左子树，直接输出当前节点的值
                System.out.print(cur.value + " ");

            } else {
                // 2. 找到 cur 节点的左子树上最右的节点 mostRight
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 3. 如果 mostRight.right 为空，说明是第一次遍历到cur节点，则打印cur节点的值，并将 mostRight.right 指向 cur 节点（方便遍历完左子树后返回当前节点），并将 cur 节点左移
                if (mostRight.right == null) {
                    System.out.print(cur.value + " ");
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    // 4. 如果 mostRight.right 不为空，说明是第二次遍历到 cur 节点，则把 mostRight.right 置为空，
                    mostRight.right = null;
                }
            }
            // 5. 当前节点右移
            cur = cur.right;
        }
        System.out.println();
    }

    // Morris 中序遍历 （左 头 右）
    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                // 1. 存在左子树，找到 cur 节点的左子树上最右的节点 mostRight
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 2. 说明还没遍历到该节点
                if (mostRight.right == null) {
                    mostRight.right = cur;    // 方便遍历完左子树后返回 cur 节点
                    cur = cur.left;
                    continue;
                } else {
                    // 3. 如果 mostRight.right 不为空，指向当前节点，说明已经遍历完当前节点的左子树，则把 mostRight.right 置为空
                    mostRight.right = null;
                }
            }
            // 4. 输出当前节点
            System.out.print(cur.value + " ");
            // 5. 当前节点右移
            cur = cur.right;
        }
        System.out.println();
    }

    // Morris 后序遍历
    public static void morrisPos(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                // 1. 找到 cur 节点的左子树上最右的节点 mostRight
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 2. 如果 mostRight.right 为空，说明是第一次遍历到cur节点，将 mostRight.right 指向 cur 节点（方便遍历完左子树后返回当前节点），并将 cur 节点左移
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    // 3. 如果 mostRight.right 不为空，说明左子树已经遍历完，需要逆序打印左子树的右边界
                    mostRight.right = null;
                    // 4. 能到一个节点两次的时候，逆序打印左树的右边界
                    printEdge(cur.left);
                }
            }
            // 5. 当前节点没有左子树时，向右移动
            cur = cur.right;
        }
        // 6. morrios 遍历完后，逆序打印整棵树的右边界
        printEdge(head);
        System.out.println();
    }

    // 逆序打印节点的右边界
    public static void printEdge(Node head) {
        Node tail = reverseEdge(head);  // 将右边界逆序，返回最后一个节点
        Node cur = tail;
        while (cur != null) {   // 从最后一个节点开始向右遍历，逐个打印节点的值
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);  // 再将右边界恢复成原来的顺序
    }

    // 将节点的右边界逆序，并返回最后一个节点
    public static Node reverseEdge(Node from) {
        Node pre = null;
        Node next = null;
        while (from != null) {  // 从节点 from 开始逆序遍历右边界
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre; // 返回逆序后的最后一个节点
    }

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    // morris 遍历判断一棵树是否是搜索二叉树（搜索二叉树遍历一遍后是从小到大的）
    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        Node cur = head;
        Node mostRight = null;
        Integer pre = null;
        boolean ans = true;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            // 需要 morris 遍历完，恢复原树
            if (pre != null && pre >= cur.value) {
                ans = false;
            }
            pre = cur.value;
            cur = cur.right;
        }
        return ans;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        printTree(head);
        System.out.println("Morris 先序：");
        morrisIn(head);
        System.out.println("Morris 中序：");
        morrisPre(head);
        System.out.println("Morris 后序：");
        morrisPos(head);
        printTree(head);

    }

}
