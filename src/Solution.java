import java.util.Scanner;

public class Solution {

    public static class Problem {
        Scanner in = new Scanner(System.in);

        void solve() {
            // read inputs
            int numOfCases = in.nextInt();
            in.nextLine(); // skip to next line
            for (int cn = 1; cn <= numOfCases; cn++) {

                int shield = in.nextInt(); // shield
                String p = in.next();
                char[] ps = p.toCharArray();
                int numOfInstructions = ps.length;
                int[] currentShield = new int[numOfInstructions];
                int[] currentCharge = new int[numOfInstructions];

                int charge = 1;
                int numOfShots = 0;
                for (int i = 0; i < numOfInstructions; i++) {
                    if (ps[i] == 'C') {
                        charge = charge * 2;
                    } else if (ps[i] == 'S') {
                        shield -= charge;
                        numOfShots++;
                    }

                    currentShield[i] = shield;
                    currentCharge[i] = charge;
                }

                int result = -1;
                if (numOfShots > shield) {
                    // impossible
                } else if (numOfShots == 0) {
                    result = 0;
                }

                System.out.println("Case #" + cn + ": " + (result == -1 ? "IMPOSSIBLE" : result) + " ");
            }

        }
    }

    public static void main(String[] args) {
        Problem problem = new Problem();
        problem.solve();
    }
}
