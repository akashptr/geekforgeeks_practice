package tree_and_graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
	private List<Integer>[] adjList;
	private int[] vertexValue;
	private int numberOfVertices;

	@SuppressWarnings("unchecked")
	public Graph(int numberOfVertex) {
		this.adjList = new List[numberOfVertex];
		for (int i = 0; i < adjList.length; i++)
			adjList[i] = new LinkedList<>();
		this.numberOfVertices = numberOfVertex;
		this.vertexValue = new int[numberOfVertex];
	}

	public void assignVertexValue(int[] values) {
		this.vertexValue = values;
	}

	public void addUndirectedEdge(int u, int v) {
		adjList[u].add(v);
		adjList[v].add(u);
	}

	public void addDirectedEdge(int from, int to) {
		adjList[from].add(to);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < adjList.length; i++) {
			str.append("(" + i + ", " + vertexValue[i] + "): ");
			for (int x : adjList[i])
				str.append(x + " ");
			str.append("\n");
		}
		return str.toString();
	}

	public int[] BFSTraversal(int startingVertex) {
		int[] traversal = new int[this.numberOfVertices];
		boolean[] visited = new boolean[this.numberOfVertices];
		int current = 0;
		int nextEmpty = 1;
		traversal[current] = startingVertex;
		visited[startingVertex] = true;
		while (nextEmpty < this.numberOfVertices) {
			for (int adj : this.adjList[traversal[current]]) {
				if (!visited[adj]) {
					visited[adj] = true;
					traversal[nextEmpty++] = adj;
				}
			}
			current++;
		}
		return traversal;
	}

	public int[] BFSTraversalDisconnected() {
		int[] traversal = new int[this.numberOfVertices];
		boolean[] visited = new boolean[this.numberOfVertices];
		Queue<Integer> qu = new LinkedList<>();
		int nextEmpty = 0;
		for (int vertex = 0; vertex < this.numberOfVertices; vertex++) {
			if (visited[vertex])
				continue;

			traversal[nextEmpty++] = vertex;
			visited[vertex] = true;
			qu.add(vertex);
			while (!qu.isEmpty()) {
				int current = qu.remove();
				for(int adj : this.adjList[current]) {
					if(!visited[adj]) {
						visited[adj] = true;
						qu.add(adj);
						traversal[nextEmpty++] = adj;
					}
				}
			}
		}
		return traversal;
	}
	
	private void DFSUtil(int vertex, boolean[] visited, List<Integer> traversal) {
		visited[vertex] = true;
		traversal.add(vertex);
		for(int adj : this.adjList[vertex]) {
			if(!visited[adj]) {
				DFSUtil(adj, visited, traversal);
			}
		}
	}
	
	public List<Integer> DFSTraversal() {
		List<Integer> traversalResult = new LinkedList<>();
		boolean[] visited = new boolean[this.numberOfVertices];
		for(int vertex = 0; vertex < this.numberOfVertices; vertex++) {
			if(visited[vertex])
				continue;
			this.DFSUtil(vertex, visited, traversalResult);
		}
		return traversalResult;
	}
	
	public static void main(String[] args) {
		Graph g1 = new Graph(4);
		g1.addDirectedEdge(0, 1);
		g1.addDirectedEdge(0, 2);
		g1.addDirectedEdge(1, 2);
		g1.addDirectedEdge(2, 0);
		g1.addDirectedEdge(2, 3);
		g1.addDirectedEdge(3, 3);
		System.out.println(g1);
		System.out.println(Arrays.toString(g1.BFSTraversal(2)));
		System.out.println(g1.DFSTraversal());
		
		Graph g2 = new Graph(6);
		g2.addDirectedEdge(0, 1);
		g2.addDirectedEdge(1, 2);
		g2.addDirectedEdge(2, 1);
		g2.addDirectedEdge(2, 1);
		g2.addDirectedEdge(3, 4);
		g2.addDirectedEdge(4, 4);
		g2.addDirectedEdge(5, 4);
		System.out.println(g2);
		System.out.println(Arrays.toString(g2.BFSTraversalDisconnected()));
		System.out.println(Arrays.toString(g1.BFSTraversalDisconnected()));
	}
}
