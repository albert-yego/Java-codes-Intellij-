public class SudokuSolver {

    static int N = 9;


    //Sudoku solver
    static boolean solver(int[][] matrix, int row,
                          int col) {


        if (row == N - 1 && col == N) {
            return true;
        }

        if (col == N) {
            row++;
            col = 0;
        }

        if (matrix[row][col] != 0) {
            return solver(matrix, row, col + 1);
        }

        String piece = "CNGBIM213";

        for (int i = 0; i < piece.length(); i++) {

            char figures = piece.charAt(i);

            if (analyzer(matrix, row, col, figures)) {

                matrix[row][col] = figures;

                if (solver(matrix,row, col + 1)) {
                    return true;
                }

            }

            matrix[row][col] = 0;

        }
        return false;

    }

    //this checks whether each row, colum or in the 3x3 matrix have similar figures
    static boolean analyzer(int[][] matrix, int row, int col, char figures) {

        for (int x = 0; x <= 8; x++) {
            if (matrix[row][x] == figures) {
                return false;
            }
        }

        for (int x = 0; x <= 8; x++) {
            if (matrix[x][col] == figures) {
                return false;
            }
        }

        int rowstart = row - row % 3, columnstart = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[i + rowstart][j + columnstart] == figures)
                    return false;
            }
        }
        return true;

    }

    //prints the solved sudoku
    static void print(int[][] matrix) {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print((char) matrix[i][j] + " ");
            }
            System.out.println();
        }

    }

}