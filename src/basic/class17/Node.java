package basic.class17;

import java.util.ArrayList;

// 点结构的描述
public class Node {
	public int value;
	public int in;  // 入度
	public int out; // 出度
	public ArrayList<Node> nexts;
	public ArrayList<Edge> edges;

	public Node(int value) {
		this.value = value;
		in = 0;
		out = 0;
		nexts = new ArrayList<>();
		edges = new ArrayList<>();
	}
}