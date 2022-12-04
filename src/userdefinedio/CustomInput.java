package userdefinedio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CustomInput {
	
	static void userInputForTree() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int t = Integer.parseInt(br.readLine()); 	// 1. Number of test cases.

			while (t-- > 0) {
				int v = Integer.parseInt(br.readLine());
				int e = v - 1;
//				Graph tree = new Graph(v);
//				tree.assignVertexValue(					// 3. Vertex values.
//						java.util.stream.Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
//				for (int i = 0; i < e; i++) {			// 4. Edges
//					int[] adjV1V2 = java.util.stream.Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt)
//							.toArray();
//					tree.addUndirectedEdge(adjV1V2[0] - 1, adjV1V2[1] - 1);
//				}
				// Invoke Method on tree.
			}
		} catch (IOException exp) {
			exp.printStackTrace();
		}
	}
	
	static void userInputForArrays() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int t = Integer.parseInt(br.readLine()); // test cases.

			while (t-- > 0) {
				String[] ip = br.readLine().split(" ");
				int n = Integer.parseInt(ip[0]);
				int k = Integer.parseInt(ip[1]);
				int[] arr = java.util.stream.Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				int[] result = method1(arr, n, k);	// invoke the method
				Arrays.stream(result).forEach(x -> System.out.print(x + " "));
				System.out.println();

			}
		} catch (IOException exp) {
			exp.printStackTrace();
		}
	}
	
	static int[] method1(int[] arr, int n, int k) {
		return new int[] {1, 2, 3, 4, 5};
	}
}
