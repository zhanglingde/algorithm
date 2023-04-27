package basic.class32;

/**
 * 给定一个数组arr，用户希望你实现如下三个方法
 * 1）void add(int L, int R, int V) :  让数组 arr[L…R] 上每个数都加上V
 * 2）void update(int L, int R, int V) :  让数组 arr[L…R] 上每个数都变成V
 * 3）int sum(int L, int R) : 让返回 arr[L…R] 这个范围整体的累加和
 * 怎么让这三个方法，时间复杂度都是 O(logN)
 */
public class Code01_SegmentTree {

    public static class SegmentTree {
        private int MAXN;
        private int[] arr;          // arr[] 为原序列的信息从0开始，但在arr里是从1开始的
        private int[] sum;          // sum[] 模拟线段树维护区间和
        private int[] lazy;         // lazy[] 为累加和懒惰标记
        private int[] change;       // change[] 为更新的值
        private boolean[] update;   // update[] 为更新慵懒标记

        public SegmentTree(int[] origin) {
            MAXN = origin.length + 1;
            arr = new int[MAXN]; // arr[0] 不用 从 1 开始使用
            for (int i = 1; i < MAXN; i++) {
                arr[i] = origin[i - 1];
            }
            sum = new int[MAXN << 2];           // 用来支持脑补概念中，某一个范围的累加和信息
            lazy = new int[MAXN << 2];          // 用来支持脑补概念中，某一个范围没有往下传递的附加任务
            change = new int[MAXN << 2];        // 用来支持脑补概念中，某一个范围有没有更新操作的任务
            update = new boolean[MAXN << 2];    // 用来支持脑补概念中，某一个范围更新任务，更新成了什么
        }

        /**
         * 更新当前节点累加和信息
         * @param rt
         */
        private void pushUp(int rt) {
            // 左孩子累加和(i * 2) + 右孩子累加和(i * 2 + 1)
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        /**
         * 之前的，所有懒增加，和懒更新，从父范围，发给左右两个子范围
         * 分发策略是什么
         *
         * @param rt 当前节点
         * @param ln 表示左子树元素结点个数
         * @param rn 表示右子树结点个数
         */
        private void pushDown(int rt, int ln, int rn) {
            // 下传懒标记到左右子节点，并更新节点信息
            if (update[rt]) {
                // 如果该节点打了更新标记，下传给左右子节点，并更新左右子节点的信息
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                lazy[rt << 1] = 0;
                lazy[rt << 1 | 1] = 0;
                sum[rt << 1] = change[rt] * ln;
                sum[rt << 1 | 1] = change[rt] * rn;
                update[rt] = false;
            }
            if (lazy[rt] != 0) {
                // 如果该节点打了懒标记，下传给左右子节点，并更新左右子节点的信息
                lazy[rt << 1] += lazy[rt];
                sum[rt << 1] += lazy[rt] * ln;
                lazy[rt << 1 | 1] += lazy[rt];
                sum[rt << 1 | 1] += lazy[rt] * rn;
                lazy[rt] = 0;
            }
        }

        /**
         * 建立线段树
         * 在初始化阶段，先把 sum 数组，填好; 在arr[l~r] 范围上，去 build，1~N，
         *
         * @param l 当前区间的左端点
         * @param r 当前区间的右端点
         * @param rt 当前节点的编号 这个范围在 sum 中的下标
         */
        public void build(int l, int r, int rt) {
            if (l == r) {
                // 如果区间长度为 1，即叶子节点，将该节点的值设为数组中对应位置的元素值
                sum[rt] = arr[l];
                return;
            }
            // 否则，将区间分为两部分，分别递归建立左右子树
            int mid = (l + r) >> 1;
            // 递归建立左子树，左子树的节点编号为 rt<<1
            build(l, mid, rt << 1);
            // 递归建立右子树，右子树的节点编号为 rt<<1|1
            build(mid + 1, r, rt << 1 | 1);
            // 根据左右子树的信息更新当前节点的信息
            pushUp(rt);
        }

        /**
         * 更新区间 [L,R] 的值为 C
         *
         * @param L  区间左边界
         * @param R  区间右边界
         * @param C  要更新的值
         * @param l  当前节点的左端点
         * @param r  当前节点的右端点
         * @param rt 当前节点的编号
         */
        public void update(int L, int R, int C, int l, int r, int rt) {
            // 1. 当前节点区间完全被覆盖
            if (L <= l && r <= R) {
                update[rt] = true;
                change[rt] = C;
                sum[rt] = C * (r - l + 1);
                lazy[rt] = 0;
                return;
            }
            // 2. 如果当前节点区间未被完全覆盖，需要往下递归更新（无法懒更新，要往下发）
            int mid = (l + r) >> 1;
            // 将当前节点的懒标记下传到子节点
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                // 往左子树递归更新
                update(L, R, C, l, mid, rt << 1);   // 乘 2
            }
            if (R > mid) {
                // 往右子树递归更新
                update(L, R, C, mid + 1, r, rt << 1 | 1); // 乘2+1
            }
            // 更新当前节点的值
            pushUp(rt);
        }

