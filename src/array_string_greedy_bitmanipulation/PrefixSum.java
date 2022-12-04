package array_string_greedy_bitmanipulation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PrefixSum {
	public static void main(String[] args) {
		int[] arr = new int[] { 3, 2, 1, 4, 22 };
		System.out.println(Arrays.toString(prefixSum(arr)));

		sol1(arr, new int[][] { { 2, 4 }, { 3, 3 }, { 0, 3 } });

		System.out.println(sol2Naive(5, new int[][] { { 2, 4 }, { 1, 3 }, { 1, 2 } }));
		System.out.println(sol2PS(5, new int[][] { { 2, 4 }, { 1, 3 }, { 1, 2 } }));

		System.out.println(sol3Naive(new int[] { -7, 1, 5, 2, -4, 3, 0 }));
		System.out.println(sol3Naive(new int[] { 1, 2, 3 }));
		System.out.println(sol3Naive(new int[] { 1, 2, -2 }));

		System.out.println(sol3PS(new int[] { -7, 1, 5, 2, -4, 3, 0 }));
		System.out.println(sol3PS(new int[] { 1, 2, 3 }));
		System.out.println(sol3PS(new int[] { 1, 2, -2 }));

		System.out.println(sol4PS(new int[] { -3, 2, 3, 1, 6 }));
		System.out.println(sol4PS(new int[] { 4, 2, -3, 1, 6 }));
	}

	/*
	 * Problem 5: You start with an array A of size N. Also, A[i] = 0 for i = 1 to
	 * N. You will be given K positive integers. Let j be one of these integers, you
	 * have to add 1 to all A[i], for i >= j. Your task is to print array A after all
	 * these K updates are done.
	 * 
	 * Example:
	 * Input:
	 * 3 4
	 * 1 1 2 3
	 * Output:
	 * 2 3 4
	 * Explanation:
	 * Initially the array is {0, 0, 0}. 
	 * After the first 1, it becomes {1, 1, 1}. 
	 * After the second 1 it becomes {2, 2, 2}. 
	 * After 2, it becomes {2, 3, 3}. 
	 * After 3 it becomes, {2, 3, 4}.
	 * 
	 */
	public static void sol5(int a[], int n, int updates[], int k)
    {
        for(int query : updates) {
            a[query - 1]++;
        }
        for(int i=1; i<n; i++) {
            a[i] += a[i-1];
        }
    }

	// Problem 4: Find if there is a sub-array of 0 sum
	public static boolean sol4PS(int[] arr) {
		int sum = 0;
		Set<Integer> set = new HashSet<>();
		for (int e : arr) {
			sum += e;
			if (e == 0 || sum == 0 || set.contains(sum))
				return true;
			set.add(sum);
		}
		return false;
	}

	/*
	 * Problem 3: Equilibrium index The equilibrium index of an array is an index
	 * such that the sum of elements at lower indexes is equal to the sum of
	 * elements at higher indexes.
	 */
	public static int sol3Naive(int[] arr) {
		int sum = 0;
		for (int element : arr) {
			sum += element;
		}
		int leftSum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum -= arr[i];
			if (leftSum == sum)
				return i;
			leftSum += arr[i];
		}
		return -1;
	}

	public static int sol3PS(int[] arr) {
		int n = arr.length;
		int[] forwardPS = new int[n];
		int[] backwardPS = new int[n];
		forwardPS[0] = arr[0];
		for (int i = 1; i < n; i++)
			forwardPS[i] = forwardPS[i - 1] + arr[i];
		backwardPS[n - 1] = arr[n - 1];
		for (int i = n - 2; i >= 0; i--)
			backwardPS[i] = backwardPS[i + 1] + arr[i];
		for (int i = 0; i < n; i++) {
			if (forwardPS[i] == backwardPS[i])
				return i;
		}
		return -1;
	}

	/*
	 * Problem 2: Consider an array of size n with all initial values as 0. Perform
	 * the given ‘m’ add operations from index ‘a’ to ‘b’ and evaluate the highest
	 * element in the array. An add operation adds 100 to all elements from a to b
	 * (both inclusive).
	 * 
	 * Input : n = 5, We consider array {0, 0, 0, 0, 0}, m = 3. a = 2, b = 4. a = 1,
	 * b = 3. a = 1, b = 2. Output : 300
	 * 
	 * Explanation :
	 * 
	 * After I operation - A : 0 100 100 100 0
	 * 
	 * After II operation - A : 100 200 200 100 0
	 * 
	 * After III operation - A : 200 300 200 100 0
	 * 
	 * Highest element : 300
	 */
	public static int sol2Naive(int n, int[][] opIndex) {
		int[] arr = new int[n];
		int max = 0;
		for (int[] row : opIndex) {
			for (int col = row[0] - 1; col < row[1]; col++) {
				arr[col] += 100;
				if (arr[col] > max)
					max = arr[col];
			}
		}
		System.out.println(Arrays.toString(arr));
		return max;
	}

	public static int sol2PS(int n, int[][] opIndex) {
		int[] arr = new int[n];
		for (int[] row : opIndex) {
			if (row[0] > 0)
				arr[row[0] - 1] += 100;
			if (row[1] < n)
				arr[row[1]] -= 100;
		}
		int sum = 0;
		int max = 0;
		for (int x : arr) {
			sum += x;
			if (sum > max)
				max = sum;
		}
		System.out.println(Arrays.toString(arr));
		return max;
	}

	/*
	 * Problem 1: Consider an array of size n with all initial values as 0. Perform
	 * the given ‘m’ add operations from index ‘a’ to ‘b’ and evaluate the highest
	 * element in the array. An add operation adds 100 to all elements from a to b
	 * (both inclusive).
	 * 
	 * Input : n = 5 We consider array {0, 0, 0, 0, 0} m = 3. a = 2, b = 4. a = 1, b
	 * = 3. a = 1, b = 2. Output : 300
	 * 
	 * Explanation :
	 * 
	 * After I operation - A : 0 100 100 100 0 After II operation - A : 100 200 200
	 * 100 0 After III operation - A : 200 300 200 100 0
	 * 
	 * Highest element : 300
	 */
	public static void sol1(int[] org, int[][] query) {
		int[] pSum = prefixSum(org);
		for (int[] row : query) {
			int l = row[0];
			int r = row[1];
			if (l <= 0)
				System.out.println(pSum[r]);
			else
				System.out.println(pSum[r] - pSum[l - 1]);
		}
	}

	// Find prefix sum of an array.
	public static int[] prefixSum(int[] org) {
		int prefixSum[] = new int[org.length];
		prefixSum[0] = org[0];
		for (int i = 1; i < prefixSum.length; i++) {
			prefixSum[i] = org[i] + prefixSum[i - 1];
		}
		return prefixSum;
	}

}
