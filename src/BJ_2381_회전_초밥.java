import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class BJ_2381_회전_초밥 {
    public void main() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        List<Integer> sushi = new ArrayList<>();
        for (int idx = 0; idx < N; idx++) {
            sushi.add(Integer.parseInt(br.readLine()));
        }

        Set<Integer> windowSet = new HashSet<>();
        int cnt = 0;
        int start = 0;
        int end = k;
        while (start < N) {
            windowSet.clear();
            windowSet.add(c);

            if (end < start) {
                for (int idx = start; idx < N; idx++) {
                    windowSet.add(sushi.get(idx));
                }
                for (int idx = 0; idx < end; idx++) {
                    windowSet.add(sushi.get(idx));
                }
            } else {
                for (int idx = start; idx < end; idx++) {
                    windowSet.add(sushi.get(idx));
                }
            }

            cnt = Math.max(cnt, windowSet.size());
            start++;

            if (end == N) {
                end = 1;
            } else {
                end++;
            }
        }

        System.out.println(cnt);
    }
}
