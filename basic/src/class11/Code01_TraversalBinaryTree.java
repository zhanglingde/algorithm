package class11;

// 遍历二查叉树
public class Code01_TraversalBinaryTree {
    public static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    //    先序
    public static void pre(TreeNode root){
        if (root == null) {
            return;
        }
        System.out.println(root.value);
        pre(root.left);
        pre(root.right);
    }

    //   中序
    public static void in(TreeNode root) {
        if (root == null) {
            return;
        }
        in(root.left);
        System.out.println(root.value);
        in(root.right);
    }

    //    后序
    public static void pos(TreeNode root) {
        if (root == null) {
            return;
        }
        pos(root.left);
        pos(root.right);
        System.out.println(root.value);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

//        pre(root);
//        in(root);
        pos(root);
    }
}
