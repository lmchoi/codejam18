public class MatrixUtils {
    private static char[][] rotateMatrix90(char[][] board) {
        int n = board.length;
        char[][] boardR = new char[n][n];
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                boardR[n - y - 1][x] = board[n - x - 1][n - y - 1];
            }
        }

        return boardR;
    }

    public static void printMatrix(char[][] board) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j]);
            }

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
