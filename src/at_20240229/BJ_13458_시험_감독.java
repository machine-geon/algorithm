package at_20240229;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_13458_시험_감독 {
    public void main() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Integer> candidates = new ArrayList<>();
        while (st.hasMoreTokens()) {
            candidates.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken()); // 총 감독관 감시 가능수
        int C = Integer.parseInt(st.nextToken()); // 부감독관 감시 가능수

        long result = 0;
        for (var candidate : candidates) {
            candidate -= B;
            result += 1;

            if (candidate < 0) continue;

            result += (candidate / C);
            if (candidate % C != 0) result += 1;
        }

        System.out.println(result);
        br.close();
    }
}
