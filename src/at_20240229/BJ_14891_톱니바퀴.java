package at_20240229;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_14891_톱니바퀴 {
    private static HashMap<Integer, Gear> gears = new HashMap<>();

    public void main() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        gears.put(1, new Gear(1, br.readLine().toCharArray()));
        gears.put(2, new Gear(2, br.readLine().toCharArray()));
        gears.put(3, new Gear(3, br.readLine().toCharArray()));
        gears.put(4, new Gear(4, br.readLine().toCharArray()));

        int K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<Command> cmds = new ArrayList<>();
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            cmds.add(new Command(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        // End of input

        for (var cmd : cmds) {
            if (cmd.getId() == 1) {
                compareGear(gears.get(cmd.getId()), null, gears.get(cmd.getId() + 1), cmd.getDir());
            } else if (cmd.getId() == 4) {
                compareGear(gears.get(cmd.getId()), gears.get(cmd.getId() - 1), null, cmd.getDir());
            } else {
                compareGear(gears.get(cmd.getId()), gears.get(cmd.getId() - 1), gears.get(cmd.getId() + 1),cmd.getDir());
            }
        }

        int result = 0;
        for (var gear : gears.values()) {
            result += gear.getScore();
        }

        System.out.println(result);
    }

    /**
     * <pre>
     * 톱니바퀴 A를 회전할 때, 그 옆에 있는 톱니바퀴 B와 서로 맞닿은 톱니의 극이 다르다면,
     * B는 A가 회전한 방향과 반대방향으로 회전하게 된다.
     * </pre>
     * 
     * @param targetGear
     * @param leftGear
     * @param rightGear
     * @param dir
     */
    private static void compareGear(Gear targetGear, Gear leftGear, Gear rightGear, int dir) {
        if (leftGear != null) {
            if (leftGear.getRight() != targetGear.getLeft()) {
                if (targetGear.getId() > 1) {
                    compareGear(leftGear, gears.get(leftGear.getId() - 1), null, convertDir(dir));
                }
            }
        }

        if (rightGear != null) {
            if (targetGear.getRight() != rightGear.getLeft()) {
                if (targetGear.getId() < 4) {
                    compareGear(rightGear, null, gears.get(rightGear.getId() + 1), convertDir(dir));
                }
            }
        }

        targetGear.turn(dir);
    }

    private static int convertDir(int dir) {
        if (dir == 1)
            return -1;
        else
            return 1;
    }

    static class Command {
        private int id; // 대상 톱니 바퀴 ID
        private int dir; // 1: 시계 방향, 2: 반시계 방향

        public Command(int id, int dir) {
            this.id = id;
            this.dir = dir;
        }

        public int getId() {
            return this.id;
        }

        public int getDir() {
            return this.dir;
        }
    }

    static class Gear {
        private int id; // 톱니 바퀴 ID
        private int topIdx; // 12시 Idx
        private char[] SNList; // N극: 0, S극: 1

        public Gear(int id, char[] SNList) {
            this.id = id;
            this.topIdx = 0;
            this.SNList = SNList;
        }

        public int getId() {
            return this.id;
        }

        // 오른쪽 톱니 값 조회
        public Character getRight() {
            if (topIdx < 6)
                return SNList[topIdx + 2];
            else
                return SNList[topIdx - 6];

        }

        // 왼쪽 톱니 값 조회
        public Character getLeft() {
            if (topIdx < 2)
                return SNList[topIdx + 6];
            else
                return SNList[topIdx - 2];

        }

        // 회전
        public void turn(int dir) {
            // 시계 방향
            if (dir == 1) {
                if (this.topIdx == 0)
                    this.topIdx = 7;
                else
                    this.topIdx -= 1;
            }
            // 반시계 방향
            else if (dir == -1) {
                if (this.topIdx == 7)
                    this.topIdx = 0;
                else
                    this.topIdx += 1;
            }
        }

        /**
         * 1번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 1점
         * 2번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 2점
         * 3번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 4점
         * 4번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 8점
         */
        public int getScore() {
            switch (this.id) {
                case 1:
                    if (SNList[topIdx] == '0')
                        return 0;
                    return 1;
                case 2:
                    if (SNList[topIdx] == '0')
                        return 0;
                    return 2;
                case 3:
                    if (SNList[topIdx] == '0')
                        return 0;
                    return 4;
                case 4:
                    if (SNList[topIdx] == '0')
                        return 0;
                    return 8;
                default:
                    break;
            }
            return 0;
        }
    }
}
