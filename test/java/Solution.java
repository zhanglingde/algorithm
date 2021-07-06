import java.util.HashMap;

/**
 * @author zhangling 2021/7/6 11:39
 */
public class Solution {
    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length <= 2) {
            return null;
        }
        int[] arr = {0, 1};
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.get(target - nums[i]) != null) {
                arr[0] = i;
                arr[1] = map.get(target - nums[i]);
                if (arr[0] != arr[1]) {
                    break;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 4};
        int[] ints = twoSum(arr, 6);
        for (int anInt : ints) {
            System.out.println(anInt);

        }
    }

}
