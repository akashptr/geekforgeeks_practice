package array_string_greedy_bitmanipulation;

/*
 * Consider an array A[] of integers and following two types of queries.
 * 1. update(l, r, x) : Adds x to all values from A[l] to A[r] (both inclusive).
 * 2. printArray() : Prints the current modified array.
 * 
 * Example
 * Input : A [] { 10, 5, 20, 40 }
 *      update(0, 1, 10)
 *      printArray()
 *      update(1, 3, 20)
 *      update(2, 2, 30)
 *      printArray()
 * Output : 20 15 20 40
 *        20 35 70 60
 * Explanation : The query update(0, 1, 10) 
 * adds 10 to A[0] and A[1]. 
 * After update, A[] becomes {20, 15, 20, 40}
 * Query update(1, 3, 20) adds 20 to A[1], A[2] and A[3]. 
 * After update, A[] becomes {20, 35, 40, 60}.
 * Query update(2, 2, 30) adds 30 to A[2]. 
 * After update, A[] becomes {20, 35, 70, 60}.
 * 
 * The problem is to perform update and printArray operations in O(1) time.
 * 
 * Solution
 * Difference array D[i] of a given array A[i] is defined as D[i] = A[i] - A[i-1] (for 0 < i < N ) and D[0] = A[0] considering 0 based indexing.
 * 1. update(l, r, x) : Add x to D[l] and subtract it from D[r+1], i.e., we do D[l] += x, D[r+1] -= x.
 * 2. printArray() : Do A[0] = D[0] and print it. For rest of the elements, do A[i] = A[i-1] + D[i] and print them.
 * 
 */
public class DifferenceArray {

	// Creates a diff array D[] for A[] and returns it after filling initial values.
	static void initializeDiffArray(int A[], int D[]) {

		int n = A.length;

		D[0] = A[0];
		D[n] = 0;
		for (int i = 1; i < n; i++)
			D[i] = A[i] - A[i - 1];
	}

	// Does range update
	static void update(int D[], int l, int r, int x) {
		D[l] += x;
		D[r + 1] -= x;
	}

	// Prints updated Array
	static int printArray(int A[], int D[]) {
		for (int i = 0; i < A.length; i++) {

			if (i == 0)
				A[i] = D[i];

			// Note that A[0] or D[0] decides
			// values of rest of the elements.
			else
				A[i] = D[i] + A[i - 1];

			System.out.print(A[i] + " ");
		}

		System.out.println();

		return 0;
	}

	// Driver Code
	public static void main(String[] args) {
		// Array to be updated
		int A[] = { 10, 5, 20, 40 };
		int n = A.length;
		// Create and fill difference Array
		// We use one extra space because
		// update(l, r, x) updates D[r+1]
		int D[] = new int[n + 1];
		initializeDiffArray(A, D);

		// After below update(l, r, x), the
		// elements should become 20, 15, 20, 40
		update(D, 0, 1, 10);
		printArray(A, D);

		// After below updates, the
		// array should become 30, 35, 70, 60
		update(D, 1, 3, 20);
		update(D, 2, 2, 30);

		printArray(A, D);
	}
}
