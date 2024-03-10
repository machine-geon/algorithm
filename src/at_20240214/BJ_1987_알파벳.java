package at_20240214;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class BJ_1987_알파벳 {
    public static Character[][] map;
    public static List<Character> visited = new ArrayList<>();
    public static int[] dy = { -1, 1, 0, 0 };
    public static int[] dx = { 0, 0, -1, 1 };
    public static int R, C, result;

    public void main() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new Character[R][C];
        for (int row = 0; row < R; row++) {
            String str = br.readLine();
            for (int col = 0; col < C; col++) {
                map[row][col] = str.charAt(col);
            }
        }

        visited.add(map[0][0]);
        result = 0;
        dfs(0, 0, 1);

        System.out.println(result);
    }

    public static int dfs(int row, int col, int cnt) {

        for (int idx = 0; idx < 4; idx++) {
            int nextRow = row + dy[idx];
            int nextCol = col + dx[idx];

            if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C) {
                continue;
            }

            if (visited.contains(map[nextRow][nextCol])) {
                continue;
            }

            visited.add(map[nextRow][nextCol]);
            dfs(nextRow, nextCol, cnt + 1);
            visited.remove(map[nextRow][nextCol]);
        }

        result = Math.max(result, cnt);
        return cnt;
    }
}
