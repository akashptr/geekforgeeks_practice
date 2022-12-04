package tree_and_graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree {

	private int numberOfVertices;
	private List<Integer> adjList[];
	private int[] values;
	private final int root;
	private final int parentOfRoot;

	enum TraversalOrder {
		PREORDER, POSTORDER;
	}

	@SuppressWarnings("unchecked")
	Tree(int noOfVtx, int root) throws Exception {
		if(noOfVtx <= 0 || root < 0 || root >= noOfVtx)
			throw new Exception("Invalid number of vertex or root value");
		this.numberOfVertices = noOfVtx;
		this.adjList = new List[noOfVtx];
		for (int i = 0; i < noOfVtx; i++)
			adjList[i] = new LinkedList<>();
		values = new int[noOfVtx];
		this.root = root;
		this.parentOfRoot = -1;
	}

	void addEdge(int v1, int v2) throws Exception {
		if (v1 == v2)
			throw new Exception("Argument creates self loop");
		if (v1 < 0 || v1 >= numberOfVertices || v2 < 0 || v2 >= numberOfVertices)
			throw new Exception("Invalid vertex number");
		adjList[v1].add(v2);
		adjList[v2].add(v1);
	}

	void assignValuesToTheVertex(int[] vArr) throws Exception {
		if (vArr.length != this.numberOfVertices)
			throw new Exception("Number of values do not match number of vertices");
		this.values = vArr;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < adjList.length; i++) {
			str.append("(" + i + ", " + this.values[i] + "): ");
			for (int x : adjList[i])
				str.append(x + " ");
			str.append("\n");
		}
		return str.toString();
	}

	int[] BFSTraversal() {
		int[] result = new int[this.numberOfVertices];
		boolean[] visited = new boolean[this.numberOfVertices];

		int current = 0;
		result[current] = this.root;
		visited[this.root] = true;
		int nextEmpty = 1;
		while (nextEmpty < this.numberOfVertices) {
			for (int vertex : adjList[result[current]]) {
				if (!visited[vertex]) {
					result[nextEmpty++] = vertex;
					visited[vertex] = true;
				}
			}
			current++;
		}

		return result;
	}

	private void DFSPreorder(int current, int parent, List<Integer> result) {
		result.add(current);
		for (int vertex : adjList[current]) {
			if (vertex != parent) {
				DFSPreorder(vertex, current, result);
			}
		}
	}

	private void DFSPostorder(int current, int parent, List<Integer> result) {
		for (int vertex : adjList[current]) {
			if (vertex != parent) {
				DFSPostorder(vertex, current, result);
			}
		}
		result.add(current);
	}

	List<Integer> DFSTraversal(TraversalOrder order) {
		List<Integer> result = new ArrayList<>();
		switch (order) {
		case PREORDER:
			DFSPreorder(this.root, this.parentOfRoot, result);
			break;
		case POSTORDER:
			DFSPostorder(this.root, this.parentOfRoot, result);
		}
		return result;
	}

	int[] getLevelOfEachVertex() {
		int[] level = new int[this.numberOfVertices];
		Queue<Integer> qu = new LinkedList<>();
		boolean[] visited = new boolean[this.numberOfVertices];
		level[this.root] = 0;
		visited[this.root] = true;
		qu.add(this.root);
		while (!qu.isEmpty()) {
			int current = qu.remove();
			for (int vertex : this.adjList[current]) {
				if (!visited[vertex]) {
					visited[vertex] = true;
					level[vertex] = level[current] + 1;
					qu.add(vertex);
				}
			}
		}
		return level;
	}

	int absDifferenceEvenOddLevel() {
		int evenSum = 0, oddSum = 0;

		int[] level = this.getLevelOfEachVertex();
		for (int vertex = 0; vertex < this.numberOfVertices; vertex++) {
			if (level[vertex] % 2 == 0)
				evenSum += this.values[vertex];
			else
				oddSum += this.values[vertex];
		}

		return Math.abs(oddSum - evenSum);
	}

	private int findMaxDistanceFromAVertexRecursion(int vertex, int parent) {
		int maxDist = -1;
		for(int adj : this.adjList[vertex]) {
			if(adj != parent) {
				int dist = findMaxDistanceFromAVertexRecursion(adj, vertex);
				if(dist > maxDist)
					maxDist = dist;
			}
		}
		return maxDist + 1;
	}
	
	int getHeightOfTree() {
		return findMaxDistanceFromAVertexRecursion(this.root, this.parentOfRoot);
	}
	
	int findMaxDistFromVertex(int vertex) {
		return findMaxDistanceFromAVertexRecursion(vertex, this.parentOfRoot);
	}
	
	public static void main(String[] args) {
		try {
			Tree tree1 = new Tree(7, 6);
			tree1.addEdge(6, 2);
			tree1.addEdge(0, 2);
			tree1.addEdge(4, 3);
			tree1.addEdge(5, 4);
			tree1.addEdge(1, 4);
			tree1.addEdge(4, 6);
			tree1.assignValuesToTheVertex(new int[] { 1, 4, 9, 2, 0, 3, 5 });
			System.out.println("Adjacency List of the tree-\n" + tree1);
			System.out.println("BFS Traversal of the tree: " + Arrays.toString(tree1.BFSTraversal()));
			System.out.println("DFS Preorder traversal of the tree: " + tree1.DFSTraversal(TraversalOrder.PREORDER));
			System.out.println("DFS Postorder traversal of the tree: " + tree1.DFSTraversal(TraversalOrder.POSTORDER));
			System.out.println("Level of each vertex : " + Arrays.toString(tree1.getLevelOfEachVertex()));
			System.out.println("Absolute difference between even level sum and odd level sum: " + tree1.absDifferenceEvenOddLevel());
			System.out.println("Height of the tree: " + tree1.getHeightOfTree());
			System.out.println("Max distance from node 0 is: " + tree1.findMaxDistFromVertex(0));
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

}
