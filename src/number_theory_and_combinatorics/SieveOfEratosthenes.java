package number_theory_and_combinatorics;

import java.util.ArrayList;

public class SieveOfEratosthenes {

	public static void main(String[] args) {
		System.out.println(simpleSieve(10));
		System.out.println(simpleSieve(35));
		System.out.println(segmentedSieve(35));
		System.out.println(primeProduct(1, 10));
	}

	static long primeProduct(long left, long R) {
		int L = (int)left;
		int n = (int)R;
		
		int segment = (int) Math.sqrt(n);
		ArrayList<Integer> prime = simpleSieve(segment);

		int low = segment + 1;
		int high = 2 * segment;
		if (high > n)
			high = n;

		boolean[] markPrime = new boolean[segment];
		long result = 1, modulo = 1000000007;
		
		if(L <= segment) {
			int i= 0;
			while(prime.get(i) < L)
				i++;
			for(int j=i; j < prime.size(); j++)
				result = ((result % modulo) * (prime.get(j) % modulo)) % modulo;
		}
		while (low <= high) {
			for (int j = 0; j < segment; j++)
				markPrime[j] = true;
			for (int k : prime) {
				int loMin = Math.floorDiv(low, k) * k;
				if (loMin < low)
					loMin += k;
				for (int j = loMin; j <= high; j += k)
					markPrime[j - low] = false;
			}
			for (int j = 0; j <= (high - low); j++) {
				if (markPrime[j] == true && (low + j) >= L) {
					result = ((result % modulo) * ((low + j) % modulo)) % modulo;
				}
			}
			low = high + 1;
			high = low + segment - 1;
			if (high > n)
				high = n;
		}
		return result;
	}

	static ArrayList<Integer> segmentedSieve(int n) {
		int segment = (int) Math.sqrt(n);
		ArrayList<Integer> prime = simpleSieve(segment);

		int low = segment + 1;
		int high = 2 * segment;
		if (high > n)
			high = n;

		//boolean[] markComposite = new boolean[segment];
		boolean[] markPrime = new boolean[segment];
		ArrayList<Integer> result = new ArrayList<>();
		for (int element : prime)
			result.add(element);

		while (low <= high) {
			for (int j = 0; j < segment; j++)
				markPrime[j] = true;
			
			for (int k : prime) {
				int loMin = Math.floorDiv(low, k) * k;
				if (loMin < low)
					loMin += k;
				for (int j = loMin; j <= high; j += k)
					markPrime[j - low] = false;
			}
			for (int j = 0; j <= (high - low); j++) {
				if (markPrime[j] == true) {
					result.add(low + j);
				}
			}
			low = high + 1;
			high = low + segment - 1;
			if (high > n)
				high = n;
		}
		return result;
	}

	static ArrayList<Integer> simpleSieve(int N) {
		boolean[] composite = new boolean[N + 1];
		for (int i = 2; i * i <= N; i++) {
			if (composite[i] == false) {
				for (int j = i; j * i <= N; j++) {
					if (composite[j * i] == false)
						composite[j * i] = true;
				}
			}
		}
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 2; i <= N; i++)
			if (composite[i] == false)
				result.add(i);
		return result;
	}
}
