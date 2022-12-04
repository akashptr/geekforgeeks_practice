package number_theory_and_combinatorics;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class DivisorOfNaturalNumber {

	public static void main(String[] args) {
		System.out.println("Native Approach: " + naive(100));
		System.out.println("Time: O(sqrt n), space: O(1) " + oSqrtN(100));
		System.out.println("Same as prvious but sorted (space O(sqrt n)): " + oSqrtNSorted(100));
		System.out.println("Sorted and constant space: " + sortedConstantSpace(100));
		System.out.println("Kth smallest factor: " + kThSmallestFactor(4, 2));
		System.out.println("Kth smallest factor: " + kThSmallestFactor(4, 3));
		System.out.println("Kth smallest factor: " + kThSmallestFactor(4, 7));
	}

	/*
	 * GIven two positive integers N and K. You have to find the Kth smallest factor
	 * of N. A factor of N is a positive integer which divides N. Output the Kth
	 * smallest factor of N if it exists otherwise print -1.
	 */
	static int kThSmallestFactor(int n, int k) {
		int i;
		for (i = 1; i * i < n; i++) {
			if ((n % i == 0) && (--k == 0))
				return i;
		}
		if (i - (n / i) == 1) {
			i--;
		}
		for (; i >= 1; i--) {
			if ((n % i == 0) && (--k == 0))
				return (n / i);
		}
		return -1;
	}

	static List<Integer> sortedConstantSpace(int n) {
		List<Integer> result = new ArrayList<>();
		int i;
		for (i = 1; i * i < n; i++) {
			if (n % i == 0)
				result.add(i);
		}
		if (i - (n / i) == 1) {
			i--;
		}
		for (; i >= 1; i--) {
			if (n % i == 0)
				result.add(n / i);
		}
		return result;
	}

	static List<Integer> oSqrtNSorted(int n) {
		Deque<Integer> half = new LinkedList<>();
		List<Integer> result = new ArrayList<>();
		for (int i = 1; i * i <= n; i++) {
			if ((n % i) == 0)
				if ((n / i) == i)
					result.add(i);
				else {
					result.add(i);
					half.push(n / i);
				}
		}
		result.addAll(half);
		return result;
	}

	static List<Integer> oSqrtN(int n) {
		List<Integer> result = new ArrayList<>();

		for (int i = 1; i * i <= n; i++)
			if (n % i == 0)
				if ((n / i) == i)
					result.add(i);
				else {
					result.add(i);
					result.add(n / i);
				}

		return result;
	}

	static List<Integer> naive(int n) {
		List<Integer> result = new ArrayList<>();

		for (int i = 1; i <= n; i++)
			if (n % i == 0)
				result.add(i);

		return result;
	}
}
