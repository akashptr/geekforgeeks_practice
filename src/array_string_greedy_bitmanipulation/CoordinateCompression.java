package array_string_greedy_bitmanipulation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CoordinateCompression {

	public static void main(String[] args) {
		int[] arr1 = {10, 40, 20};
		int[] arr2 = {5, 10, 40, 30, 20};
		sol1(arr1);
		System.out.println(Arrays.toString(arr1));
		sol1(arr2);
		System.out.println(Arrays.toString(arr2));
	}
	
	/*
	 * Problem 1: Given an array with N distinct elements, convert the given array to a form
	 * where all elements are in the range from 0 to N-1. The order of elements is
	 * the same, i.e., 0 is placed in the place of the smallest element, 1 is placed
	 * for the second smallest element, … N-1 is placed for the largest element.
	 * 
	 * Example: 
	 * Input: arr[] = {10, 40, 20} 
	 * Output: arr[] = {0, 2, 1}
	 * 
	 * Input: arr[] = {5, 10, 40, 30, 20} 
	 * Output: arr[] = {0, 1, 4, 3, 2}
	 */
	public static void sol1(int[] arr) {
		//int[] temp = Arrays.copyOf(arr, arr.length);
		int[] temp = arr.clone();
		Arrays.sort(temp);
		Map<Integer, Integer> map = new HashMap<>();
		for(int i=0; i<temp.length; i++) {
			map.put(temp[i], i);
		}
		for(int i=0; i<arr.length; i++) {
			arr[i] = map.get(arr[i]);
		}
	}
}
