import java.util.Scanner;

public class Solution {

    public static class Problem {
        private final int cellsToPrepare;
        private final Scanner in;

        Problem(int cellsToPrepare, Scanner in) {

            this.cellsToPrepare = cellsToPrepare;
            this.in = in;
        }

        void solve() {
            while (true) {
                outputMove();

                int r = in.nextInt();
                int c = in.nextInt();

                if (r == -1 && c == -1) {
                    System.exit(1);
                } else if (r == 0 && c == 0) {
                    return;
                }

                // TODO update map with r and c

                outputMove();
            }
        }

        private void outputMove() {
            // get next move
            // output move, 2 int, between 2 and 999
            int i = 2; // row
            int j = 2; // column
            System.out.println(i + " " + j);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // read inputs
        int numOfCases = in.nextInt();
        in.nextLine(); // skip to next line
        for (int cn = 1; cn <= numOfCases; cn++) {
            int cellsToPrepare = in.nextInt(); // required rectangle

            Problem problem = new Problem(cellsToPrepare, in);
            problem.solve();
        }
    }
}
