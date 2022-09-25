package basic.class04;

import java.util.Arrays;

/**
 * 计数排序
 * @author zhangling 2021/7/13 10:40
 */
public class Code03_CountSort {

    // 计数排序
    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 找到数组中的最大数max，设置max个桶，以元素作下标，计算每个元素出现的次数
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int[] bucket = new int[max + 1];
        // 计算元素出现的次数, bucket 下标为元素，数组值为元素出现的次数
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }
        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                arr[i++] = j;
            }
        }
    }

    public static void main(String[] args) {
        int testTimes = 500000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = generateRandomArray(100, 50);
            int[] arr2 = arr1.clone();
            countSort(arr1);
            Arrays.sort(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("error...");
                break;
            }
        }
        System.out.println("end...");
    }

    public static int[] generateRandomArray(int maxValue, int maxSize) {
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
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
}
