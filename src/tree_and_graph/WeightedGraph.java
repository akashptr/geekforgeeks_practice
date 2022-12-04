package tree_and_graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class WeightedGraph {
	
	private static class Pair {
		int vertex;
		int weight;
		Pair(int v, int w) {
			this.vertex = v;
			this.weight = w;
		}
		public String toString() {
			return String.format("(%d, %d)", this.vertex, this.weight);
		}
	}

	private ArrayList<List<Pair>> adjacencyList;
	private int noOfVtx;
	
	WeightedGraph(int numberOfVertices) {
		this.noOfVtx = numberOfVertices;
		this.adjacencyList = new ArrayList<>(this.noOfVtx);
		for(int i=0; i<this.noOfVtx; i++)
			this.adjacencyList.add(i, new LinkedList<>());
	}
	
	public void addUndirectedEdge(int v1, int v2, int weight) {
		this.adjacencyList.get(v1).add(new Pair(v2, weight));
		this.adjacencyList.get(v2).add(new Pair(v1, weight));
	}
	
	public void addDirectedEdge(int from, int to, int weight) {
		this.adjacencyList.get(from).add(new Pair(to, weight));
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for(int i=0; i<this.noOfVtx; i++) {
			str.append(i + ": " + this.adjacencyList.get(i) + "\n");
		}
		return str.toString();
	}
	
	public int[] shortedPathUsingDijkstras(int startVtx) {
		boolean[] visited = new boolean[this.noOfVtx];
		int[] dist = new int[this.noOfVtx];
		for(int i=0; i<this.noOfVtx; i++)
			dist[i] = Integer.MAX_VALUE;
		dist[startVtx] = 0;
		
		for(int count=0; count < this.noOfVtx; count++) {
			int vertex = unvisitedVertexWithMinDist(visited, dist);
			for(Pair adjacent : this.adjacencyList.get(vertex)) {
				if(!visited[adjacent.vertex]) {
					if(dist[adjacent.vertex] > (dist[vertex] + adjacent.weight))
						dist[adjacent.vertex] = dist[vertex] + adjacent.weight;
				}
			}
			visited[vertex] = true;
		}
		return dist;
	}
	
	private int unvisitedVertexWithMinDist(boolean[] visited, int[] dist) {
		int nOV = visited.length;
		int minDist = Integer.MAX_VALUE;
		int minVtx = -1;
		for(int vtx = 0; vtx < nOV; vtx++) {
			if(visited[vtx] == false && dist[vtx] < minDist) {
				minDist = dist[vtx];
				minVtx = vtx;
			}
		}
		return minVtx;
	}
	
	public int[] bellmanFordSP(int startingVertex) {
		int dist[] = new int[this.noOfVtx];
		int infinite = Integer.MAX_VALUE;
		for(int i=0; i<dist.length; i++)
			dist[i] = infinite;
		dist[startingVertex] = 0;
		
		for(int i=0; i<this.noOfVtx-1; i++) {
			for(int j=0; j<this.noOfVtx; j++) {
				for(Pair k : this.adjacencyList.get(j)) {
					if((dist[j] != infinite) && (dist[j] + k.weight < dist[k.vertex]))
						dist[k.vertex] = dist[j] + k.weight;
				}
			}
		}
		boolean containsNegetiveEdgeCycle = false;
		for(int j=0; j<this.noOfVtx; j++) {
			for(Pair k : this.adjacencyList.get(j)) {
				if((dist[j] != infinite) && (dist[j] + k.weight < dist[k.vertex])) {
					containsNegetiveEdgeCycle = true;
					break;
				}
			}
		}
		System.out.println("Contains negetive edge cycle? " + containsNegetiveEdgeCycle);
		return dist;
	}

	public static void main(String[] args) {
//		WeightedGraph g1 = new WeightedGraph(9);
//		g1.addUndirectedEdge(0, 1, 4);
//		g1.addUndirectedEdge(1, 2, 8);
//		g1.addUndirectedEdge(2, 3, 7);
//		g1.addUndirectedEdge(3, 4, 9);
//		g1.addUndirectedEdge(4, 5, 10);
//		g1.addUndirectedEdge(5, 6, 2);
//		g1.addUndirectedEdge(6, 7, 1);
//		g1.addUndirectedEdge(7, 0, 8);
//		g1.addUndirectedEdge(1, 7, 11);
//		g1.addUndirectedEdge(8, 7, 7);
//		g1.addUndirectedEdge(8, 6, 6);
//		g1.addUndirectedEdge(8, 2, 2);
//		g1.addUndirectedEdge(5, 2, 4);
//		g1.addUndirectedEdge(5, 3, 14);
//		System.out.println(g1);
//		int[] minDist = g1.shortedPathUsingDijkstras(0);
//		System.out.println(Arrays.toString(minDist));
		
		WeightedGraph g2 = new WeightedGraph(5);
		g2.addDirectedEdge(0, 1, -1);
		g2.addDirectedEdge(0, 2, 4);
		g2.addDirectedEdge(1, 2, 3);
		g2.addDirectedEdge(1, 3, 2);
		g2.addDirectedEdge(1, 4, 2);
		g2.addDirectedEdge(3, 1, 1);
		g2.addDirectedEdge(3, 2, 5);
		g2.addDirectedEdge(4, 3, -3);
		System.out.println(g2);
		System.out.println(Arrays.toString(g2.bellmanFordSP(0)));
		
	}

}
