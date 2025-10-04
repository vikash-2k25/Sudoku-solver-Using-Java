
public class SudokuSolver {

    // Function to print the Sudoku board
    public static void printBoard(int[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int d = 0; d < 9; d++) {
                System.out.print(board[r][d]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    // Check if placing num at board[row][col] is valid
    public static boolean isSafe(int[][] board, int row, int col, int num) {
        // Row check
        for (int d = 0; d < 9; d++) {
            if (board[row][d] == num) {
                return false;
            }
        }

        // Column check
        for (int r = 0; r < 9; r++) {
            if (board[r][col] == num) {
                return false;
            }
        }

        // 3x3 subgrid check
        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int r = startRow; r < startRow + 3; r++) {
            for (int d = startCol; d < startCol + 3; d++) {
                if (board[r][d] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    // Solve Sudoku using Backtracking
    public static boolean solveSudoku(int[][] board, int n) {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;

        // Find empty cell
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) { // 0 means empty
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        // No empty cell → solved
        if (isEmpty) {
            return true;
        }

        // Try filling numbers 1–9
        for (int num = 1; num <= n; num++) {
            if (isSafe(board, row, col, num)) {
                board[row][col] = num;

                if (solveSudoku(board, n)) {
                    return true;
                } else {
                    board[row][col] = 0; // backtrack
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] board = new int[][] {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        int n = board.length;

        if (solveSudoku(board, n)) {
            printBoard(board);
        } else {
            System.out.println("No solution exists!");
        }
    }
}
