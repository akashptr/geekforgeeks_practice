package number_theory_and_combinatorics;

import java.util.Arrays;

public class LeastPrimeFactorsUptoN {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(leastPrimeFactor(10)));
	}

	static int[] leastPrimeFactor(int n) {
		int[] mark = new int[n+1];
		for(int i=0; i<mark.length; i++)
			mark[i] = i;
		
		for(int i=2; i*i<=n ; i++) {
			if(mark[i] == i) {
				for(int j=i*i; j<=n; j+=i) {
					if(mark[j] == j)
						mark[j] = i;
				}				
			}
		}
		return mark;
	}

}
