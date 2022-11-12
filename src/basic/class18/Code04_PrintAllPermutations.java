package basic.class18;

import java.util.ArrayList;
import java.util.List;


/**
 * 打印一个字符串的全部排列
 */
public class Code04_PrintAllPermutations {

	public static List<String> permutation1(String s) {
		List<String> ans = new ArrayList<>();
		if (s == null || s.length() == 0) {
			return ans;
		}
		char[] str = s.toCharArray();
		ArrayList<Character> rest = new ArrayList<Character>();
		for (char cha : str) {
			rest.add(cha);
		}
		String path = "";
		f(rest, path, ans);
		return ans;
	}

    /**
     * 普通递归
     *
     * @param rest 剩下的字符
     * @param path 拼接的临时结果
     * @param ans 最终保存的排列结果
     */
	public static void f(ArrayList<Character> rest, String path, List<String> ans) {
		if (rest.isEmpty()) {
			ans.add(path);
		} else {
			int N = rest.size();
			for (int i = 0; i < N; i++) {
                // 第一个字符串可以是任何一个，然后后面持续递归（全排列，把当前字符移除）
				char cur = rest.get(i);
				rest.remove(i);
				f(rest, path + cur, ans);
                // 恢复现场，下一个字符循环的时候，需要用到
				rest.add(i, cur);
			}
		}
	}

    /**
     * 优化后的递归
     * @param s
     * @return
     */
	public static List<String> permutation2(String s) {
		List<String> ans = new ArrayList<>();
		if (s == null || s.length() == 0) {
			return ans;
		}
		char[] str = s.toCharArray();
		g1(str, 0, ans);
		return ans;
	}

    /**
     * 优化后的递归 1
     *
     * @param str
     * @param index
     * @param ans
     */
	public static void g1(char[] str, int index, List<String> ans) {
		if (index == str.length) {
            // 交换结果结束
			ans.add(String.valueOf(str));
		} else {
			for (int i = index; i < str.length; i++) {
				swap(str, index, i);
				g1(str, index + 1, ans);
                // 恢复现场
				swap(str, index, i);
			}
		}
	}

	public static List<String> permutation3(String s) {
		List<String> ans = new ArrayList<>();
		if (s == null || s.length() == 0) {
			return ans;
		}
		char[] str = s.toCharArray();
		g2(str, 0, ans);
		return ans;
	}

    /**
     * 去重后的排列
     *
     * @param str
     * @param index
     * @param ans
     */
	public static void g2(char[] str, int index, List<String> ans) {
		if (index == str.length) {
			ans.add(String.valueOf(str));
		} else {
            // 256 个 ASCII
			boolean[] visited = new boolean[256];
			for (int i = index; i < str.length; i++) {
                // index 和 i 交换前判断 i 是否位置的字符是否使用过，使用过不再使用
				if (!visited[str[i]]) {
					visited[str[i]] = true;
					swap(str, index, i);
					g2(str, index + 1, ans);
					swap(str, index, i);
				}
			}
		}
	}

	public static void swap(char[] chs, int i, int j) {
		char tmp = chs[i];
		chs[i] = chs[j];
		chs[j] = tmp;
	}

	public static void main(String[] args) {
		String s = "acc";
		List<String> ans1 = permutation1(s);
		for (String str : ans1) {
			System.out.println(str);
		}
		System.out.println("=======");
		List<String> ans2 = permutation2(s);
		for (String str : ans2) {
			System.out.println(str);
		}
		System.out.println("=======");
		List<String> ans3 = permutation3(s);
		for (String str : ans3) {
			System.out.println(str);
		}

	}

}
