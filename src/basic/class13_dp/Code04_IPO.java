package basic.class13_dp;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入: 正数数组costs、正数数组profits、正数K、正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
 * 输出：你最后获得的最大钱数。
 */
public class Code04_IPO {

    /**
     *
     * @param K 最多做 K 个项目
     * @param M 初始资金
     * @param Profits 两个数组等长
     * @param Capital
     * @return 返回最终最大的资金
     */
	public static int findMaximizedCapital(int K, int M, int[] Profits, int[] Capital) {
        // 1. 花费从小到大排序，利润从大到小排序，两个数组等长
		PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());
		PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
		for (int i = 0; i < Profits.length; i++) {
			minCostQ.add(new Program(Profits[i], Capital[i]));
		}
        // k 个项目
		for (int i = 0; i < K; i++) {
            // 2. 小根堆中能做的项目，进入大根堆按利润排序
			while (!minCostQ.isEmpty() && minCostQ.peek().c <= M) {
				maxProfitQ.add(minCostQ.poll());
			}
            // 3. 大根堆中能做的项目，利润加起来
			if (maxProfitQ.isEmpty()) {
				return M;
			}
			M += maxProfitQ.poll().p;
		}
		return M;
	}

	public static class Program {
		public int p;
		public int c;

		public Program(int p, int c) {
			this.p = p;
			this.c = c;
		}
	}

    // 花费从小到大排序
	public static class MinCostComparator implements Comparator<Program> {
		@Override
		public int compare(Program o1, Program o2) {
			return o1.c - o2.c;
		}
	}

    // 利润从大到小排序
	public static class MaxProfitComparator implements Comparator<Program> {
		@Override
		public int compare(Program o1, Program o2) {
			return o2.p - o1.p;
		}
	}
}