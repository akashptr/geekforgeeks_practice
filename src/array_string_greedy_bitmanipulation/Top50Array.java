package array_string_greedy_bitmanipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class Top50Array {

	public static void main(String[] args) {
		System.out.println(peakElement(new int[] { 2, 3 }, 2));
		int[] arr1 = new int[] { 2, 3, 7, 10, 12 };
		int[] arr2 = new int[] { 1, 5, 7, 8 };
		System.out.println(maxPathSum(arr1, arr2));
		int[] arr3 = new int[] { 100, 180, 260, 310, 40, 535, 695 };
		System.out.println(stockBuySell(arr3, arr3.length));
		System.out.println(Arrays.toString(sumClosest(new int[] { 10, 22, 28, 29, 30, 40 }, 54)));
		System.out.println(findMinDiff(Arrays.asList(3, 4, 1, 9, 56, 7, 9, 12), 8, 5));

	}

	// Level 2

	/*
	 * Chocolate Distribution Problem
	 * 
	 * Given an array A[ ] of positive integers of size N, where each value
	 * represents the number of chocolates in a packet. Each packet can have a
	 * variable number of chocolates. There are M students, the task is to
	 * distribute chocolate packets among M students such that : 1. Each student
	 * gets exactly one packet. 2. The difference between maximum number of
	 * chocolates given to a student and minimum number of chocolates given to a
	 * student is minimum.
	 */
	static long findMinDiff(List<Integer> a, int n, int m) {
		Collections.sort(a);
		int start = 0;
		int end = m - 1;
		long minDiff = Long.MAX_VALUE;
		while (end < n) {
			int diff = a.get(end) - a.get(start);
			if (diff < minDiff)
				minDiff = diff;
			start++;
			end++;
		}
		return minDiff;
	}

	/*
	 * Given a sorted array arr[] of size N and a number X, find a pair in array
	 * whose sum is closest to X.
	 */
	static int[] sumClosest(int[] arr, int x) {
		int start = 0, end = arr.length - 1;
		int closestSum = 0;
		int[] result = new int[2];
		while (start < end) {
			int sum = arr[start] + arr[end];
			int closeness = (int) Math.abs(x - sum);
			if (closeness < (int) Math.abs(x - closestSum)) {
				closestSum = sum;
				result[0] = arr[start];
				result[1] = arr[end];
			}
			if (sum > x)
				end--;
			else
				start++;
		}
		return result;
	}

	/*
	 * The cost of stock on each day is given in an array A[] of size N. Find all
	 * the segment of days on which you buy and sell the stock so that in between
	 * those days your profit is maximum.
	 * 
	 */
	static ArrayList<ArrayList<Integer>> stockBuySell(int A[], int n) {
		// code here
		ArrayList<ArrayList<Integer>> lst = new ArrayList<>();
		for (int i = 1; i < n; ++i) {
			if (A[i] > A[i - 1]) {
				ArrayList<Integer> temp = new ArrayList<>(2);
				temp.add(i - 1);
				temp.add(i);
				lst.add(temp);
			}
		}
		return lst;
	}

	/*
	 * Given an unsorted array Arr of size N of positive integers. One number 'A'
	 * from set {1, 2, …N} is missing and one number 'B' occurs twice in array. Find
	 * these two numbers.
	 */
	static int[] findTwoElement(int arr[], int n) {
		// code here
		int[] ans = new int[2];
		Map<Integer, Integer> m = new HashMap<>();

		// inserting value in Hash Map
		for (int i = 0; i < n; i++) {
			if (m.containsKey(arr[i])) {
				m.put(arr[i], m.get(arr[i]) + 1);
			} else {
				m.put(arr[i], 1);
			}
		}

		// finding repeated element
		for (int i = 0; i < n; i++) {
			if (m.get(arr[i]) == 2) {
				ans[0] = arr[i];
				break;
			}
		}

		// finding missing element
		for (int i = 0; i < n; i++) {
			if (m.containsKey(i + 1)) {
				continue;
			} else {
				ans[1] = i + 1;
				break;
			}
		}

		return ans;
	}

	/*
	 * Given two sorted arrays A and B of size M and N respectively. Each array may
	 * have some elements in common with the other array. Find the maximum sum of a
	 * path from the beginning of any array to the end of any of the two arrays. We
	 * can switch from one array to another array only at the common elements.Both
	 * the arrays are sorted. Note: Only one repeated value is considered in the
	 * valid path sum.
	 * 
	 * Example: Input: M = 5, N = 4 A[] = {2,3,7,10,12} B[] = {1,5,7,8} Output: 35
	 * Explanation: The path will be 1+5+7+10+12 = 35.
	 */
	static int maxPathSum(int ar1[], int ar2[]) {
		int i = 0, j = 0, sum1 = 0, sum2 = 0, ans = 0;

		while (i < ar1.length && j < ar2.length) {
			if (ar1[i] == ar2[j]) {
				ans += Math.max(sum1, sum2);
				sum1 = 0;
				sum2 = 0;
				ans += ar1[i]; // we can add any one of the two as both will be equal
				i++;
				j++;
			} else if (ar1[i] < ar2[j])
				sum1 += ar1[i++];
			else
				sum2 += ar2[j++];
		}

		while (i < ar1.length)
			sum1 += ar1[i++];

		while (j < ar2.length)
			sum2 += ar2[j++];

		ans += Math.max(sum1, sum2);
		return ans;
	}

	/*
	 * Given an array A[] of N positive integers. The task is to find the maximum of
	 * j - i subjected to the constraint of A[i] < A[j] and i < j.
	 */
	static int maxIndexDiff(int A[], int N) {
		int postfix[] = new int[N];
		postfix[N - 1] = A[N - 1];
		for (int i = N - 2; i >= 0; i--) {
			postfix[i] = Math.max(postfix[i + 1], A[i]);
		}
		int i = 0, j = 0, ans = 0;
		while (i < N && j < N) {
			if (A[i] <= postfix[j]) {
				ans = Math.max(ans, j - i);
				j++;
			} else {
				i++;
			}
		}
		return ans;
	}

	/*
	 * Given an array arr[] of N non-negative integers representing the height of
	 * blocks. If width of each block is 1, compute how much water can be trapped
	 * between the blocks during the rainy season.
	 */
	static long trappingWater(int arr[], int n) {
		// Your code here
		int larr[] = new int[n];
		int rarr[] = new int[n];
		larr[0] = arr[0];
		for (int i = 1; i < n; i++) {
			larr[i] = Math.max(arr[i], larr[i - 1]);
		}
		rarr[n - 1] = arr[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			rarr[i] = Math.max(arr[i], rarr[i + 1]);
		}
		long res = 0;
		for (int i = 1; i < n - 1; i++) {
			res = res + (Math.min(rarr[i], larr[i]) - arr[i]);
		}
		return res;
	}

	/*
	 * Given a sorted array arr[] of distinct integers. Sort the array into a
	 * wave-like array(In Place). In other words, arrange the elements into a
	 * sequence such that arr[1] >= arr[2] <= arr[3] >= arr[4] <= arr[5].....
	 * 
	 * If there are multiple solutions, find the lexicographically smallest one.
	 */
	public static void convertToWave(int n, int[] a) {
		for (int i = 0; i < n - 1; i += 2) {
			int temp = a[i + 1];
			a[i + 1] = a[i];
			a[i] = temp;
		}
	}

	/*
	 * Boyer-Moore Majority Voting Algorithm: Given an array A of N elements. Find
	 * the majority element in the array. A majority element in an array A of size N
	 * is an element that appears more than N/2 times in the array.
	 */
	static int majorityElement(int nums[], int size) {
		// your code here
		int count = 0, candidate = -1;

		// Finding majority candidate
		for (int index = 0; index < nums.length; index++) {
			if (count == 0) {
				candidate = nums[index];
				count = 1;
			} else {
				if (nums[index] == candidate)
					count++;
				else
					count--;
			}
		}

		// Checking if majority candidate occurs more than
		// n/2 times
		count = 0;
		for (int index = 0; index < nums.length; index++) {
			if (nums[index] == candidate)
				count++;
		}
		if (count > (nums.length / 2))
			return candidate;
		return -1;
	}

	// Implement 2 stacks using one array.
	class TwoStack {
		private int size;
		private int top1, top2;
		private int arr[];

		TwoStack() {
			size = 100;
			arr = new int[size];
			top1 = -1;
			top2 = size;
		}

		// Function to push an integer into the stack1.
		void push1(int x) {
			if (top1 >= top2)
				return;
			arr[++top1] = x;
		}

		// Function to push an integer into the stack2.
		void push2(int x, TwoStack sq) {
			if (top1 >= top2)
				return;
			arr[--top2] = x;
		}

		// Function to remove an element from top of the stack1.
		int pop1(TwoStack sq) {
			if (top1 == -1)
				return -1;
			return arr[top1--];
		}

		// Function to remove an element from top of the stack2.
		int pop2(TwoStack sq) {
			if (top2 == size)
				return -1;
			return arr[top2++];
		}
	}

	/*
	 * Given two arrays: a1[0..n-1] of size n and a2[0..m-1] of size m. Task is to
	 * check whether a2[] is a subset of a1[] or not. Both the arrays can be sorted
	 * or unsorted.
	 */
	String isSubset(long a1[], long a2[], long n, long m) {
		Map<Long, Long> table = new HashMap<>();
		for (long ele : a1) {
			table.merge(ele, 1L, Long::sum);
		}
		String result = "Yes";
		for (long ele : a2) {
			if (!table.containsKey(ele)) {
				result = "No";
				break;
			} else {
				table.put(ele, table.get(ele) - 1);
				if (table.get(ele) == 0)
					table.remove(ele);
			}
		}
		return result;
	}

	/*
	 * Given a matrix of size r*c. Traverse the matrix in spiral form.
	 */
	static ArrayList<Integer> spirallyTraverse(int arr[][], int r, int c) {
		ArrayList<Integer> list = new ArrayList<Integer>();

		int startrow = 0;
		int startcol = 0;

		int lastrow = arr.length - 1;
		int lastcol = arr[0].length - 1;

		while (startrow <= lastrow && startcol <= lastcol) {

			int row = startrow;
			int col = startcol;

			// going right
			while (col + 1 <= lastcol) {

				list.add(arr[row][col]);
				col += 1;
			}

			// col-=1;
			// row+=1;

			// going down
			while (row + 1 <= lastrow) {
				list.add(arr[row][col]);
				row += 1;
			}
			lastcol -= 1; // shrinking lastcolumn
			// going left:
			// while going back check we are not going back in same row
			while (col - 1 >= startcol && row > startrow) {
				list.add(arr[row][col]);
				col -= 1;
			}

			// going up
			// while going back beck check we are not going back to same column
			while (row - 1 > startrow && col <= lastcol) {
				list.add(arr[row][col]);
				row -= 1;
			}

			lastrow -= 1; // shrinking lastrow

			list.add(arr[row][col]);

			// increasing index diagonally
			startrow += 1;
			startcol += 1;
		}
		return list;
	}

	/*
	 * Given a boolean 2D array of n x m dimensions where each row is sorted. Find
	 * the 0-based index of the first row that has the maximum number of 1's.
	 * 
	 * Example
	 * 
	 * Input: N = 4 , M = 4 Arr[][] = {{0, 1, 1, 1}, {0, 0, 1, 1}, {1, 1, 1, 1}, {0,
	 * 0, 0, 0}} Output: 2 Explanation: Row 2 contains 4 1's (0-based indexing).
	 */
	static int rowWithMax1s(int arr[][], int n, int m) {
		int curRow = 0, curCol = m - 1, maxRow = -1;
		while (curRow < n && curCol >= 0) {
			if (arr[curRow][curCol] == 1) {
				maxRow = curRow;
				curCol--;
			} else
				curRow++;
		}
		return maxRow;
	}

	/*
	 * You are given an array arr[] of N integers including 0. The task is to find
	 * the smallest positive number missing from the array.
	 */
	static int missingNumber(int arr[], int size) {
		// Your code here
		Map<Integer, Integer> m = new HashMap<>();
		for (int i = 0; i < size; i++)
			m.put(arr[i], 1);
		for (int i = 0; i <= size; i++)
			if (m.containsKey(i + 1))
				continue;
			else
				return i + 1;
		return -1;
	}

	/*
	 * Given an array arr of size n and an integer X. Find if there's a triplet in
	 * the array which sums up to the given integer X.
	 */
	public static boolean find3Numbers(int A[], int n, int X) {
		Arrays.sort(A);
		for (int i = 0; i < n; i++) {
			int start = i + 1;
			int end = n - 1;

			while (start < end) {
				int sum = A[i] + A[start] + A[end];
				if (sum == X)
					return true;
				else if (sum > X)
					end--;
				else
					start++;
			}
		}
		return false;
	}

	/*
	 * The stock span problem is a financial problem where we have a series of n
	 * daily price quotes for a stock and we need to calculate the span of stocks
	 * price for all n days. The span Si of the stocks price on a given day i is
	 * defined as the maximum number of consecutive days just before the given day,
	 * for which the price of the stock on the current day is less than or equal to
	 * its price on the given day.
	 * 
	 * Example: Input: N = 7, price[] = [100 80 60 70 60 75 85] Output: 1 1 1 2 1 4
	 * 6 Explanation: Traversing the given input span for 100 will be 1, 80 is
	 * smaller than 100 so the span is 1, 60 is smaller than 80 so the span is 1, 70
	 * is greater than 60 so the span is 2 and so on. Hence the output will be 1 1 1
	 * 2 1 4 6.
	 */
	public static int[] calculateSpan(int price[], int n) {
		int arr[] = new int[n];
		Deque<Integer> s = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			if (s.size() == 0) {
				arr[i] = 1;
			} else if (s.size() > 0 && price[s.peek()] > price[i]) {
				arr[i] = i - s.peek();
			} else if (s.size() > 0 && price[s.peek()] <= price[i]) {
				while (s.size() > 0 && price[s.peek()] <= price[i]) {
					s.pop();
				}
				if (s.size() == 0) {
					arr[i] = i + 1;
				} else {
					arr[i] = i - s.peek();
				}
			}
			s.push(i);
		}
		return arr;
	}

	/*
	 * Given an array of N integers arr[] where each element represents the max
	 * length(* MAX not Exact) of the jump that can be made forward from that
	 * element. Find the minimum number of jumps to reach the end of the array
	 * (starting from the first element). If an element is 0, then you cannot move
	 * through that element.
	 * 
	 * Note: Return -1 if you can't reach the end of the array.
	 */
	static int minJumps(int[] arr, int n) {
		int maxR = arr[0];
		int step = arr[0];
		int jump = 1;
		if (n == 1)
			return 0;
		else if (arr[0] == 0)
			return -1;
		else {
			for (int i = 1; i < n; i++) {
				if (i == (n - 1)) {
					return jump;
				}
				maxR = Math.max(maxR, i + arr[i]);
				step--;
				if (step == 0) {
					jump++;
					if (i >= maxR) {
						return -1;
					}
					step = maxR - i;
				}
			}
		}
		return -1;
	}

	/*
	 * Given arrival and departure times of all trains that reach a railway station.
	 * Find the minimum number of platforms required for the railway station so that
	 * no train is kept waiting. Consider that all the trains arrive on the same day
	 * and leave on the same day. Arrival and departure time can never be the same
	 * for a train but we can have arrival time of one train equal to departure time
	 * of the other. At any given instance of time, same platform can not be used
	 * for both departure of a train and arrival of another train. In such cases, we
	 * need different platforms.
	 */
	static int findPlatform(int arr[], int dep[], int n) {
		// add your code here
		Arrays.sort(arr);
		Arrays.sort(dep);
		int curPlat = 0, maxPlat = 0;
		int i = 0, j = 0;
		while (i < arr.length && j < dep.length) {
			if (arr[i] <= dep[j]) {
				curPlat++;
				i++;
			} else {
				curPlat--;
				j++;
			}
			maxPlat = Math.max(curPlat, maxPlat);
		}
		return maxPlat;
	}

	/*
	 * Given an array(0-based indexing), you have to find the max sum of i*A[i]
	 * where A[i] is the element at index i in the array. The only operation allowed
	 * is to rotate(clock-wise or counter clock-wise) the array any number of times.
	 */
	static int max_sum(int A[], int n) {
		int curSum = 0, sum = 0;
		for (int i = 0; i < n; i++) {
			curSum += (A[i] * i);
			sum += A[i];
		}
		int maxSum = curSum;
		for (int i = 0; i < n - 1; i++) {
			curSum += (n * A[i]) - sum;
			maxSum = Math.max(maxSum, curSum);
		}
		return maxSum;
	}

	/*
	 * sorted(in ascending order) array A[ ] with distinct elements is rotated at
	 * some unknown point, the task is to find the minimum element in it.
	 */
	static int findMin(int arr[], int n) {
		int start = 0, end = n - 1;
		int mid = (start + end) / 2;
		if (arr[start] < arr[end])
			return arr[0];

		while (start < end) {
			if (arr[mid] > arr[end])
				start = mid + 1;
			else
				end = mid;
			mid = (start + end) / 2;
		}
		return arr[mid];
	}

	/*
	 * Given an array of positive integers. Find the length of the longest
	 * sub-sequence such that elements in the subsequence are consecutive integers,
	 * the consecutive numbers can be in any order.
	 */
	static int findLongestConseqSubseq(int arr[], int N) {
		HashSet<Integer> set = new HashSet<>();
		for (int ele : arr)
			set.add(ele);
		int maxSize = 0;
		while (!set.isEmpty()) {
			int ter = set.iterator().next();
			while (set.contains(ter - 1))
				ter = ter - 1;
			int size = 1;
			while (set.contains(ter + 1)) {
				set.remove(ter);
				size++;
				ter++;
			}
			set.remove(ter);
			maxSize = Math.max(maxSize, size);
		}
		return maxSize;
	}

	/*
	 * Problem: Given an array Arr[] that contains N integers (may be positive,
	 * negative or zero). Find the product of the maximum product sub-array.
	 * 
	 * Explanation : 1.) Through intuition explanation we know that if all the
	 * elements are positive or the negative elements are even then your answer will
	 * be product of complete array which u will get in variable l and r at the last
	 * iteration.
	 * 
	 * 2.) But if negative elements are odd then u have to remove one negative
	 * element and it is sure that it will be either right of max prefix product or
	 * left of max suffix product. So u need not to modify anything in your code as
	 * u are getting prefix product in l and suffix product in r.
	 * 
	 * 3.) If array also contains 0 then your l and r will become 0 at that
	 * point...then just update it to 1(or else u will keep multiplying with 0) to
	 * get the product ahead making another sub-array.
	 */
	static long maxProductSubarray(int[] arr, int n) {
		long ans = arr[0];
		long l = 1, r = 1;

		for (int i = 0; i < n; i++) {
			l = l == 0 ? 1 : l;
			r = r == 0 ? 1 : r;

			l *= arr[i];
			r *= arr[n - i - 1];

			ans = Math.max(ans, Math.max(l, r));
		}

		return ans;
	}

	// Factorial of a large number.
	static ArrayList<Integer> factorial(int N) {
		ArrayList<Integer> al = new ArrayList<>();
		al.add(1);
		for (int i = 2; i <= N; i++) {
			ArrayList<Integer> newL = new ArrayList<>();
			int l = al.size() - 1;
			int carry = 0;
			int ans = 0;
			while (l >= 0) {
				ans = i * al.get(l) + carry;
				int num = ans % 10;
				carry = ans / 10;
				newL.add(0, num);
				l--;
			}
			while (carry != 0) {
				newL.add(0, carry % 10);
				carry /= 10;
			}
			al = newL;
		}
		return al;
	}

	// Given an array of positive and negative numbers. Find if there is a subarray
	// (of size at-least one) with 0 sum.
	static boolean findsum(int arr[], int n) {
		Set<Integer> set = new HashSet<>();
		set.add(0);
		for (int i = 0, prefixSum = 0; i < n; i++) {
			prefixSum += arr[i];
			if (set.contains(prefixSum))
				return true;
			set.add(prefixSum);
		}
		return false;
	}

	/*
	 * Given an unsorted array Arr of N positive and negative numbers. Your task is
	 * to create an array of alternate positive and negative numbers without
	 * changing the relative order of positive and negative numbers. Note: Array
	 * should start with a positive number.
	 */
	static void rearrange(int arr[], int n) {
		Queue<Integer> positive = new LinkedList<>();
		Queue<Integer> negetive = new LinkedList<>();

		for (int ele : arr) {
			if (ele < 0)
				negetive.add(ele);
			else
				positive.add(ele);
		}
		int i = 0;
		while (i < n) {
			if (!positive.isEmpty())
				arr[i++] = positive.remove();
			if (i == n)
				break;
			if (!negetive.isEmpty())
				arr[i++] = negetive.remove();
		}
	}

	/*
	 * Given an array containing 0s and 1s. Find the number of subarrays having
	 * equal number of 0s and 1s.
	 */
	static int countSubarrWithEqualZeroAndOne(int arr[], int n) {
		HashMap<Integer, Integer> mp = new HashMap<>();
		int ans = 0, sum = 0;
		mp.put(0, 1);
		for (int val : arr) {
			if (val == 0)
				sum += -1;
			else
				sum += 1;
			if (mp.containsKey(sum)) {
				ans += mp.get(sum);
				mp.put(sum, mp.get(sum) + 1);
			} else
				mp.put(sum, 1);
		}
		return ans;
	}

	/*
	 * Find the first non-repeating element in a given array arr of N integers.
	 * Note: Array consists of only positive and negative integers and not zero.
	 */
	public int firstNonRepeating(int arr[], int n) {
		Map<Integer, Integer> map = new LinkedHashMap<>();
		for (int element : arr) {
			map.merge(element, 1, Integer::sum);
		}
		for (int key : map.keySet()) {
			if (map.get(key) == 1)
				return key;
		}
		return 0;
	}

	/*
	 * Given an array arr[] of size n, find the first repeating element. The element
	 * should occurs more than once and the index of its first occurrence should be
	 * the smallest.
	 */
	public static int firstRepeated(int[] arr, int n) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int element : arr) {
			map.merge(element, 1, Integer::sum);
		}
		for (int i = 0; i < n; i++) {
			if (map.get(arr[i]) > 1)
				return i + 1;
		}
		return -1;
	}

	/*
	 * Given three arrays sorted in increasing order. Find the elements that are
	 * common in all three arrays. Note: can you take care of the duplicates without
	 * using any additional Data Structure?
	 */
	ArrayList<Integer> commonElements(int a[], int b[], int c[], int n1, int n2, int n3) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		int i = 0, k = 0, j = 0;
		while (i < n1 && j < n2 && k < n3) {
			if (a[i] == b[j] && b[j] == c[k]) {
				if (!result.contains(a[i])) {
					result.add(a[i]);
				}
				i++;
				j++;
				k++;
			} else if (a[i] < b[j])
				i++;
			else if (b[j] < c[k])
				j++;
			else
				k++;
		}
		return result;
	}

	/*
	 * Given an array a[] of size N which contains elements from 0 to N-1, you need
	 * to find all the elements occurring more than once in the given array.
	 */
	public static ArrayList<Integer> duplicates(int arr[], int n) {
		int newArray[] = new int[n];
		for (int element : arr) {
			newArray[element]++;
		}
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (newArray[i] > 1)
				list.add(i);
		}
		if (list.isEmpty())
			list.add(-1);
		return list;
	}

	/*
	 * Given an array of N integers, and an integer K, find the number of pairs of
	 * elements in the array whose sum is equal to K.
	 */
	int getPairsCount(int[] arr, int n, int k) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int element : arr)
			map.merge(element, 1, Integer::sum);
		int count = 0;
		for (int element : arr) {
			int complement = k - element;
			if (map.containsKey(complement)) {
				if (complement == element) {
					count += (map.get(complement) - 1);
				} else
					count += map.get(complement);
			}
		}
		return (count / 2);
	}

	// Clock-wise cyclic rotation of an array.
	public void rotateClockwise(int arr[], int n) {
		int last = arr[n - 1];
		for (int i = n - 1; i > 0; i--)
			arr[i] = arr[i - 1];
		arr[0] = last;
	}

	// Level 1

	// Function to find the peak element
	// An element is called a peak element if its value is not smaller than the
	// value of its adjacent elements(if they exists).
	// arr[]: input array
	// n: size of array a[]
	public static int peakElement(int[] arr, int n) {
		int lower = 0;
		int higher = n - 1;
		int left, right, current;
		while (lower <= higher) {
			int mid = (lower + higher) / 2;
			current = arr[mid];
			if (mid == lower)
				left = Integer.MIN_VALUE;
			else
				left = arr[mid - 1];

			if (mid == higher)
				right = Integer.MIN_VALUE;
			else
				right = arr[mid + 1];
			if (left <= current && current >= right)
				return mid;
			if (left > current)
				higher = mid - 1;
			else
				lower = mid + 1;
		}
		return -1;
	}

	private static void swap(int[] arr, int m, int n) {
		int temp = arr[m];
		arr[m] = arr[n];
		arr[n] = temp;
	}

	// Find kth smallest element
	public static int kthSmallest(int[] arr, int l, int r, int k) {
		if (r == 0)
			return arr[0];
		while (l < r) {
			int p = partition(arr, l, r);
			if (p == k - 1) {
				return arr[p];
			} else if (p < k - 1) {
				l = p + 1;
			} else {
				r = p - 1;
			}
		}
		return arr[l];
	}

	private static int partition(int[] arr, int l, int r) {
		Random random = new Random();
		int pivot_index = random.nextInt(r - l) + l;

		int pivot = arr[pivot_index];
		swap(arr, r, pivot_index);

		int left = l;

		for (int i = left; i <= r; i++) {
			if (arr[i] < pivot) {
				swap(arr, i, left);
				left++;
			}
		}
		swap(arr, r, left);
		return left;

	}

	// Dutch National Flag algorithm: Sort an array of 0, 1 and 2s.
	public static void sort012(int a[], int n) {
		// code here
		int low = 0, mid = 0, high = n - 1;
		while (mid <= high) {
			if (a[mid] < 1) {
				swap(a, low, mid);
				mid++;
				low++;
			} else if (a[mid] > 1) {
				swap(a, high, mid);
				high--;
			} else
				mid++;
		}
	}

	// Function to find a continuous sub-array which adds up to a given number.
	static ArrayList<Integer> subarraySum(int[] arr, int n, int s) {
		// Your code here
		int i = 0;
		int j = 0;
		int sum = arr[i];
		while (j < n) {
			if (sum == s)
				break;
			else if (sum < s) {
				j++;
				if (j < n)
					sum += arr[j];
			} else {
				sum -= arr[i];
				i++;
			}
		}
		ArrayList<Integer> result = new ArrayList<>();
		if (j == n || i > j)
			result.add(-1);
		else {
			result.add(i + 1);
			result.add(j + 1);
		}
		return result;
	}

	// Move all negative elements at the end of the array
	public void segregateElements(int arr[], int n) {
		int[] temp = new int[n];
		int i = 0;
		for (int element : arr) {
			if (element >= 0)
				temp[i++] = element;
		}
		for (int element : arr)
			if (element < 0)
				temp[i++] = element;
		for (int j = 0; j < n; j++)
			arr[j] = temp[j];
	}

	// Union of two sorted arrays
	public static int doUnion(int a[], int n, int b[], int m) {
		// Your code here
		Set<Integer> unionSet = new HashSet<>();
		for (int x : a)
			unionSet.add(x);
		for (int y : b)
			unionSet.add(y);
		return unionSet.size();
	}
}
