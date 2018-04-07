import java.util.*;

public class Solution {
    static final short TOP_LEFT = 1;
    static final short TOP = 2;
    static final short TOP_RIGHT = 4;
    static final short LEFT = 8;
    static final short RIGHT = 16;
    static final short BOTTOM_LEFT = 32;
    static final short BOTTOM = 64;
    static final short BOTTOM_RIGHT = 128;

    static class Patch {
        short friends = 0;
        short friendsCount = 0;

        boolean set(short c) {
            int exists = c & friends;
            friends |= c;

            if (exists == 0) {
                friendsCount++;
                return true;
            }

            return false;
        }
    }

    static class Cell {
        int r, c;

        Cell(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static class Problem {
        private final Scanner in;
        private static final int EXPECTED_R20 = 4;
        private static final int EXPECTED_C20 = 5;

        private static final int EXPECTED_R200 = 20;
        private static final int EXPECTED_C200 = 10;

        private Cell corner = new Cell(1, 1); // raw value

        private final Patch[][] soil;

        private short leastFriends = 1;

        Problem(int cellsToPrepare, Scanner in) {
            this.in = in;

            // System.err.println("c: " + cellsToPrepare);

            int expectedR;
            int expectedC;

            if (cellsToPrepare == 20) {
                expectedR = EXPECTED_R20;
                expectedC = EXPECTED_C20;
            } else if (cellsToPrepare == 200) {
                expectedR = EXPECTED_R200;
                expectedC = EXPECTED_C200;
            } else {
                expectedR = 10;
                expectedC = 10;
            }

            soil = new Patch[expectedR - 2][expectedC - 2];
            for (int i = 0; i < soil.length; i++) {
                for (int j = 0; j < soil[0].length; j++) {
                    soil[i][j] = new Patch();
                }
            }
        }

        void solve() {
            // set corners
            Optional<Cell> res = init();
            if (!res.isPresent()) {
                return;
            }

            // find patch with least friends
            while (true) {
                boolean updated = false;

                for (int i = 0; i < soil.length; i++) {
                    for (int j = 0; j < soil[i].length; j++) {
                        if (soil[i][j].friendsCount < leastFriends) {
                            Optional<Cell> result = sendRequest(i, j);
                            if (result.isPresent()) {
                                updated = updateFriends(result.get());
                            } else {
                                return;
                            }
                        }
                    }
                }

//                System.err.println(leastFriends);
                if (!updated) {
                    if (leastFriends < 8) {
                        leastFriends++;
                    }
                }
                // System.err.println("required friends " + leastFriends);
            }
        }

        private boolean updateFriends(Cell cell) {
            int nr = cell.r;
            int nc = cell.c;

            // System.err.println("update: " + nr + " " + nc);

            boolean updated = false;

            // update left and right
            if (nr > 0 && nr < soil.length) {
                if (nc > -1 && nc < (soil[0].length - 1)) {
                    updated = soil[nr][nc + 1].set(LEFT) || updated;
                }

                if (nc > 0 && nc < soil[0].length) {
                    updated = soil[nr][nc - 1].set(RIGHT) || updated;
                }
            }

            // update below
            if (nr > -1 && nr < (soil.length - 1)) {
                if (nc > 0 && nc < soil[0].length) {
                    updated = soil[nr + 1][nc].set(TOP) || updated;
                }

                if (nc > -1 && nc < (soil[0].length - 1)) {
                    updated = soil[nr + 1][nc + 1].set(TOP_LEFT) || updated;
                }

                if (nc > 0 && nc < soil[0].length) {
                    updated = soil[nr + 1][nc - 1].set(TOP_RIGHT) || updated;
                }
            }

            // update below
            if (nr > 0 && nr < soil.length) {
                if (nc > 0 && nc < soil[0].length) {
                    updated = soil[nr - 1][nc].set(BOTTOM) || updated;
                }

                if (nc > -1 && nc < (soil[0].length - 1)) {
                    updated = soil[nr - 1][nc + 1].set(BOTTOM_LEFT) || updated;
                }

                if (nc > 0 && nc < soil[0].length) {
                    updated = soil[nr - 1][nc - 1].set(BOTTOM_RIGHT) || updated;
                }
            }

            return updated;
        }

        private Optional<Cell> init() {
            Optional<Cell> result = sendRawRequest(2, 2);

            if (result.isPresent()) {
                Cell cell = result.get();
                corner.r = cell.r;
                corner.c = cell.c;
                soil[0][0].set(TOP_LEFT);
            }

            return result;
        }

        private Optional<Cell> sendRawRequest(int r, int c) {
            // System.err.println("Request: " + r + " " + c);
            System.out.println(r + " " + c);

            int gr = in.nextInt();
            int gc = in.nextInt();

            // System.err.println("Response: " + gr + " " + gc);
            // System.err.println("Raw Corner: " + gr + " " + gc);

            if (gr == -1 && gc == -1) {
                System.exit(1);
            } else if (gr == 0 && gc == 0) {
                return Optional.empty();
            }
            return Optional.of(new Cell(gr, gc));
        }

        private Optional<Cell> sendRequest(int r, int c) {
            int nr = r + corner.r + 1;
            int nc = c + corner.c + 1;
            // System.err.println("xRequest: " + r + " " + c);
            // System.err.println("Request: " + nr + " " + nc);
            System.out.println(nr + " " + nc);

            int gr = in.nextInt();
            int gc = in.nextInt();
            int xr = gr - corner.r - 1;
            int xc = gc - corner.c - 1;

            // System.err.println("Response: " + gr + " " + gc);
            // System.err.println("xResponse: " + xr + " " + xc);

            if (gr == -1 && gc == -1) {
//                printSoil();

                System.exit(1);
            } else if (gr == 0 && gc == 0) {
//                printSoil();

                return Optional.empty();
            }
            return Optional.of(new Cell(xr, xc));
        }

        private void printSoil() {
            for (int i = 0; i < soil.length; i++) {
                for (int j = 0; j < soil[0].length; j++) {
                    System.err.print(soil[i][j].friends + " ");
                }
                System.err.println();
            }
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
