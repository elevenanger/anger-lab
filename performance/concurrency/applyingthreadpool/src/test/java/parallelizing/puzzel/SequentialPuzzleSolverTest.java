package parallelizing.puzzel;

import org.junit.jupiter.api.Test;
import parallelizing.puzzel.twodimensional.CrossMove;
import parallelizing.puzzel.twodimensional.TwoDimensionalPosition;
import parallelizing.puzzel.twodimensional.TwoDimensionalPuzzle;

import java.util.List;

/**
 * @author : anger
 */
class SequentialPuzzleSolverTest {

    @Test
    void testTwoDimensionalPuzzle() {
        TwoDimensionalPuzzle puzzle = new TwoDimensionalPuzzle(4, 4);
        SequentialPuzzleSolver<TwoDimensionalPosition, CrossMove>
            puzzleSolver = new SequentialPuzzleSolver<>(puzzle);
        List<CrossMove> moves = puzzleSolver.solve();
        System.out.println(moves);
        puzzle.printFootPrint(moves);
    }
}