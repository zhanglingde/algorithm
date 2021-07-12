package class04;

import java.util.Comparator;

/**
 * @author zhangling 2021/7/12 14:57
 */
public class Code01_Comparator {


    public static <T> void sort(T[] arr, Comparator<T> comparator) {
        for (int i = 0; i < arr.length; i++) {
            int minValueIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minValueIndex = comparator.compare(arr[minValueIndex], arr[j]) > 0 ? j : minValueIndex;
            }
            swap(arr, i, minValueIndex);
        }
    }

    public static <T> void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static class Cat {
        private int weight;
        private int height;

        public Cat(int weight, int height) {
            this.weight = weight;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Cat{" +
                    "weight=" + weight +
                    ", height=" + height +
                    '}';
        }
    }

    public static class CatWeightComparator implements Comparator<Cat> {
        @Override
        public int compare(Cat c1, Cat c2) {
            if (c1.weight < c2.weight) {
                return -1;
            } else if (c1.weight > c2.weight) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        Cat[] cats = new Cat[4];
        cats[0] = new Cat(5, 10);
        cats[1] = new Cat(3, 10);
        cats[2] = new Cat(6, 10);
        cats[3] = new Cat(1, 10);
        printArray(cats);
        sort(cats, new CatWeightComparator());
        printArray(cats);
    }

    public static <T> void printArray(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
