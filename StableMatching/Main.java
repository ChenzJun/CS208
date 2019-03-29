import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) {	
		
//		long StartTime = System.currentTimeMillis();
		
		InputStream inputStream = System.in;// new
											// FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		Task solver = new Task();
		solver.solve(in, out);
		
//		long endTime = System.currentTimeMillis();
//		System.out.println(endTime - StartTime);
		
		out.close();
	}

	static class Task {

		public void solve(InputReader in, PrintWriter out) {
			int num = in.nextInt();
			Queue<Integer> waitedSas = new LinkedList<>();
			int i = 0;
			int j = 0;
			
			// initial SAs
			String[] sas = new String[num];
			HashMap<String, Integer> sa2IndexMap = new HashMap<>();
			for (i = 0; i < num; i++) {
				String sa = in.next();
				sas[i] = sa;
				waitedSas.add(i);
				sa2IndexMap.put(sa, i);
			}
			
			// initial students
			String[] students = new String[num];
			HashMap<String, Integer> student2IndexMap = new HashMap<>();
			for (i = 0; i < num; i++) {
				String student = in.next();
				students[i] = student;
				student2IndexMap.put(student, i);
			}
			
			// initial sa preferred list
			int[][] saPerfered = new int[num][num];
			int[] flag = new int[num];
			for (i = 0; i < num; i++) {
				for (j = 0; j < num; j++) {
					saPerfered[i][j] = student2IndexMap.get(in.next());
				}
			}
			
			// initial student preferred list
			int[][] studentPerferredRanks = new int[num][num];
			for (i = 0; i < num; i++) {
				for (j = 0; j < num; j++) {
					studentPerferredRanks[i][sa2IndexMap.get(in.next())] = j;
				}
			}
			
			// initial mached groups
			int[] matched = new int[num];
			for (i = 0; i < matched.length; i++) {
				matched[i] = -1;
			}
			
			// GS stable matching
			while (!waitedSas.isEmpty()) {
				int sa = waitedSas.poll();
				int student = saPerfered[sa][flag[sa]];
				if (matched[student]==-1) {
					matched[student] = sa;
				} else {
					int prevSa = matched[student];
					if (studentPerferredRanks[student][prevSa] 
							> studentPerferredRanks[student][sa]) { 
						// student prefer this one
						matched[student] = sa;
						waitedSas.add(prevSa);
					} else {
						waitedSas.add(sa);
					}
				}
				flag[sa] += 1;
			}
			
			int[] result = new int[num];
			for (i = 0; i < result.length; i++) {
				result[matched[i]] = i; 
			}
			for (i = 0; i < result.length; i++) {
				out.print(students[result[i]]+" ");
			}
			out.println();
		}
	}

	static class InputReader {
		public BufferedReader reader;
		public StringTokenizer tokenizer;

		public InputReader(InputStream stream) {
			reader = new BufferedReader(new InputStreamReader(stream), 32768);
			tokenizer = null;
		}

		public String next() {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				try {
					tokenizer = new StringTokenizer(reader.readLine());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			return tokenizer.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public char[] nextCharArray() {
			return next().toCharArray();
		}

		public boolean hasNext() {
			try {
				String string = reader.readLine();
				if (string == null) {
					return false;
				}
				tokenizer = new StringTokenizer(string);
				return tokenizer.hasMoreTokens();
			} catch (IOException e) {
				return false;
			}
		}

		public BigInteger nextBigInteger() {
			return new BigInteger(next());
		}

		public BigDecimal nextBigDecinal() {
			return new BigDecimal(next());
		}
	}
}
