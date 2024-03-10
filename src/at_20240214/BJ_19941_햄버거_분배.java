package at_20240214;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_19941_햄버거_분배 {

    public void main() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String str = br.readLine();
        var charArr = str.toCharArray();
        boolean [] checkPerson = new boolean [str.length()];
        int result = 0;

        for (int charIdx = 0; charIdx < charArr.length; charIdx++) {
            if (charArr[charIdx] == 'H') {
                for (int innerIdx = charIdx - K; innerIdx <= charIdx + K; innerIdx++) {
                    if (innerIdx < 0 || innerIdx >= charArr.length) {
                        continue;
                    }
                    if (charArr[innerIdx] == 'P' && !checkPerson[innerIdx]) {
                        checkPerson[innerIdx] = true;
                        result++;
                        break;
                    }
                }
            }
        }

        System.out.println(result);
    }
}
