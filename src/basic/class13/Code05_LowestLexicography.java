package basic.class13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * 贪心算法：给出一组字段串，将其组合在一起，排出字典序最小的组合
 */
public class Code05_LowestLexicography {

    public static void main(String[] args) {
        // String[] str = {"b","ba"};
        // System.out.println(lowestString2(str));

        int arrLen = 6;
        int strLen = 5;
        int testTimes = 10000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            if (!lowestString1(arr1).equals(lowestString2(arr2))) {
                for (String str : arr1) {
                    System.out.print(str + ",");
                }
                System.out.println();
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


    /**
     * 对数器：暴力获取所有组合方式，取字典序最小的
     */
	public static String lowestString1(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		TreeSet<String> ans = process(strs);
		return ans.size() == 0 ? "" : ans.first();
	}

	// strs 中所有字符串全排列，返回所有可能的结果
	public static TreeSet<String> process(String[] strs) {
		TreeSet<String> ans = new TreeSet<>();
		if (strs.length == 0) {
			ans.add("");
			return ans;
		}
		for (int i = 0; i < strs.length; i++) {
			String first = strs[i];
			String[] nexts = removeIndexString(strs, i);
			TreeSet<String> next = process(nexts);
			for (String cur : next) {
				ans.add(first + cur);
			}
		}
		return ans;
	}

	// {"abc", "cks", "bct"}
	// 0 1 2
	// removeIndexString(arr , 1) -> {"abc", "bct"}
	public static String[] removeIndexString(String[] arr, int index) {
		int N = arr.length;
		String[] ans = new String[N - 1];
		int ansIndex = 0;
		for (int i = 0; i < N; i++) {
			if (i != index) {
				ans[ansIndex++] = arr[i];
			}
		}
		return ans;
	}

	public static class MyComparator implements Comparator<String> {
		@Override
		public int compare(String a, String b) {
            // 如果仅仅是对所有单个字符串进行排序，则有反例  b  ba，按单个字符串排序最后组成的字符串是 bba,单该字符串比 bab 的字典序大
			// return a.compareTo(b);
			return (a + b).compareTo(b + a);
		}
	}

    /**
     * 提出贪心策略：
     * if (a+b 字典序 < b+a 字典序） 则组成 ab
     * if (a+b 字典序 > b+a 字典序） 则组成 ba
     *
     * @return 返回组成的字符串是所有组合里字典序最小的
     */
	public static String lowestString2(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		Arrays.sort(strs, new MyComparator());
		String res = "";
		for (int i = 0; i < strs.length; i++) {
			res += strs[i];
		}
		return res;
	}

	// for test
	public static String generateRandomString(int strLen) {
		char[] ans = new char[(int) (Math.random() * strLen) + 1];
		for (int i = 0; i < ans.length; i++) {
			int value = (int) (Math.random() * 5);
			ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
		}
		return String.valueOf(ans);
	}

	// for test
	public static String[] generateRandomStringArray(int arrLen, int strLen) {
		String[] ans = new String[(int) (Math.random() * arrLen) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = generateRandomString(strLen);
		}
		return ans;
	}

	// for test
	public static String[] copyStringArray(String[] arr) {
		String[] ans = new String[arr.length];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = String.valueOf(arr[i]);
		}
		return ans;
	}



}