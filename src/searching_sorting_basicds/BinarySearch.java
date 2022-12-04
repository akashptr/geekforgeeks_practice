package searching_sorting_basicds;

import java.util.Arrays;
import java.util.function.Function;

public class BinarySearch {

	public static void main(String[] args) {
		int[] arr1 = new int[] { 2, 3, 5, 7, 9, 10 };
		System.out.println("Index of 7 is: " + iterativeApproach(arr1, 7));
		System.out.println("Index of 15 is: " + iterativeApproach(arr1, 15));
		System.out.println("Index of 7 is: " + recursiveApproach(arr1, 0, arr1.length - 1, 7));
		System.out.println("Index of 15 is: " + recursiveApproach(arr1, 0, arr1.length - 1, 15));
		System.out.println("Function 1: " + findFirstPositive(MonotonicallyIncreasingFunctions::func1));
		System.out.println("Function 2: " + findFirstPositive(MonotonicallyIncreasingFunctions::func2));
		int[] arr2 = new int[] { 10, 15, 7, 1, 3 };
		int[] query = new int[] { 4, 5, 2, 10, 25, 0 };
		System.out.println(Arrays.toString(geekAndHisMarks(arr2, query)));
	}

	/*
	 * There are N students in a class. Each student got arr[i] (1 <= i <= N) marks
	 * in mathematics exam. Geek loves mathematics, so, he wanted to solve the
	 * questions. But to his surprise, he got different marks every time he solved.
	 * There are Q queries, each query represents a number X. For each query, your
	 * task is to find the sum of the marks of students who got marks greater than
	 * X.
	 */
	static long[] geekAndHisMarks(int[] arr, int[] query) {
		Arrays.sort(arr);
		long sum = 0;
		int ptr = arr.length;
		long[] result = new long[query.length];
		for (int q = 0; q < query.length; q++) {
			int index = bSearch(arr, query[q]);
			if (index == -1)
				result[q] = 0;
			else {
				while (ptr > index)
					sum += arr[--ptr];
				while (ptr < index)
					sum -= arr[ptr++];
				result[q] = sum;
			}
		}
		return result;
	}

	/*
	 * Finds the lowest index whose value is greater than key.
	 */
	static int bSearch(int arr[], int key) {
		int low = 0, high = arr.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (arr[mid] > key) {
				if (mid == low || arr[mid - 1] <= key)
					return mid;
				else
					high = mid - 1;
			} else
				low = mid + 1;
		}
		return -1;
	}

	static int findFirstPositive(Function<Integer, Integer> func) {
		int i = 1;
		int n;
		do {
			n = boundedBinarySearch(func, i / 2, i);
			i *= 2;
		} while (n < 0);
		return n;
	}

	static int boundedBinarySearch(Function<Integer, Integer> func, int low, int high) {
		while (low <= high) {
			int mid = (low + high) / 2;
			if (func.apply(mid) > 0) {
				if (mid == low || func.apply(mid - 1) <= 0)
					return mid;
				else
					high = mid - 1;
			} else
				low = mid + 1;
		}
		return -1;
	}

	class MonotonicallyIncreasingFunctions {
		static int func1(int x) {
			return (x * x - 10 * x - 20);
		}

		static int func2(int x) {
			return (2 * x) + 1;
		}
	}

	static int recursiveApproach(int[] arr, int low, int high, int key) {
		if (high < low)
			return -1;
		int mid = (low + high) / 2;
		if (arr[mid] == key)
			return mid;
		if (arr[mid] < key)
			return recursiveApproach(arr, mid + 1, high, key);
		return recursiveApproach(arr, low, mid - 1, key);
	}

	static int iterativeApproach(int[] arr, int key) {
		Arrays.sort(arr);
		int index = -1;
		int low = 0;
		int high = arr.length - 1;
		while (high >= low) {
			int mid = (low + high) / 2;
			if (arr[mid] == key) {
				index = mid;
				break;
			} else if (arr[mid] < key)
				low = mid + 1;
			else
				high = mid - 1;
		}
		return index;
	}
}
