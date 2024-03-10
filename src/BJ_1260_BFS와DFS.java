import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1260_BFS와DFS {

    public void main() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 정점의 개수
        int M = Integer.parseInt(st.nextToken()); // 간선의 개수
        int V = Integer.parseInt(st.nextToken()); // 탐색 시작 정점 번호

        var map = new boolean[N + 1][N + 1];
        int row = 0, col = 0;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            row = Integer.parseInt(st.nextToken());
            col = Integer.parseInt(st.nextToken());
            map[row][col] = true;
            map[col][row] = true;
        }

        // DFS
        var visit = new boolean[N + 1];
        var dfsSB = new StringBuilder();
        dfs(map, visit, V, dfsSB);

        // BFS
        Queue<Integer> q = new LinkedList<Integer>();
        visit = new boolean[N + 1];
        var bfsSB = new StringBuilder();
        bfs(map, visit, V, bfsSB, q);

        System.out.println(dfsSB);
        System.out.println(bfsSB);
    }

    public static StringBuilder bfs(boolean[][] map, boolean[] visit, int v, StringBuilder sb, Queue<Integer> q) {
        q.offer(v);
        visit[v] = true;

        while (!q.isEmpty()) {
            v = q.poll();
            sb.append(v + " ");
            for (int i = 1; i < map.length; i++) {
                if (map[v][i] && !visit[i]) {
                    q.offer(i);
                    visit[i] = true;
                }
            }
        }

        return sb;
    }

    public static StringBuilder dfs(boolean[][] map, boolean[] visit, int v, StringBuilder sb) {
        if (visit[v])
            return sb;

        visit[v] = true;
        sb.append(v + " ");
        for (int i = 1; i < map.length; i++) {
            if (map[v][i]) {
                dfs(map, visit, i, sb);
            }
        }
        return dfs(map, visit, v, sb);
    }
}
