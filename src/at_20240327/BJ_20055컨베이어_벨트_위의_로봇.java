package at_20240327;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ_20055컨베이어_벨트_위의_로봇 {
    static int N, K, step = 0;
    static LinkedList<ConveyorUnit> conveyor;

    public void main() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        conveyor = new LinkedList<ConveyorUnit>();
        for (int idx = 0; idx < N * 2; idx++) {
            conveyor.add(new ConveyorUnit(Integer.parseInt(st.nextToken())));
        }

        // 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다.
        while (K > 0) {
            // 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
            moveConveyor();
            // 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다.
            moveRobot();
        }

        bw.write(String.valueOf(step));
        br.close();
        bw.flush();
        bw.close();
    }

    // 컨베이어 벨트 이동
    static void moveConveyor() {
        step++;
        conveyor.add(0, conveyor.removeLast());
        // 내리는 곳에 로봇이 있다면 로봇 제거
        if (conveyor.get(N - 1).robot)
            conveyor.get(N - 1).removeRobot();
    }

    // 로봇이동
    static void moveRobot() {

        // 가장 먼저 벨트에 올라간 로봇부터
        for (int i = N - 1; i > 0; i--) {

            // 벨트위에 로봇 없으면 패스
            if (!conveyor.get(i).robot)
                continue;

            // 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
            if (conveyor.get(i + 1).robot == true || conveyor.get(i + 1).durability <= 0)
                continue;

            conveyor.get(i).removeRobot();
            conveyor.get(i + 1).putRobot();
            if (conveyor.get(i + 1).durability <= 0)
                K--;

            // 로봇이 내리는 위치에 도달하면 그 즉시 내린다
            // 내리는 위치로 로봇 이동했다면 로봇 내리기
            if (i + 1 == N - 1)
                conveyor.get(i + 1).removeRobot();
        }

        // 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
        if (conveyor.get(0).durability > 0) {
            conveyor.get(0).putRobot();
            if (conveyor.get(0).durability <= 0)
                K--;
        }

    }

    static class ConveyorUnit {
        private int durability;
        private boolean robot;

        public ConveyorUnit(int durability) {
            this.durability = durability;
        }

        public void putRobot() {
            robot = true;
            durability--;
        }

        public void removeRobot() {
            robot = false;
        }
    }
}
