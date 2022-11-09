package basic.class17;

/**
 * 将其他图的表示方式转换成自己熟悉的图的表示方式
 */
public class GraphGenerator {


    /**
     * 将其他图的表示方式转换成自己熟悉的图的表示方式
     *
     * [ 5 , 0 , 7]
     * [ 3 , 0,  1]
     *
     * N*3 的矩阵
     * [weight, from节点上面的值，to节点上面的值]
     *
     * @param matrix 所有的边
     * @return
     */
	public static Graph createGraph(int[][] matrix) {
		Graph graph = new Graph();
		for (int i = 0; i < matrix.length; i++) {
			 // 拿到每一条边， matrix[i] 
			int weight = matrix[i][0];
			int from = matrix[i][1];
			int to = matrix[i][2];
			if (!graph.nodes.containsKey(from)) {
				graph.nodes.put(from, new Node(from));
			}
			if (!graph.nodes.containsKey(to)) {
				graph.nodes.put(to, new Node(to));
			}
			Node fromNode = graph.nodes.get(from);
			Node toNode = graph.nodes.get(to);
			Edge newEdge = new Edge(weight, fromNode, toNode);
			fromNode.nexts.add(toNode);
			fromNode.out++;
			toNode.in++;
			fromNode.edges.add(newEdge);
			graph.edges.add(newEdge);
		}
		return graph;
	}

}
