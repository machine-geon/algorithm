import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  첫 줄에 물품의 수 N(1 ≤ N ≤ 100)과 준서가 버틸 수 있는 무게 K(1 ≤ K ≤ 100,000)가 주어진다. 
 *  두 번째 줄부터 N개의 줄에 거쳐 각 물건의 무게 W(1 ≤ W ≤ 100,000)와 해당 물건의 가치 V(0 ≤ V ≤ 1,000)가 주어진다.
 */
public class BJ12865_평범한배낭 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 물품의 수
		int K = Integer.parseInt(st.nextToken()); // 무게

		int[] W = new int[N + 1]; // 물건의 무게
		int[] V = new int[N + 1]; // 물건의 가치

		int[][] dp = new int[N + 1][K + 1];

		for (int idx = 1; idx <= N; idx++) {
			st = new StringTokenizer(br.readLine());
			W[idx] = Integer.parseInt(st.nextToken());
			V[idx] = Integer.parseInt(st.nextToken());
		} // End of Input

		for (int idx = 1; idx <= N; idx++) { // 물건 idx
			for (int weight = 1; weight <= K; weight++) { // 무게
				// weight를 담을 수 없는 경우
				if (W[idx] > weight) {
					dp[idx][weight] = dp[idx - 1][weight];
				}
				// weight를 담을 수 있는 경우
				else {
					dp[idx][weight] = Math.max(dp[idx - 1][weight], dp[idx - 1][weight - W[idx]] + V[idx]);
				}
			}
		}

		System.out.println(dp[N][K]);
	}
}