package array_string_greedy_bitmanipulation;

public class BitManipulation {

	public static void main(String[] args) {
		System.out.println(setBit(8, 2));
		System.out.println(resetBit(10, 2));
		System.out.println(toggle(10, 3));
		System.out.println(checkBitSet(10, 3));
		System.out.println(checkBitSet(10, 2));
		System.out.println(stripLowestSetBit(10));
		System.out.println(getLowestSetBit(10));
		int[] nums = { 1, 4, 2, 6, 5 };
		System.out.println(findMissingNumberAddition(nums));
		System.out.println(findMissingNumberNoOverflow(nums));
		System.out.println(findMissingNumberXor(nums));
		swap(10, 20);
		swapOneLine(3, 45);
		System.out.println(findOddOccurrence(new int[] { 21, 3, 43, 3, 21, 5, 21, 5, 21 }));
		findTwoOddOccurrence(new int[] { 2, 3, 7, 9, 11, 2, 3, 11 });
		System.out.println(add(0, 3));
		System.out.println(countDifferentBits(10, 20));
		System.out.println(countDifferentBits(7, 10));
		System.out.println(clearBit(15, 2));
		System.out.println(clearBitFromMSB(215, 4));
		System.out.println(toLowerCase('A'));
		System.out.println(toUpperCase('a'));
		System.out.println(kernighanAlgo(12));
		System.out.println(log2(12));
		System.out.println(log2(16));
		System.out.println(isPowerOf2(16));
		System.out.println(isPowerOf2(17));
		System.out.println(isPowerOf2(0));
		System.out.println(lastSetBit(1));
		System.out.println(xor1ToN(12));
		System.out.println(countI(12));
		System.out.println(geekAndFunction(66, 3));
	}

	/*
	 * Given two integers numbers N and K, the Geek wants you to find
	 * f(f(..........f(N))) K times, where f(x) = x XOR (x%10).
	 */
	public static int geekAndFunction(int n, int k) {
		if(n%10 == 0 || k == 0)
			return n;
		return geekAndFunction(n ^ (n % 10), --k);
	}

	/*
	 * Given an array arr of positive integers of size N and an integer K, the task
	 * is to find the sum of all subsets of size K.
	 */
	public static int subsetSum(int[] nums, int n, int k) {
		// iterate all the possible 2 ^ n subsets
		int sum = 0;
		for (int i = 1; i < (1 << n); i++) {
			// skip other than k
			if (Integer.bitCount(i) != k)
				continue;
			for (int j = 0, temp = i; j < n; j++, temp >>= 1)
				if ((temp & 1) == 1)// current bit is set add nums[i] to sum
					sum += nums[j];
		}
		return sum;
	}

	// Check if a number has all its bits in alternate pattern.
	public static boolean checkAlternateBitPattern(int num) {
		int temp = (num ^ (num >>> 1));
		if (isPowerOf2(temp + 1))
			return true;
		return false;
	}

	// XOR of all sub-sets of a set.
	public static int xorOfSubset(int[] set) {
		if (set.length == 1)
			return set[0];
		return 0;
	}

	// count the number of i such that n+i = n xor i where 0<=i<=n.
	public static int countI(int n) {
		int count = 0;
		while (n != 0) {
			if ((n & 1) == 0)
				count++;
			n >>= 1;
		}
		return 1 << count;
	}

	// XOR from 1 to n.
	public static int xor1ToN(int N) {
		int rem = N % 4;
		switch (rem) {
		case 0:
			return N;
		case 1:
			return 1;
		case 2:
			return N + 1;
		}
		return 0;
	}

	// Find the last set bit.
	public static int lastSetBit(int n) {
		return log2(n & -n) + 1;
	}

	// Checking if the given integer is power of 2.
	public static boolean isPowerOf2(int n) {
		return (n == 0) ? false : ((n & (n - 1)) == 0) ? true : false;
	}

	// Find log base 2 of 32-bit integer.
	public static int log2(int n) {
		int result = 0;
		while ((n >>= 1) != 0)
			result++;
		return result;
	}

	// Count set bits in an integer.
	public static int kernighanAlgo(int num) {
		int count = 0;
		while (num != 0) {
			count++;
			num &= (num - 1);
		}
		return count;
	}

	// Lower case to upper case.
	public static char toUpperCase(char ch) {
		// Mask with 1101111 or '_'
		return (char) (ch & '_');
	}

