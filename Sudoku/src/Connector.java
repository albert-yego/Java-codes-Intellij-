public class Connector {
    public void creator() {
        new SudokuCreator(9,57).creator();
    }

    public void solver() {
        new SudokuSolver().solver(SudokuCreator.board,0,0);
    }
}
