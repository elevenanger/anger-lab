package parallelizing.puzzel.concurrent;

import org.junit.jupiter.api.Test;
import parallelizing.puzzel.twodimensional.CrossMove;
import parallelizing.puzzel.twodimensional.TwoDimensionalPosition;
import parallelizing.puzzel.twodimensional.TwoDimensionalPuzzle;

import java.util.List;

/**
 * @author : anger
 */
class PuzzleSolverTest {
    TwoDimensionalPuzzle puzzle = new TwoDimensionalPuzzle(9, 9);
    PuzzleSolver<TwoDimensionalPosition, CrossMove> puzzleSolver =
        new PuzzleSolver<>(puzzle);

    @Test
    void puzzleSolverTest() throws InterruptedException {
        List<CrossMove> moves = puzzleSolver.solve();
        System.out.println(moves);
        puzzle.printFootPrint(moves);
    }
}