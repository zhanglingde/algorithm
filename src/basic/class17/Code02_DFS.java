package basic.class17;

import java.util.HashSet;
import java.util.Stack;

/**
 * 图的深度优先遍历
 */
public class Code02_DFS {

	public static void dfs(Node node) {
		if (node == null) {
			return;
		}
		Stack<Node> stack = new Stack<>();
        // Set 用于保存遍历过的节点，当一条路走完后，往回走，遍历过的子节点就不再遍历
		HashSet<Node> set = new HashSet<>();
		stack.add(node);
		set.add(node);
		System.out.println(node.value);
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			for (Node next : cur.nexts) {
				if (!set.contains(next)) {
                    // 当前节点再次加入栈是为了一条节点走完后，往回走
					stack.push(cur);
					stack.push(next);
					set.add(next);
					System.out.println(next.value);
                    // break 是为了不遍历当前节点的的其他路径，使往一个链路走
					break;
				}
			}
		}
	}
	
	
	

}
