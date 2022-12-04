package number_theory_and_combinatorics;

public class GCDandLCM {

	public static void main(String[] args) {
		int a = 98, b = 56;
        System.out.println("GCD of " + a +" and " + b + " is " + gcd(a, b));
        System.out.println("LCM of " + a +" and " + b + " is " + lcm(a, b));
        System.out.println(-6%5);
        a = 3;
        b = 6;
        int c = 9;
        if(isPossible(a, b, c))
            System.out.println( "Possible" );
        else
            System.out.println( "Not Possible");
     
        // Second example
        a = 3; b = 6; c = 8;
        if(isPossible(a, b, c))
            System.out.println( "Possible") ;
        else
            System.out.println( "Not Possible");
     
        // Third example
        a = 2; b = 5; c = 1;
        if(isPossible(a, b, c))
            System.out.println( "Possible" );
        else
            System.out.println( "Not Possible");
        
	}

	/*
	 * Linear Diophantine Equation
	 * ax+by=c has a solution i.e. there are integral
	 * value of x and y exist if gcd(a, b) can divide c.
	 */
	static boolean isPossible(int a, int b, int c) {
		if(c % gcd(a, b) == 0)
			return true;
		return false;
	}
	
	/*
	 * LCM of two numbers.
	 */
	static int lcm(int a, int b) {
		return (a * b) / gcd(a, b);
	}
	
	/*
	 * Euclidean algorithm of GCD. 
	 */
	static int gcd(int a, int b) {
		if(b == 0)
			return a;
		return gcd(b, a%b);
	}
}
