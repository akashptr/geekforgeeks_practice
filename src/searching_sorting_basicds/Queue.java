package searching_sorting_basicds;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Queue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(nThGoodNumber(5));
		System.out.println(problem2(5));
		System.out.println(problem2(4));
		System.out.println(problem3(9, 3));
		System.out.println(problem3(25, 7));
		System.out.println(problem3Optimized(9, 3));
		System.out.println(problem3Optimized(25, 7));

		System.out.println(Arrays.toString(priorityQueueP1(new int[] { 450, 230, 730, 230, 150 })));
		System.out.println(Arrays.toString(priorityQueueP1(new int[] { 500, 500, 500 })));

		System.out.println(Arrays.toString(priorityQueueP2(new int[] { 10, 11, 10, 11, 12 }, 2, 3)));
		System.out.println(Arrays.toString(priorityQueueP2(new int[] { 1, 2, 3, 4, 5 }, 5, 1)));
	}

	static int[] priorityQueueP3(int[][] query) {
		java.util.Queue<Integer> pqu = new PriorityQueue<>(Collections.reverseOrder());
		java.util.Queue<Integer> qu = new LinkedList<>();
		java.util.Deque<Integer> stack = new LinkedList<>();

		for (int[] q : query) {
			switch (q[0]) {
			case 1:
				pqu.add(q[1]);
				break;
			case 2:
				qu.add(q[1]);
				break;
			default:
				stack.push(q[1]);
			}
		}
		
		int[] result = new int[pqu.size() + qu.size() + stack.size()];
		int i = 0;
		while(!pqu.isEmpty())
			result[i++] = pqu.remove();
		while(!qu.isEmpty())
			result[i++] = qu.remove();
		while(!stack.isEmpty())
			result[i++] = stack.pop();
		
		return result;
	}

	static int[] priorityQueueP2(int[] arr, int k, int m) {
		java.util.Queue<Integer> qu = new PriorityQueue<>(Collections.reverseOrder());
		int prefixSum = 0;
		for (int i = 0; i < k; i++)
			prefixSum += arr[i];
		qu.add(prefixSum);
		for (int j = k; j < arr.length; j++) {
			prefixSum += arr[j];
			prefixSum -= arr[j - k];
			qu.add(prefixSum);
		}
		int result[] = new int[m];
		for (int p = 0; p < m; p++)
			result[p] = qu.remove();
		return result;
	}

	static int[] priorityQueueP1(int[] arr) {
		java.util.Queue<Pair> qu = new PriorityQueue<>();
		for (int i = 1; i <= arr.length; i++)
			qu.add(new Pair(arr[i - 1], i));
		int[] result = new int[arr.length];
		for (int i = 0; i < result.length; i++)
			result[i] = qu.remove().position;
		return result;
	}

	static class Pair implements Comparable<Pair> {
		int data, position;

		Pair(int data, int pos) {
			this.data = data;
			this.position = pos;
		}

		@Override
		public int compareTo(Pair o) {
			if (o.data == this.data)
				return o.position - this.position;
			return o.data - this.data;
		}
	}

	static int problem3Optimized(int n, int k) {
		int left = 1, right = n;
		boolean directionForward = true;
		while (right - left + 1 > k) {
			if (directionForward)
				left += k;
			else
				right -= k;
			directionForward = !directionForward;
		}
		if (directionForward)
			return right;
		return left;
	}

	static int problem3(int n, int k) {
		java.util.Deque<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= n; i++)
			queue.add(i);

		boolean right = false;
		while (queue.size() > k) {
			if (right) {
				for (int i = 0; i < k; i++)
					queue.removeLast();
			} else {
				for (int i = 0; i < k; i++)
					queue.removeFirst();
			}
			right = !right;
		}
		if (right)
			return queue.getFirst();
		return queue.getLast();
	}

	static int problem2(int n) {
		java.util.Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			queue.add(i);
		}
		while (queue.size() != 1) {
			queue.add(queue.remove());
			queue.remove();
		}
		return queue.remove();
	}

	static String nThGoodNumber(int n) {
		java.util.Queue<String> queue = new LinkedList<>();
		queue.add("1");
		queue.add("2");
		for (int i = 1; i < n; i++) {
			String curr = queue.remove();
			queue.add(curr + "1");
			queue.add(curr + "2");
		}
		return queue.remove();
	}

}
