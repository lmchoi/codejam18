import java.util.*;

public class Solution {

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

        private static final int EXPECTED_R200 = 10;
        private static final int EXPECTED_C200 = 20;
        private final int expectedR;
        private final int expectedC;

        private Cell corner = new Cell(-1, -1);
        private Cell target = new Cell(-1, -1);
        private final Map<Integer, BitSet> cells;

        Problem(int cellsToPrepare, Scanner in) {
            this.in = in;

            System.err.println("c: " + cellsToPrepare);

            if (cellsToPrepare == 20) {
                expectedR = EXPECTED_R20;
                expectedC = EXPECTED_C20;
            } else if (cellsToPrepare == 200){
                expectedR = EXPECTED_R200;
                expectedC = EXPECTED_C200;
            } else {
                expectedR = 3;
                expectedC = 4;
            }

            cells = new HashMap<>();
        }

        void solve() {
            // initial move
            Cell move = new Cell(2, 2);
            outputMove(move);
            Cell result = readOutcome();
            if (isFinish(result)) {
                return;
            }

            init(result);

            do {
                updateCells(result);
                move = nextMove();
                outputMove(move);
                result = readOutcome();
            } while (!isFinish(result));
        }

        private void init(Cell result) {
            corner.c = result.c;
            corner.r = result.r;

            target.c = result.c + 1;
            target.r = result.r + 1;
        }

        private void updateCells(Cell result) {
            BitSet cols = cells.get(result.r);
            if (cols == null) {
                cells.put(result.r, cols = new BitSet());
                cols.set(result.c);
            } else {
                cols.set(result.c);
            }
        }

        private Cell nextMove() {
            while (rowComplete(target.r - 1)) {
                target.r++;
                target.c = corner.c + 1;
            }

            // if cell above target is set move to next
            while (isSet(target.r - 1, target.c - 1) && // top left
                    isSet(target.r - 1, target.c) && // top mid
                    isSet(target.r, target.c - 1) && // left
                    isSet(target.r + 1, target.c - 1) && // bottom left
                    target.c < (corner.c + expectedC - 2)) {
                target.c++;
            }

            return target;
        }

        private boolean rowComplete(int r) {
            BitSet row = cells.get(r);
            return row != null && row.cardinality() == expectedC;
        }

        private boolean isSet(int r, int c) {
            BitSet cols = cells.get(r);
            return cols != null && cols.get(c);
        }

        private void outputMove(Cell move) {
            System.out.println(move.r + " " + move.c);
        }

        private Cell readOutcome() {
            int r = in.nextInt();
            int c = in.nextInt();
            return new Cell(r, c);
        }

        private boolean isFinish(Cell move) {
            if (move.r == -1 && move.c == -1) {
                printCells();
                System.exit(1);
            } else if (move.r == 0 && move.c == 0) {
                printCells();
                return true;
            }
            return false;
        }

        private void printCells() {
            for (Map.Entry<Integer,BitSet> entry : cells.entrySet()) {
                BitSet cols = entry.getValue();
                System.err.println(entry.getKey() + " : " + cols.toString());
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
