package array_string_greedy_bitmanipulation;

import java.util.Arrays;

public class PrefixSum2D {

	public static void main(String[] args) {
		int[][] arr = {	{10, 20, 30},
						{5, 10, 20},
						{2, 4, 6} };
		System.out.println(Arrays.deepToString(prefixSum2D(arr)));
	}

	/*
	 * 	Original		Prefix sum
	 * 
	 * 	10	20	30		10	30	60
	 * 	5	10	20		15	45	95
	 * 	2	4	6		17	51	107
	 * 
	 */
	public static int[][] prefixSum2D(int[][] arr) {
		int row = arr.length;
		int col = arr[0].length;
		int[][] ps = new int[row][col];
		
		for(int r=0; r<row; r++) {
			int rowSum = 0;
			for(int c=0; c<col; c++) {
				rowSum += arr[r][c];
				if(r==0)
					ps[r][c] = rowSum;
				else
					ps[r][c] = ps[r-1][c] + rowSum;
			}
		}
		return ps;
	}
}
