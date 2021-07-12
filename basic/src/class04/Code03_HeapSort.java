package class04;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author zhangling 2021/7/12 13:51
 */
public class Code03_HeapSort {

    // 堆排序
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 数组 -> 大根堆，O(N*logN)
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        // 额外空间复杂度 O(1)
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        // O(N*logN)
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            // 只有左孩子，largest = left
            // 同时有左节点和右节点，右节点的值 <= 左节点的值 largest = left
            // 同时有左节点和右节点，右节点的值 > 左节点的值 largest = left + 1
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 父节点与孩子节点中较大的那个比较
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {   // 子节点不比父节点大
                break;
            }
            swap(arr,index,largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        //int[] arr = {6, 1, 53, 16, 99, 95, 98, 90, 49, 61};
        //heapSort(arr);
        //printArray(arr);
        int testTimes = 500000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = generateRandomArray(100, 50);
            int[] arr2 = arr1.clone();
            int[] arr3 = arr1.clone();
            heapSort(arr1);
            Arrays.sort(arr2);
            if (!isEqual(arr1, arr2)) {
                printArray(arr3);
                System.out.println("error...");
                break;
            }
        }
        System.out.println("end...");

    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static int[] generateRandomArray(int maxValue, int maxSize) {
        int[] arr = new int[(int)(Math.random()*(maxSize+1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }
    public static boolean isEqual(int[] arr1,int[] arr2){
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
