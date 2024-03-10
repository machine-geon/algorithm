package at_20240131;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2606_바이러스 {
    public void main() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        var DeskTopcnt = Integer.parseInt(br.readLine());
        var Connectcnt = Integer.parseInt(br.readLine());

        var map = new boolean[DeskTopcnt + 1][DeskTopcnt + 1];
        var visit = new boolean[DeskTopcnt + 1];
        var virusCnt = 0;

        // 1. Map을 이용하여 연결된 컴퓨터를 저장한다.
        while (Connectcnt-- > 0) {
            st = new StringTokenizer(br.readLine());
            var x = Integer.parseInt(st.nextToken());
            var y = Integer.parseInt(st.nextToken());
            map[x][y] = true;
            map[y][x] = true;
        }

        // 2. 1번 컴퓨터를 시작으로 연결된 컴퓨터를 찾아간다.
        visit[0] = true;
        visit[1] = true;
        for (int idx = 2; idx <= DeskTopcnt; idx++) {
            if (map[1][idx]) {
                virusCnt = dfs(map, visit, idx, virusCnt);
            }
        }

        System.out.println(virusCnt);
    }

    private int dfs(boolean[][] map, boolean[] visit, int idx, int virusCnt) {
        // 1. 방문한 컴퓨터 관리
        if (visit[idx])
            return virusCnt;

        // 2. 연결된 컴퓨터를 찾아간다.
        virusCnt += 1;
        visit[idx] = true;
        for (int i = 1; i < map.length; i++) {
            if (map[idx][i]) {
                virusCnt = dfs(map, visit, i, virusCnt);
            }
        }
        return virusCnt;
    }
}
