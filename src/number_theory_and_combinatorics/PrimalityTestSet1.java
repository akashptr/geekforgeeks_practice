package number_theory_and_combinatorics;

public class PrimalityTestSet1 {

	public static void main(String[] args) {
		System.out.println("17 is prime: " + isPrime1(17));
		System.out.println("25 is prime: " + isPrime1(25));
		System.out.println("17 is prime: " + isPrime2(17));
		System.out.println("25 is prime: " + isPrime2(25));
		System.out.println("0 is prime: " + isPrime3(0));
		System.out.println("3 is prime: " + isPrime3(3));
	}

	/*
	 * Checking if the number is divisible by 6k+-1.
	 */
	static boolean isPrime3(int n) {
		if (n == 2 || n == 3)
			return true;
		if (n <= 1 || n % 2 == 0 || n % 3 == 0)
			return false;
		for (int i = 5; i * i <= n; i += 6) {
			if (n % i == 0 || n % (i + 2) == 0)
				return false;
		}
		return true;
	}

	/*
	 * Improving previous approach by iterating up to root(n).
	 */
	static boolean isPrime2(int n) {
		if (n <= 1)
			return false;
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	/*
	 * Checking if the number n is divisible by any number i such that 2<=i<=(n-1).
	 */
	static boolean isPrime1(int n) {
		if (n <= 1)
			return false;
		for (int i = 2; i < n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}
}
