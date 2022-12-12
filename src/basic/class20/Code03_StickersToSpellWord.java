package basic.class20;

import java.util.HashMap;

/**
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
 * arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * ba + ba + c  3  abcd + abcd 2  abcd+ba 2
 * 所以返回2
 *
 * 本题测试链接：https://leetcode.com/problems/stickers-to-spell-word
 */
public class Code03_StickersToSpellWord {

	public static int minStickers1(String[] stickers, String target) {
		int ans = process1(stickers, target);
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	// 所有贴纸stickers，每一种贴纸都有无穷张
	// target
	// 最少张数
	public static int process1(String[] stickers, String target) {
		if (target.length() == 0) {
			return 0;
		}
		int min = Integer.MAX_VALUE;
		for (String first : stickers) {
			String rest = minus(target, first);
			if (rest.length() != target.length()) {
				min = Math.min(min, process1(stickers, rest));
			}
		}
		return min + (min == Integer.MAX_VALUE ? 0 : 1);
	}

    /**
     *
     * @param s1 需要拼接的目标字符串
     * @param s2 贴纸字符串
     * @return
     */
	public static String minus(String s1, String s2) {
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int[] count = new int[26];
        // 计算目标字符串中每个字符的数量
		for (char cha : str1) {
			count[cha - 'a']++;
		}
        // 贴纸字符串中出现了就减去一次数量计算
		for (char cha : str2) {
			count[cha - 'a']--;
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 26; i++) {
			if (count[i] > 0) {
				for (int j = 0; j < count[i]; j++) {
					builder.append((char) (i + 'a'));
				}
			}
		}
		return builder.toString();
	}

    /**
     *
     * @param stickers 贴纸字符串数组
     * @param target
     * @return
     */
	public static int minStickers2(String[] stickers, String target) {
		int N = stickers.length;
		// 关键优化(用词频表替代贴纸数组)
		int[][] counts = new int[N][26];
		for (int i = 0; i < N; i++) {
			char[] str = stickers[i].toCharArray();
			for (char cha : str) {
				counts[i][cha - 'a']++;
			}
		}
		int ans = process2(counts, target);
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	// stickers[i] 数组，当初i号贴纸的字符统计 int[][] stickers -> 所有的贴纸
	// 每一种贴纸都有无穷张
	// 返回搞定target的最少张数
	// 最少张数
	public static int process2(int[][] stickers, String t) {
		if (t.length() == 0) {
			return 0;
		}
		// target做出词频统计
		// target  aabbc  2 2 1..
		//                0 1 2..
		char[] target = t.toCharArray();
		int[] tcounts = new int[26];
		for (char cha : target) {
			tcounts[cha - 'a']++;
		}
		int N = stickers.length;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			// 尝试第一张贴纸是谁
			int[] sticker = stickers[i];
			// 最关键的优化(重要的剪枝!这一步也是贪心!)   只有贴纸中有 前面字符的先试，如果贴纸中连第一个字符都没有就不用太尝试了，减少尝试次数
			if (sticker[target[0] - 'a'] > 0) {
				StringBuilder builder = new StringBuilder();
				for (int j = 0; j < 26; j++) {
					if (tcounts[j] > 0) {
						int nums = tcounts[j] - sticker[j];
						for (int k = 0; k < nums; k++) {
							builder.append((char) (j + 'a'));
						}
					}
				}
				String rest = builder.toString();
				min = Math.min(min, process2(stickers, rest));
			}
		}
		return min + (min == Integer.MAX_VALUE ? 0 : 1);
	}

	public static int minStickers3(String[] stickers, String target) {
		int N = stickers.length;
		int[][] counts = new int[N][26];
		for (int i = 0; i < N; i++) {
			char[] str = stickers[i].toCharArray();
			for (char cha : str) {
				counts[i][cha - 'a']++;
			}
		}
		HashMap<String, Integer> dp = new HashMap<>();
		dp.put("", 0);
		int ans = process3(counts, target, dp);
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

    /**
     * 傻缓存
     *
     * @param stickers
     * @param t
     * @param dp
     * @return
     */
	public static int process3(int[][] stickers, String t, HashMap<String, Integer> dp) {
		if (dp.containsKey(t)) {
			return dp.get(t);
		}
		char[] target = t.toCharArray();
		int[] tcounts = new int[26];
		for (char cha : target) {
			tcounts[cha - 'a']++;
		}
		int N = stickers.length;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			int[] sticker = stickers[i];
			if (sticker[target[0] - 'a'] > 0) {
				StringBuilder builder = new StringBuilder();
				for (int j = 0; j < 26; j++) {
					if (tcounts[j] > 0) {
						int nums = tcounts[j] - sticker[j];
						for (int k = 0; k < nums; k++) {
							builder.append((char) (j + 'a'));
						}
					}
				}
				String rest = builder.toString();
				min = Math.min(min, process3(stickers, rest, dp));
			}
		}
		int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
		dp.put(t, ans);
		return ans;
	}

}