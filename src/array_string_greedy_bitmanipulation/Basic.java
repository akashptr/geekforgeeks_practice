package array_string_greedy_bitmanipulation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Basic {
	// Reversing a string
	public static String reverseStringUsingIteration(String str) {
		char[] charArray = str.toCharArray();
		for (int left = 0, right = charArray.length - 1; left < right; left++, right--) {
			char temp = charArray[left];
			charArray[left] = charArray[right];
			charArray[right] = temp;
		}
		return String.valueOf(charArray);
	}

	public static void reverseStringUsingRecursion(StringBuilder str, int index1, int index2) {
		if (str == null || str.isEmpty() || index1 < 0 || index2 >= str.length())
			return;
		if (index1 >= index2)
			return;
		char temp = str.charAt(index1);
		str.setCharAt(index1, str.charAt(index2));
		str.setCharAt(index2, temp);
		reverseStringUsingRecursion(str, ++index1, --index2);
	}

	// Reversing an array
	public static <T> void reverseArrayIteration(T[] arr) {
		for (int left = 0, right = arr.length - 1; left < right; left++, right--) {
			T temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;
		}
	}

	public static <T> void reverseArrayRecursion(T[] arr, int start, int end) {
		if (start >= end)
			return;
		T temp = arr[start];
		arr[start] = arr[end];
		arr[end] = temp;
		reverseArrayRecursion(arr, ++start, --end);
	}

	/*
	 * Provided an array a[] this method will return sum such that sum =
	 * (a[n]*a[n])-(a[n-1]*a[n-1])+(a[n-2]*a[n-2])-...upto index 1
	 */
	public static long reverseSquaredSum(int arr[]) {
		long sum = 0;
		int len = arr.length;
		for (int i = len - 1; i >= 0; i--) {
			sum += (int) Math.pow(-1, len - i - 1) * arr[i] * arr[i];
		}
		return sum;
	}

	// Sum of digits of a number. For example 123 -> 1+2+3 -> 6
	public static int sumOfDigits(int num) {
		int sum = 0;
		while (num > 0) {
			int digit = num % 10;
			sum += digit;
			num /= 10;
		}
		return sum;
	}

	public static int sumOfDigitsUsingRecursion(int num) {
		if (num / 10 == 0)
			return num;
		int sum = (num % 10) + sumOfDigitsUsingRecursion(num / 10);
		return sum;
	}

	public static int sumOfDigitsString(String num) {
		int sum = 0;
		for (int i = 0; i < num.length(); i++)
			sum += (num.charAt(i) - 48);
		return sum;
	}

	public static int sumOfDigitsTailRecursion(int num, int val) {
		if (num < 10) {
			val += num;
			return val;
		}
		return sumOfDigitsTailRecursion(num / 10, val + (num % 10));
	}

	// Palindrome check
	public static boolean isPalindrome(String str) {
		int len = str.length();
		boolean res = true;
		for (int left = 0, right = len - 1; left < right; left++, right--) {
			if (str.charAt(left) != str.charAt(right)) {
				res = false;
				break;
			}
		}
		return res;
	}

	// Sum of elements
	public static int sumArrayIteration(int[] arr) {
		int sum = 0;
		for (int left = 0, right = arr.length - 1; left <= right; left++, right--) {
			if (left == right)
				sum += arr[left];
			else
				sum += arr[left] + arr[right];
		}
		return sum;
	}

	public static int sumArrayRecursion(int[] arr, int start, int end) {
		if (start == end)
			return arr[start];
		return (arr[start] + arr[end] + sumArrayRecursion(arr, ++start, --end));
	}

	// Find the missing number from a set of n natural numbers
	public static int missingNumber(int[] arr, int n) {
		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += arr[i];
		}
		int originalSum = (n * (n + 1)) / 2;
		return originalSum - sum;
	}

	// Finding maximum and minimum from an array
	// This class is used to return both maximum and minimum value
	public static class MaxMin {
		public int max, min;

		MaxMin() {
			max = 0;
			min = 0;
		}

		MaxMin(int mx, int mn) {
			max = mx;
			min = mn;
		}
	}

	public static MaxMin findMaxMinNaive(int[] arr) {
		int max = arr[0];
		int min = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max)
				max = arr[i];
			if (arr[i] < min)
				min = arr[i];
		}
		return new MaxMin(max, min);
	}

	public static MaxMin maxMinTournament(int[] arr, int start, int end) {
		if (start == end)
			return new MaxMin(arr[start], arr[start]);
		if (start + 1 == end) {
			if (arr[start] > arr[end])
				return new MaxMin(arr[start], arr[end]);
			return new MaxMin(arr[end], arr[start]);
		}
		int mid = (start + end) / 2;
		MaxMin left = maxMinTournament(arr, start, mid);
		MaxMin right = maxMinTournament(arr, mid + 1, end);
		int currentMax = (left.max > right.max) ? left.max : right.max;
		int currentMin = (left.min < right.min) ? left.min : right.min;
		return new MaxMin(currentMax, currentMin);
	}

	// Frequency count
	public static Map<Integer, Integer> frequencyCount(int[] arr) {
		Map<Integer, Integer> result = new LinkedHashMap<>();
		for (int element : arr)
			result.merge(element, 1, Integer::sum);
		return result;
	}

	// Frequencies of limited range array elements
	public static void frequencyCountRange(int[] arr, int N, int P) {
		Map<Integer, Integer> result = new LinkedHashMap<>();
		for (int i = 1; i <= P; i++) {
			result.put(i, 0);
		}

		for (int element : arr) {
			result.put(element, result.get(element) + 1);
		}
		for (Map.Entry<Integer, Integer> ele : result.entrySet()) {
			System.out.print(ele.getValue() + " ");
		}
	}

	// Kth frequency
	public static void kThFrequency(int[] arr, int n, int k) {
		Map<Integer, Integer> result = new HashMap<>();
		for (int element : arr) {
			result.merge(element, 1, Integer::sum);
		}

		/*
		 * for (int key : result.keySet()) { if (result.get(key) <= k)
		 * result.remove(key); }
		 */

		Iterator<Map.Entry<Integer, Integer>> itr = result.entrySet().iterator();
		while (itr.hasNext()) {
			if (itr.next().getValue() <= k)
				itr.remove();
		}

		if (result.isEmpty())
			System.out.println(-1);
		else
			System.out.println(result.keySet());
	}

	public static void main(String[] args) {
		// Reversing string
		String str = "Hello World";
		StringBuilder sb = new StringBuilder(str);
		System.out.println("Reverse of " + str + " is: " + reverseStringUsingIteration(str));
		reverseStringUsingRecursion(sb, 0, sb.length() - 1);
		System.out.println("Reverse of " + str + " is: " + String.valueOf(sb.toString()) + "\n");

		// Reversing array
		Integer[] arr = new Integer[] { 10, 20, 3, 7, 2, 22, 87, 1 };
		System.out.println("Original array: " + Arrays.toString(arr));
		reverseArrayIteration(arr);
		System.out.println("Reverse 1: " + Arrays.toString(arr));
		reverseArrayRecursion(arr, 0, arr.length - 1);
		System.out.println("Reverse 2: " + Arrays.toString(arr) + "\n");

		// Reverse squared sum
		int[] arr2 = new int[] { 1, 2, 3 };
		System.out.print("Reverse squared sum of " + Arrays.toString(arr2) + " is: ");
		System.out.println(reverseSquaredSum(arr2) + "\n");

		// Sum of digits
		System.out.println("Sum of digits of 654 is: " + sumOfDigits(654));
		System.out.println("Sum of digits of 654 is: " + sumOfDigitsUsingRecursion(654));
		System.out.println("Sum of digits of 654 is: " + sumOfDigitsString("654"));
		System.out.println("Sum of digits of 654 is: " + sumOfDigitsTailRecursion(654, 0) + "\n");

		// Palindrome check
		System.out.println("Is akash palindrome? " + isPalindrome("akash"));
		System.out.println("Is akash palindrome? " + isPalindrome("Madam"));

		// Sum of elements in an array
		int[] arr3 = new int[] { 10, 20, 3, 40, 2, 7, 9 };
		System.out.println("The array is: " + Arrays.toString(arr3));
		System.out.println("The sum of all the elements is: " + sumArrayIteration(arr3));
		System.out.println("The sum of all the elements is: " + sumArrayRecursion(arr3, 0, arr3.length - 1));

		// Maximum and minimum of elements
		MaxMin obj1 = findMaxMinNaive(arr3);
		System.out.println("Maximum: " + obj1.max + " Minimum: " + obj1.min);
		obj1 = maxMinTournament(arr3, 0, arr3.length - 1);
		System.out.println("Maximum: " + obj1.max + " Minimum: " + obj1.min);

		// Frequency count
		int arr4[] = new int[] { 10, 20, 20, 10, 10, 20, 5, 20 };
		Map<Integer, Integer> fCount = frequencyCount(arr4);
		for (Map.Entry<Integer, Integer> ele : fCount.entrySet())
			System.out.println(ele.getKey() + ": " + ele.getValue());
		int arr5[] = new int[] { 2, 3, 2, 3, 5 };
		frequencyCountRange(arr5, arr5.length, 5);
		System.out.println();
		kThFrequency(arr5, arr5.length, 1);

	}
}
