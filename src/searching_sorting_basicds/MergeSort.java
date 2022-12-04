package searching_sorting_basicds;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		Product section1[] = new Product[3];
		section1[0] = new Product("maggi", 10);
		section1[1] = new Product("pasta", 50);
		section1[2] = new Product("noodles", 30);
		Product section2[] = new Product[] {new Product("cake", 10), new Product("butter", 30), new Product("bread", 30)};
		geekAndGroceries(section1, section2, 1, 2);
	}

	static void geekAndGroceries(Product[] arr1, Product[] arr2, int p, int q) {
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		Product[] arr3 = new Product[p + q];
		int ptr1 = 0, ptr2 = 0, ptr3 = 0;
		while(p > 0 && q > 0) {
			if(arr1[ptr1].compareTo(arr2[ptr2]) < 0) {
				arr3[ptr3++] = arr1[ptr1++];
				p--;
			}
			else {
				arr3[ptr3++] = arr2[ptr2++];
				q--;
			}
		}
		while(p > 0) {
			arr3[ptr3++] = arr1[ptr1++];
			p--;
		}
		while(q > 0) {
			arr3[ptr3++] = arr2[ptr2++];
			q--;
		}
		Arrays.stream(arr3).forEach(prod -> System.out.print(prod.name + " "));
		System.out.println();
	}
	
	static class Product implements Comparable<Product>{
		String name;
		int price;
		Product(String name, int price) {
			this.name = name;
			this.price = price;
		}
		public int compareTo(Product p) {
			if(this.price == p.price)
				return this.name.compareTo(p.name);
			return this.price - p.price;
		}
	}
	
	static void sort(int[] arr, int low, int high) {
		if (low < high) {
			int mid = (low + high) / 2;
			sort(arr, low, mid);
			sort(arr, mid + 1, high);
			merge(arr, low, mid, high);
		}
	}

	private static void merge(int[] arr, int low, int mid, int high) {
		int ptr1 = low, ptr2 = mid + 1, ptr3 = 0;
		int[] temp = new int[high - low + 1];

		while (ptr1 <= mid && ptr2 <= high) {
			if (arr[ptr1] <= arr[ptr2])
				temp[ptr3++] = arr[ptr1++];
			else
				temp[ptr3++] = arr[ptr2++];
		}
		while (ptr1 <= mid)
			temp[ptr3++] = arr[ptr1++];
		while (ptr2 <= high)
			temp[ptr3++] = arr[ptr2++];
		for (int i = low, j = 0; i <= high; i++, j++)
			arr[i] = temp[j];
	}

}
