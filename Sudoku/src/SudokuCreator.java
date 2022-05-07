import java.util.*;

public class SudokuCreator {

    static int[][] board;
    int RC; // number of columns/rows.
    int SRC; // square root of RC
    int MISS; // No. Of missing digits

    SudokuCreator(int RC, int MISS) {
        this.RC = RC;
        this.MISS = MISS;

        double RC2 = Math.sqrt(RC);
        SRC = (int) RC2;

        board = new int[RC][RC];
    }

    // Sudoku creator
    public void creator() {

        // It fills in the diagonal of the 3*3 matrices
        for (int h = 0; h < RC; h = h + SRC) {

            fillingcells(h, h);

        }

        remaining(0, SRC);

        // Remove digits randomly to create the game
        int z = MISS;
        while (z != 0) {

            int matrixID = randomGenerator(RC * RC);
            //digits are randomly removed from the board by i & j coordinates
            int i = (matrixID / RC);
            int j = matrixID % 9;
            if (j != 0) {
                j = j - 1;
            }
            if (board[i][j] != 0) {
                z--;
                board[i][j] = 0;
            }

        }

    }

    // It fills the remaining cells
    boolean remaining(int i, int j) {

        if (j >= RC && i < RC - 1) {
            i = i + 1;
            j = 0;
        }
        if (i >= RC && j >= RC)
            return true;

        if (i < SRC) {
            if (j < SRC)
                j = SRC;
        } else if (i < RC - SRC) {
            if (j == (i / SRC) * SRC)
                j = j + SRC;
        } else {
            if (j == RC - SRC) {
                i = i + 1;
                j = 0;
                if (i >= RC)
                    return true;
            }
        }

        for (int num = 1; num <= RC; num++) {
            if (Checker(i, j, num)) {
                board[i][j] = num;
                if (remaining(i, j + 1))
                    return true;

                board[i][j] = 0;
            }
        }
        return false;

    }

    //It fills the 3x3 matrices
    void fillingcells(int row, int col) {
        char digit;
        for (int i = 0; i < SRC; i++) {
            for (int j = 0; j < SRC; j++) {
                do {
                    digit = randomGen();
                }
                while (!cellsnotused(row, col, digit));

                board[row + i][col + j] = digit;
            }
        }
    }

    //randomly generates the string of characters "CNGBIM213"
    static char randomGen() {

        String chars = "CNGBIM213";
        Random rnd = new Random();
        return chars.charAt(rnd.nextInt(chars.length()));

    }

    //A basic random generator
    int randomGenerator(int bin) {

        return (int) Math.floor((Math.random() * bin));

    }

    //It checks to see if it is safe to put a digit in every cell
    boolean Checker(int i, int j, int digit) {

        return (rowsnotused(i, digit) && columnsnotused(j, digit) && cellsnotused(i - i % SRC, j - j % SRC, digit));

    }

    //It checks by 3x3 cells
    boolean cellsnotused(int rowST, int columnST, int digit) {
        for (int i = 0; i < SRC; i++) {
            for (int j = 0; j < SRC; j++) {
                if (board[rowST + i][columnST + j] == digit) {
                    return false;
                }
            }
        }
        return true;
    }

    //It checks by rows
    boolean rowsnotused(int i, int digit) {
        for (int j = 0; j < RC; j++) {
            if (board[i][j] == digit) {
                return false;
            }
        }
        return true;
    }

    //It checks by columns
    boolean columnsnotused (int j, int digit) {
        for (int i = 0; i < RC; i++) {
            if (board[i][j] == digit){
                return false;
            }
        }
        return true;
    }

    // Prints the generated sudoku
    public void printSudoku() {
        for (int i = 0; i < RC; i++) {
            for (int j = 0; j < RC; j++)
                System.out.print((char)board[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

}
