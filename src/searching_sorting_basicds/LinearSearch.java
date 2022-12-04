package searching_sorting_basicds;

public class LinearSearch {

	public static void main(String[] args) {
		int[] arr1 = new int[] { 3, 2, 1, 10, 7, 2 };
		System.out.println("Index of 1 is: " + iterativeApproach(arr1, 1));
		System.out.println("Index of 5 is: " + iterativeApproach(arr1, 5));
		System.out.println("Index of 1 is: " + recursiveApproach(arr1, 0, 1));
		System.out.println("Index of 2 is: " + recursiveApproach(arr1, 0, 2));
		System.out.println("Index of 5 is: " + recursiveApproach(arr1, 0, 5));
	}

	/*
	 * There are N students in a class. Each student got arr[i] (1 <= i <= N) marks
	 * in mathematics exam. Geek loves mathematics and he got X marks. Now geek is
	 * curious to know, how many students in his class got marks greater than that
	 * of Geek's.
	 */
	static int findCount(int[] arr, int key) {
		int count = 0;
		for (int element : arr) {
			if (element > key)
				count++;
		}
		return count;
	}

	static int recursiveApproach(int[] arr, int start, int key) {
		if (start >= arr.length)
			return -1;
		if (arr[start] == key)
			return start;
		else
			return recursiveApproach(arr, start + 1, key);
	}

	static int iterativeApproach(int[] arr, int key) {
		int index = -1;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == key) {
				index = i;
				break;
			}
		}
		return index;
	}

}
