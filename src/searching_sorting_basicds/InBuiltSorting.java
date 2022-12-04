package searching_sorting_basicds;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class InBuiltSorting {

	public static void main(String[] args) {
		kDistLargeValues(new int[] {4, 1, 3}, 2);
		kDistLargeValues(new int[] {4, 8, 1, 8}, 3);
		kDistLargeValues(new int[] {4, 8, 1, 8}, 4);
		
	}

	static void kDistLargeValues(int[] arr, int k) {
		Arrays.sort(arr);
		for (int low = 0, high = arr.length - 1; low < high; low++, high--) {
			int temp = arr[low];
			arr[low] = arr[high];
			arr[high] = temp;
		}
		Set<Integer> set = new LinkedHashSet<>();
		for(int ele : arr) {
			if(k > 0) {
				if(!set.contains(ele)) {
					set.add(ele);
					k--;
				}
			}
			else
				break;
		}
		if(k == 0 && !set.isEmpty()) {
			set.forEach(x -> System.out.print(x + " "));
			System.out.println();
		}
		else
			System.out.println("-1");
	
	}

	/*
	 * Given an array arr of integers of size N and an integer K, the task is to
	 * find the K larger values of the array (repetition allowed)
	 */
	static void kLargerValues(Integer arr[], int k) {
		Arrays.sort(arr, Collections.reverseOrder());
		for (int i = 0; i < k; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	static void findProducts(Product[] arr, int k) {
		Arrays.sort(arr);
		for (int i = 0; i < k; i++)
			System.out.println(arr[i].name);
	}

	class Product implements Comparable<Product> {
		String name;
		int price;

		public int compareTo(Product p) {
			if (this.price == p.price)
				return this.name.compareTo(p.name);
			return this.price - p.price;
		}
	}
}