	// Upper case alphabets to lower case using bitwise operator.
	public static char toLowerCase(char ch) {
		// A -> 01000001 a -> 01100001
		// B -> 01000010 b -> 01100010
		// C -> 01000011 c -> 01100011
		// . .
		// . .
		// Z -> 01011010 z -> 01111010
		// Just OR with 10000 viz. 32
		// ' ' has ascii value 32.
		return (char) (ch | ' ');
	}

	// Clearing MSB to ith(ith bit from LSB) bit.
	public static int clearBitFromMSB(int num, int i) {
		return num & ((1 << i) - 1);
	}

	// Clear all bits from LSB to ith bit.
	public static int clearBit(int num, int i) {
		return num & (~((1 << i) - 1));
	}

	// Count number of bits to flip to convert A to B.
	public static int countDifferentBits(int A, int B) {
		int XOR = A ^ B;
		int count = 0;
		while (XOR != 0) {
			count++;
			XOR &= (XOR - 1);
		}
		return count;
	}

	// Add two numbers without using arithmetic operator.
	public static int add(int x, int y) {
		if (y == 0)
			return x;
		return add(x ^ y, (x & y) << 1);
	}

	/*
	 * Find two numbers with odd occurrences in an unsorted array where all other
	 * numbers have even occurrences.
	 */
	public static void findTwoOddOccurrence(int[] arr) {
		int XOR = 0;
		// XOR of all elements
		for (int element : arr)
			XOR ^= element;
		// Get the right most set bit
		int div = XOR & ((~XOR) + 1); // n xor -n
		int x = 0, y = 0;
		for (int element : arr) {
			if ((element & div) == 0)
				x ^= element;
			else
				y ^= element;
		}
		System.out.println("The two numbers are: " + x + " " + y);
	}

	/*
	 * Find the number with one/odd occurrence in an unsorted array where all other
	 * numbers have even occurrence.
	 */
	public static int findOddOccurrence(int[] arr) {
		int result = 0;
		for (int element : arr) {
			result ^= element;
		}
		return result;
	}

	// Swap without temporary variable.
	public static void swapOneLine(int x, int y) {
		System.out.println("Before swap: " + x + " " + y);
		x = x ^ y ^ (y = x);
		System.out.println("After swap: " + x + " " + y);
	}

	public static void swap(int x, int y) {
		System.out.println("Before swap: " + x + " " + y);
		x = x ^ y;
		y = x ^ y;
		x = x ^ y;
		System.out.println("After swap: " + x + " " + y);
	}

	/*
	 * Given an array arr[] of size N-1 with integers in the range of [1, N], the
	 * task is to find the missing number from the first N integers.
	 * 
	 * Note: There are no duplicates in the list.
	 */
	public static int findMissingNumberAddition(int[] arr) {
		int n = arr.length + 1;
		int sum = n * (n + 1) / 2;
		for (int element : arr) {
			sum -= element;
		}
		return sum;
	}

	/*
	 * Missing number = (1+2+3+...+n)-(a[0]+a[1]+a[2]+...+a[n-2]) =>
	 * 1+(2-a[0])+(3-a[1])+...+(n-a[n-2]) => sum + (c - a[c-2]) for sum = 1 and 2 <=
	 * c <= n
	 */
	public static int findMissingNumberNoOverflow(int[] arr) {
		int sum = 1, n = arr.length;
		for (int i = 2; i < n + 2; i++)
			sum = sum - arr[i - 2] + i;
		return sum;
	}

	// if a xor b = c then c xor a = b or c xor b = a
	public static int findMissingNumberXor(int[] arr) {
		int n = arr.length;
		int a = 0, b = 0;
		for (int i = 0; i < n; i++) {
			a ^= i + 1;
			b ^= arr[i];
		}
		a ^= n + 1;
		return a ^ b;
	}

	// Getting the lowest set bit of a number
	public static int getLowestSetBit(int n) {
		return n & -n;
	}

	// Striping off the lowest set bit.
	public static int stripLowestSetBit(int n) {
		return n & (n - 1);
	}

	// Check if a bit at nth position is set or unset.
	public static boolean checkBitSet(int num, int n) {
		return ((1 << (n - 1)) & num) == 0 ? false : true;
	}

	// Toggling a bit at nth position.
	public static int toggle(int num, int n) {
		return (1 << (n - 1)) ^ num;
	}

	// Reset a bit in a number.
	public static int resetBit(int num, int n) {
		return ~(1 << (n - 1)) & num;
	}

	// Set a bit in a number.
	public static int setBit(int num, int n) {
		return (1 << (n - 1)) | num;
	}
}
