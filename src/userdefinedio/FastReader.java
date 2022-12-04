package userdefinedio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FastReader {
	private BufferedReader br;
	private StringTokenizer st;

	public FastReader() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	private String next(String delim) {
		if (st == null || !st.hasMoreElements()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return st.nextToken(delim);
	}

	public int nextInt() {
		return Integer.parseInt(next(" "));
	}

	public long nextLong() {
		return Long.parseLong(next(" "));
	}

	public float nextFloat() {
		return Float.parseFloat(next(" "));
	}

	public double nextDouble() {
		return Double.parseDouble(next(" "));
	}

	public String nextLine() {
		return next("\n");
	}
}
