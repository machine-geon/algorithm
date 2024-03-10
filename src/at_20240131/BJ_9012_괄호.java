package at_20240131;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 풀이
 * 1. '(' 개수가 ')' 보다 항상 크거나 같아야 한다.
 * 2. 개수 검사를 끝낸 뒤, '(' 개수와 ')' 개수가 같아야 한다.
 * 
 * @throws Exception
 */
public class BJ_9012_괄호 {
    public static final Character LEFT = '(';
    public static final Character RIGHT = ')';

    public void main() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        var cnt = Integer.parseInt(br.readLine());
        int leftCnt = 0, rightCnt = 0;
        boolean isVPS = true;

        String line = "";
        while (cnt-- > 0) {
            // init
            line = br.readLine();
            isVPS = true;
            leftCnt = 0;
            rightCnt = 0;

            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == LEFT) {
                    leftCnt++;
                } else {
                    rightCnt++;
                }
                if (rightCnt > leftCnt) {
                    isVPS = false;
                    break;
                }
            }

            if (leftCnt != rightCnt) {
                isVPS = false;
            }
            System.out.println(isVPS ? "YES" : "NO");
        }
    }
}
