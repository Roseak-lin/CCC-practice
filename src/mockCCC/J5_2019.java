package mockCCC;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class J5_2019 {

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		int n = in.nextInt(), k = in.nextInt();
		int arr[] = new int[n];
		int fish[] = new int[n + 1];
		for (int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
			fish[arr[i]]++;
		}
		in.close();
//		for (int i = 1; i < fish.length; i++) {
//			System.out.print(fish[i] + " ");
//		}
		long dp[][] = new long[n + 1][k + 1];
		dp[0][0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= k; j++) {
				if (j == 0 || j == 1) {
					dp[i][j] = dp[i - 1][j] + fish[i];
				} else {
					dp[i][j] = (dp[i - 1][j] + (dp[i - 1][j - 1] * fish[i])) % 998244353;
				}
			}
		}
		System.out.println(dp[n][k]);
		
	}

	static class Reader {
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public Reader() {
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public Reader(String file_name) throws IOException {
			din = new DataInputStream(new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException {
			byte[] buf = new byte[100000];
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n')
					break;
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
		}

		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}

		public long nextLong() throws IOException {
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public double nextDouble() throws IOException {
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();

			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (c == '.') {
				while ((c = read()) >= '0' && c <= '9') {
					ret += (c - '0') / (div *= 10);
				}
			}

			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException {
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() throws IOException {
			if (din == null)
				return;
			din.close();
		}
	}
}
