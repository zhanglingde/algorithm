package primary.class02;

import java.util.Arrays;

/**
 * 对数器
 *
 * @author zhangling  2021/3/11 20:42
 */
public class Code04_Comp {

    /**
     * @param maxSize  数组的最大长度
     * @param maxValue 数组中的最大数
     * @return 返回一个随机数组
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    /**
     * 拷贝数组
     * @param arr
     * @return
     */
    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    /**
     * 判断两个数组是否相等
     * @return true 相等
     */
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

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    /**
     * 使用对数器测试
     * @param args
     */
    public static void main(String[] args) {
        int maxSize = 10;
        int maxValue = 30;
        int testTimes = 1000000;

        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = generateRandomArray(maxSize,maxValue);
            int[] error = copyArray(arr1);
            int[] arr2 = copyArray(arr1);
            selectSort(arr1);
            Arrays.sort(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("error1");
                printArray(error);
                break;
            }
        }
        System.out.println("测试结束");
    }


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 选择排序
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) { // 先找边界条件
            return;
        }
        // 0~n-1 取出最小值
        // 1~n-1 取出最小值
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int minValueIndex = i;         // 最小值索引作交换用
            for (int j = i + 1; j < N; j++) {         // 找到最小值的索引
                minValueIndex = arr[minValueIndex] > arr[j] ? j : minValueIndex;
            }
            swap(arr, minValueIndex, i);
        }

    }
}