        /**
         * 对区间 [L,R] 中的每个元素加上 C
         *
         * @param L  操作的区间左边界
         * @param R  操作的区间右边界
         * @param C  要加的值
         * @param l  内部下发任务的左边界
         * @param r  内部下发任务的右边界
         * @param rt 内部下发任务加的值
         */
        public void add(int L, int R, int C, int l, int r, int rt) {
            // 1. 如果当前区间完全包含 [L,R],直接更新节点信息并打上懒标记
            if (L <= l && r <= R) {
                sum[rt] += C * (r - l + 1);
                lazy[rt] += C;
                return;
            }
            // 2. 否则，将当前区间分为两部分，递归左右子树
            int mid = (l + r) >> 1;
            // 3. 下传懒标记到左右子节点
            pushDown(rt, mid - l + 1, r - mid);
            // 如果 [L,R] 与左子区间有交际，递归处理左子树
            if (L <= mid) {
                add(L, R, C, l, mid, rt << 1);
            }
            // 处理右子树，如果 [L,R] 与右子区间有交集，递归处理右子树
            if (R > mid) {
                add(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            // 4. 调整累加和（根据左右子树的信息更新当前节点的信息）
            pushUp(rt);
        }

        /**
         * 查询区间 [L, R] 的累加和
         *
         * @param L  区间左端点
         * @param R  区间右端点
         * @param l  当前节点的左端点
         * @param r  当前节点的右端点
         * @param rt 当前节点的编号
         * @return 区间 [L, R] 的值的和
         */
        public long query(int L, int R, int l, int r, int rt) {
            // 1. 如果当前节点区间被完全包含在查询区间内，直接返回当前节点的值
            if (L <= l && r <= R) {
                return sum[rt];
            }
            int mid = (l + r) >> 1;
            // 2. 下传懒标记到左右子节点
            pushDown(rt, mid - l + 1, r - mid);
            long ans = 0;
            // 往左子树递归查询
            if (L <= mid) {
                ans += query(L, R, l, mid, rt << 1);
            }
            // 往右子树递归查询
            if (R > mid) {
                ans += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            // 3. 返回区间 [L,R] 的累加和
            return ans;
        }

    }

    /**
     * 暴力方法测试用
     */
    public static class Right {
        public int[] arr;

        public Right(int[] origin) {
            arr = new int[origin.length + 1];
            for (int i = 0; i < origin.length; i++) {
                arr[i + 1] = origin[i];
            }
        }

        public void update(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] = C;
            }
        }

        public void add(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] += C;
            }
        }

        public long query(int L, int R) {
            long ans = 0;
            for (int i = L; i <= R; i++) {
                ans += arr[i];
            }
            return ans;
        }

    }

    public static int[] genarateRandomArray(int len, int max) {
        int size = (int) (Math.random() * len) + 1;
        int[] origin = new int[size];
        for (int i = 0; i < size; i++) {
            origin[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return origin;
    }

    public static boolean test() {
        int len = 100;
        int max = 1000;
        int testTimes = 5000;
        int addOrUpdateTimes = 1000;
        int queryTimes = 500;
        for (int i = 0; i < testTimes; i++) {
            int[] origin = genarateRandomArray(len, max);
            SegmentTree seg = new SegmentTree(origin);
            int S = 1;
            int N = origin.length;
            int root = 1;
            seg.build(S, N, root);
            Right rig = new Right(origin);
            for (int j = 0; j < addOrUpdateTimes; j++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                int C = (int) (Math.random() * max) - (int) (Math.random() * max);
                // 50% 概率增加，50% 概率更新
                if (Math.random() < 0.5) {
                    seg.add(L, R, C, S, N, root);
                    rig.add(L, R, C);
                } else {
                    seg.update(L, R, C, S, N, root);
                    rig.update(L, R, C);
                }
            }
            // 查询结果是否相等
            for (int k = 0; k < queryTimes; k++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                long ans1 = seg.query(L, R, S, N, root);
                long ans2 = rig.query(L, R);
                if (ans1 != ans2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] origin = {2, 1, 1, 2, 3, 4, 5};
        SegmentTree seg = new SegmentTree(origin);     // 申请新的数组
        int S = 1; // 整个区间的开始位置，规定从 1 开始，不从 0 开始 -> 固定
        int N = origin.length; // 整个区间的结束位置，规定能到 N，不是 N-1 -> 固定
        int root = 1; // 整棵树的头节点位置，规定是 1，不是 0 -> 固定
        int L = 2; // 操作区间的开始位置 -> 可变
        int R = 5; // 操作区间的结束位置 -> 可变
        int C = 4; // 要加的数字或者要更新的数字 -> 可变
        // 区间生成，必须在[S,N]整个范围上build
        seg.build(S, N, root);
        // 区间修改，可以改变L、R和C的值，其他值不可改变
        seg.add(L, R, C, S, N, root);
        // 区间更新，可以改变L、R和C的值，其他值不可改变
        seg.update(L, R, C, S, N, root);
        // 区间查询，可以改变L和R的值，其他值不可改变
        long sum = seg.query(L, R, S, N, root);
        System.out.println(sum);

        System.out.println("对数器测试开始...");
        System.out.println("测试结果 : " + (test() ? "通过" : "未通过"));

    }

}