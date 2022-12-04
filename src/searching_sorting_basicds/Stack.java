package searching_sorting_basicds;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Stack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(checkValid("0011"));
		System.out.println(checkValid("001"));
		System.out.println(checkValid("10100"));
		System.out.println(Arrays.toString(contestScore(new int[] { 10, 8, 9, 6, 5, 7 })));
		Arrays.stream(contestScore(new int[] { 2, 5, 6, 3, 9 })).forEach(x -> System.out.print(x + " "));
		System.out.println();
	}

	/*
	 * Geek participated in a contest for N consecutive days. The score of each day
	 * is given by an array arr, the task is to find the nearest Score which is less
	 * than the ith (1 <= i <= N) day's score in the days from i+1 to N. If no days
	 * exist with a smaller score then print -1.
	 */
	static int[] contestScore(int[] arr) {
		Deque<Integer> stack = new LinkedList<>();
		int numberOfElements = arr.length;
		int[] result = new int[numberOfElements];
		result[numberOfElements - 1] = -1;
		stack.push(numberOfElements - 1);

		for (int i = numberOfElements - 2; i >= 0; i--) {
			while (!stack.isEmpty() && arr[i] <= arr[stack.peek()])
				stack.pop();
			if (stack.isEmpty())
				result[i] = -1;
			else
				result[i] = stack.peek() + 1;
			stack.push(i);
		}
		return result;
	}

	/*
	 * Given binary string str of size N the task is to check if the given string is
	 * valid or not. A string is called valid if the number of 0's equals the number
	 * of 1's and at any moment starting from the left of the string number 0's must
	 * be greater than or equals to the number of 1's.
	 */
	static boolean checkValid(String binary) {
		int n = binary.length();
		int count0 = 0;
		int count1 = 0;
		for (int i = 0; i < n; i++) {
			if (binary.charAt(i) == '0') {
				count0++;
			} else {
				count1++;
				if (count0 < count1)
					return false;
			}
		}
		if (count0 == count1)
			return true;
		return false;
	}

}
