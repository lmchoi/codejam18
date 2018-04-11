import java.math.BigInteger;
import java.util.*;

public class Solution {

    static class Problem {
        Scanner in = new Scanner(System.in);

        void solve() {
            // read inputs
            int numOfCases = in.nextInt();
            in.nextLine(); // skip to next line
            for (int cn = 1; cn <= numOfCases; cn++) {

                BigInteger shield = in.nextBigInteger();
                String p = in.next();

                int result = solve(shield, p);

                System.out.println("Case #" + cn + ": " + (result == -1 ? "IMPOSSIBLE" : result));
            }
        }

        int solve(BigInteger shield, String p) {
            char[] ps = p.toCharArray();
            List<Shot> shots = new LinkedList<>();

            BigInteger currentDmg = BigInteger.ZERO;
            BigInteger currentCharge = BigInteger.ONE;
            int chargeInFront = 0;

            for (int i = 0; i < ps.length; i++) {
                if (ps[i] == 'S') {
                    shots.add(new Shot(i, chargeInFront, currentCharge));
                    chargeInFront = 0;

                    currentDmg = currentDmg.add(currentCharge);
                } else {
                    chargeInFront++;

                    currentCharge = currentCharge.shiftLeft(1);
                }
            }

            int numOfShots = shots.size();
            if (BigInteger.valueOf(numOfShots).compareTo(shield) > 0) {
                return -1;
            }

            int result = 0;
            BigInteger excessDamage = currentDmg.add(shield.negate());

            if (excessDamage.signum() > 0) {
                int i = shots.size() - 1;

                while (i >= 0) {
                    Shot shot = shots.get(i);
                    if (shot.pos > 0) {
                        if (shot.chargeInFront > 0) {
                            shot.pos--;
                            shot.chargeInFront--;
                            shot.currentCharge = shot.currentCharge.shiftRight(1);

                            result++;
                            BigInteger dmg = shot.currentCharge;
                            excessDamage = excessDamage.add(dmg.negate());

                            if (excessDamage.signum() < 1) {
                                return result;
                            } else {
                                int ni = i + 1;
                                if (ni < shots.size()) {
                                    Shot nextShot = shots.get(ni);
                                    nextShot.chargeInFront++;
                                    i = ni;
                                }
                            }
                        } else {
                            i--;
                        }
                    } else {
                        return -1;
                    }
                }
            }

            return result;
        }

        private class Shot {
            private int pos;
            private int chargeInFront;
            private BigInteger currentCharge;

            Shot(int pos, int chargeInFront, BigInteger currentCharge) {
                this.pos = pos;
                this.chargeInFront = chargeInFront;
                this.currentCharge = currentCharge;
            }
        }
    }

    public static void main(String[] args) {
        Problem problem = new Problem();
        problem.solve();
    }
}
