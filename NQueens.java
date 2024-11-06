public class NQueens {

    private static int N;
    private static int[][] board;

    public static void main(String[] args) {
        N = 8; // Change this value for a different size board
        board = new int[N][N];

        // Let's say the first queen is placed at (0, 0)
        board[0][0] = 1;

        if (solveNQueens(0, 1)) {
            printBoard();
        } else {
            System.out.println("No solution exists.");
        }
    }

    private static boolean solveNQueens(int row, int queensPlaced) {
        if (queensPlaced == N) {
            return true;
        }

        for (int i = 0; i < N; i++) {
            if (isSafe(row, i)) {
                board[row][i] = 1;

                if (solveNQueens(row + 1, queensPlaced + 1)) {
                    return true;
                }

                board[row][i] = 0;
            }
        }
        return false;
    }

    private static boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    private static void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}


//Output for 8 queens
// 1 0 0 0 0 0 0 0 
// 0 0 0 0 1 0 0 0 
// 0 0 0 0 0 0 0 1 
// 0 0 0 0 0 1 0 0 
// 0 0 0 1 0 0 0 0 
// 0 1 0 0 0 0 0 0 
// 0 0 0 0 0 0 1 0 
// 0 0 1 0 0 0 0 0 




//Time complexity : O(n!)
//Space Complexity : O(n^2)