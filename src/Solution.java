import java.util.Scanner;

public class Solution {

    public static class Problem {
        Scanner in = new Scanner(System.in);

        public void solve() {
            // read inputs
            int numOfCases = in.nextInt();
            in.nextLine(); // skip to next line
            for (int cn = 1; cn <= numOfCases; cn++) {

                // output
                int ret = 0;
                System.out.println("Case #" + cn + ": " + ret);
            }

        }
    }

    public static void main(String[] args) {
        Problem problem = new Problem();
        problem.solve();
    }
}
