package at_20240229;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_14499_주사위_굴리기 {

    private static Dice dice;
    private static int[][] map;
    private static int N, M, X, Y, K;
    // 1: 동, 2: 서,3: 북,4: 남
    private static int[] dy = { 0, 0, 0, -1, 1 };
    private static int[] dx = { 0, 1, -1, 0, 0 };
    private static StringBuilder sb;

    public void main() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 지도의 세로 크기
        M = Integer.parseInt(st.nextToken()); // 지도의 가로 크기
        X = Integer.parseInt(st.nextToken()); // 북쪽으로부터 떨어진 칸의 개수(주사위)
        Y = Integer.parseInt(st.nextToken()); // 서쪽으로부터 떨어진 칸의 개수
        K = Integer.parseInt(st.nextToken()); // 명령의 개수

        map = new int[N][M];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        List<Integer> directions = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens())
            directions.add(Integer.parseInt(st.nextToken()));

        // End of input
        
        // init dice
        dice = new Dice(X, Y);

        // roll dice
        for (var dir : directions) {
            move(dir);
        }

        System.out.println(sb.toString());
        br.close();
        bw.flush();
        bw.close();

    }

    /**
     * map validation
     * 
     * @param row
     * @param col
     * @return
     */
    private static boolean isValid(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= M)
            return false;

        return true;
    }

    /**
     * <pre>
     * 이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다.
     * </pre>
     * 
     * 0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.
     * 
     * @param row
     * @param col
     */
    private static void copy(int row, int col) {
        if (map[row][col] == 0) {
            map[row][col] = dice.getButtom();
        } else {
            dice.coverButtom(map[row][col]);
            map[row][col] = 0;
        }
    }

    /**
     * 1. map valid check
     * 2. roll dice
     * 3. cover buttom
     * 4. relocate dice
     * 5. append value
     * 
     * @param dir
     */
    private static void move(int dir) {
        int nextRow = dice.getRow() + dy[dir];
        int nextCol = dice.getCol() + dx[dir];

        if (!isValid(nextRow, nextCol))
            return;

        switch (dir) {
            case 1:
                dice.rollEast();
                break;
            case 2:
                dice.rollWest();
                break;
            case 3:
                dice.rollNorth();
                break;
            case 4:
                dice.rollSouth();
                break;

            default:
                break;
        }

        copy(nextRow, nextCol);
        dice.relocate(nextRow, nextCol);
        sb.append(dice.getTop());
        sb.append("\n");
    }

    /**
     *         2(north)
     * 4(west) 1(top) 3(east)
    *          5(south)
     *         6(buttom)
     */
    static class Dice {
        private int row;
        private int col;
        private int top = 0;
        private int buttom = 0;
        private int north = 0;
        private int west = 0;
        private int east = 0;
        private int south = 0;

        public Dice(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return this.row;
        }

        public int getCol() {
            return this.col;
        }

        public int getTop() {
            return this.top;
        }

        public int getButtom() {
            return this.buttom;
        }

        /**
         * 주사위 위치를 수정한다.
         * 
         * @param row
         * @param col
         */
        public void relocate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        /**
         * 주사위의 하단을 복사한다.
         * 
         * @param num
         */
        public void coverButtom(int num) {
            this.buttom = num;
        }

        /**
         * roll dice
         * 다이스를 굴린다
         */
        public void rollEast() {
            int temp = top;
            this.top = this.west;
            this.west = this.buttom;
            this.buttom = this.east;
            this.east = temp;
        }

        public void rollWest() {
            int temp = top;
            this.top = this.east;
            this.east = this.buttom;
            this.buttom = this.west;
            this.west = temp;
        }

        public void rollNorth() {
            int temp = top;
            this.top = south;
            this.south = this.buttom;
            this.buttom = this.north;
            this.north = temp;
        }

        public void rollSouth() {
            int temp = top;
            this.top = north;
            this.north = this.buttom;
            this.buttom = this.south;
            this.south = temp;
        }

    }
}
