package basic.class25;

import java.util.LinkedList;

/**
 * 假设一个固定大小为 W 的窗口，依次划过 arr，
 * 返回每一次滑出状况的最大值
 * 例如，arr = [4,3,5,4,3,3,6,7], W = 3
 * 返回：[5,5,5,4,6,7]
 */
public class Code01_SlidingWindowMaxArray {

    // 暴力的对数器方法
    public static int[] right(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int N = arr.length;
        int[] res = new int[N - w + 1];
        int index = 0;
        int L = 0;
        int R = w - 1;
        while (R < N) {
            int max = arr[L];
            // 遍历获取 W 窗口内最大值
            for (int i = L + 1; i <= R; i++) {
                max = Math.max(max, arr[i]);
            }
            // 窗口右移，最大值赋值给窗口最左边的值
            res[index++] = max;
            L++;
            R++;
        }
        return res;
    }

    // ==================== 滑动窗口 ========================================

    /**
     * @param arr
     * @param w   窗口中数据的量
     * @return
     */
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        // 定义 qMax 窗口最大值的更新结构（放下标）
        LinkedList<Integer> qMax = new LinkedList<Integer>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int R = 0; R < arr.length; R++) {
            // 1. 窗口右移，当遍历到的值有比窗口内的值大时，窗口内所有值都出队列，最大值入队列
            while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[R]) {
                qMax.pollLast();
            }
            qMax.addLast(R);
            // 2. 窗口右移导致左边不在窗口内，则左边的数出窗口，移出队列（超出窗口，左边指针右移）
            if (qMax.peekFirst() == R - w) {
                qMax.pollFirst();
            }
            // 3. 保存窗口内最大值
            if (R >= w - 1) {
                res[index++] = arr[qMax.peekFirst()];
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
            int[] ans1 = getMaxWindow(arr, w);
            int[] ans2 = right(arr, w);
            if (!isEqual(ans1, ans2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
