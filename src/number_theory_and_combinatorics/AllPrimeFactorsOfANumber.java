package number_theory_and_combinatorics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AllPrimeFactorsOfANumber {
	static final int MAXN = 100001;
	static int[] lpfSieve = LeastPrimeFactorsUptoN.leastPrimeFactor(MAXN);

	public static void main(String[] args) {
		System.out.println(approach1(10));
		System.out.println(approach1(23));
		System.out.println(approach2(10));
		System.out.println(approach2(23));

		System.out.println(approach3(180));
		System.out.println(approach3(12246));
		System.out.println(factorSum(30));
	}

	/*
	 * Factors sum: Given a number N find the sum of all of its factors.
	 */
	static long factorSum(int N) {
		long res = 1;
		for (int i = 2; i * i <= N; i++) {
			int curSum = 1;
			int curTerm = 1;
			while (N % i == 0) {
				curTerm *= i;
				curSum += curTerm;
				N /= i;
			}
			res *= curSum;
		}
		if (N >= 2)
			res *= (1+N);

		return res;
	}

	/*
	 * Uses pre-calculated values to process multiple queries.
	 */
	static List<Integer> approach3(int n) {
		List<Integer> result = new LinkedList<>();
		while (n != 1) {
			result.add(lpfSieve[n]);
			n /= lpfSieve[n];
		}
		return result;
	}

	static long largestPrimeFactor(int n) {
		long lpf = 1;
		if (n % 2 == 0) {
			lpf = 2;
			while (n % 2 == 0)
				n /= 2;
		}

		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0) {
				lpf = i;
				while (n % i == 0)
					n /= i;
			}
		}
		if (n > 2)
			lpf = n;
		return lpf;
	}

	static List<Integer> approach2(int n) {
		List<Integer> result = new ArrayList<>();
		int c = 2;
		while (n > 1) {
			if (n % c == 0) {
				result.add(c);
				n /= c;
			} else
				c++;
		}
		return result;
	}

	static List<Integer> approach1(int n) {
		List<Integer> result = new ArrayList<>();

		while (n % 2 == 0) {
			result.add(2);
			n /= 2;
		}
		for (int i = 3; i * i <= n; i += 2) {
			while (n % i == 0) {
				result.add(i);
				n /= i;
			}
		}
		if (n > 2)
			result.add(n);

		return result;
	}

}
