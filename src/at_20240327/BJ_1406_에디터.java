package at_20240327;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_1406_에디터 {
    public void main() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        List<Character> charList = new LinkedList<>();
        var iter = charList.listIterator();
        var charArr = br.readLine().toCharArray();
        for (var c : charArr)
            iter.add(c);

        StringTokenizer st = null;
        List<Order> orders = new ArrayList<>();
        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            if (st.countTokens() == 1) {
                orders.add(new Order(st.nextToken().charAt(0)));
            } else {
                orders.add(new Order(st.nextToken().charAt(0), st.nextToken().charAt(0)));
            }
        }

        for (var order : orders) {
            switch (order.getCmd()) {
                case 'L':
                    if (iter.hasPrevious())
                        iter.previous();
                    break;
                case 'D':
                    if (iter.hasNext())
                        iter.next();
                    break;
                case 'B':
                    if (iter.hasPrevious()) {
                        iter.previous();
                        iter.remove();
                    }
                    break;
                case 'P':
                    iter.add(order.getInsertChar());
                    break;

                default:
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (var c : charList) {
            sb.append(c);
        }

        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    static class Order {
        private char cmd;
        private char insertChar;

        public Order(char cmd) {
            this.cmd = cmd;
        }

        public Order(char cmd, char insertChar) {
            this.cmd = cmd;
            this.insertChar = insertChar;
        }

        public char getCmd() {
            return cmd;
        }

        public char getInsertChar() {
            return insertChar;
        }

    }
}
