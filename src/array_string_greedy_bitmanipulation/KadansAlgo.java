package array_string_greedy_bitmanipulation;

import java.util.Arrays;

public class KadansAlgo {

	public static void main(String[] args) {
		int[] arr = new int[] { -2, -3, 4, -1, -2, 1, 5, -3 };
		System.out.println(sol1(arr));
		System.out.println(Arrays.toString(sol2(arr)));
	}

	/*
	 * Problem 2: Print the largest sum contiguous sub-array.
	 */
	public static int[] sol2(int arr[]) {
		int start = 0, end = 0, maxSumSoFar = Integer.MIN_VALUE, s = 0, maxSumEndingHere = 0;

		for (int i = 0; i < arr.length; i++) {
			maxSumEndingHere += arr[i];
			if (maxSumEndingHere > maxSumSoFar) {
				maxSumSoFar = maxSumEndingHere;
				start = s;
				end = i;
			}
			if (maxSumEndingHere < 0) {
				maxSumEndingHere = 0;
				s = i+1;
			}
		}

		return Arrays.copyOfRange(arr, start, end + 1);
	}

	/*
	 * Problem 1: Given an array arr[] of size N. The task is to find the sum of the
	 * contiguous sub-array within arr[] with the largest sum.
	 */
	public static int sol1(int arr[]) {
		int maxSumSoFar = Integer.MIN_VALUE;
		int maxSumEndingHere = 0;

		for (int i = 0; i < arr.length; i++) {
			maxSumEndingHere += arr[i];
			if (maxSumEndingHere > maxSumSoFar) {
				maxSumSoFar = maxSumEndingHere;
			}
			if (maxSumEndingHere < 0)
				maxSumEndingHere = 0;
		}

		return maxSumSoFar;
	}
}
