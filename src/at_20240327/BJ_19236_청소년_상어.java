package at_20240327;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_19236_청소년_상어 {

    // 1부터 순서대로 ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
    static int max = 0;

    public void main() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Fish[][] map = new Fish[4][4];
        for (var r = 0; r < 4; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (var c = 0; c < 4; c++) {
                map[r][c] = new Fish(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
        }

        int startScore = map[0][0].getId();
        map[0][0] = null;
        start(map , startScore);

        br.close();
        bw.flush();
        bw.close();
    }

    static void start(Fish[][]map, int score) {
        
    }

    static Fish[][] moveFish(Fish[][] map) {
        int nextRow, nextCol;
        Fish tempFish;
        for (var r = 0; r < 4; r++) {
            for (var c = 0; c < 4; c++) {
                if (map[r][c] != null) {
                    nextRow = r + dy[map[r][c].getDir()];
                    nextCol = c + dx[map[r][c].getDir()];
                    if (isValid(nextRow, nextCol)) {
                        if (map[nextRow][nextCol] != null) {
                            tempFish = map[nextRow][nextCol];
                            map[r][c] = map[nextRow][nextCol];
                            map[nextRow][nextCol] = tempFish;
                        } else {
                            map[nextRow][nextCol] = map[r][c];
                            map[r][c] = null;
                        }
                    }
                }
            }
        }
        return map;
    }

    static boolean isValid(int nextRow, int nextCol) {
        if (nextRow < 0 || nextRow >= 4 || nextCol < 0 || nextCol >= 4) {
            return false;
        }
        return true;
    }

    static class Fish {
        private int id;
        private int dir;

        public Fish(int id, int dir) {
            this.id = id;
            this.dir = dir;
        }

        public int getId() {
            return id;
        }

        public int getDir() {
            return dir;
        }

    }
}
