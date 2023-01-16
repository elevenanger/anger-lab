package parallelizing.puzzel.twodimensional;

import parallelizing.puzzel.Puzzle;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : anger
 */
public class TwoDimensionalPuzzle implements Puzzle<TwoDimensionalPosition, CrossMove> {
    private static final Random random = new Random();
    private final int xBound;
    private final int yBound;
    private final int[][] puzzleMap;
    private final TwoDimensionalPosition goal;
    private TwoDimensionalPosition initialPosition;

    public TwoDimensionalPuzzle(int xBound, int yBound) {
        this.xBound = xBound;
        this.yBound = yBound;
        puzzleMap = new int[xBound][yBound];
        goal = new TwoDimensionalPosition(random.nextInt(xBound - 1), random.nextInt(yBound - 1));
    }

    @Override
    public TwoDimensionalPosition initialPosition() {
        int initialX = random.nextInt(xBound - 1);
        int initialY = random.nextInt(yBound - 1);
        setFootprint(initialX, initialY);
        System.out.printf("initial position => (%3d, %3d)%n", initialX, initialY);
        initialPosition = new TwoDimensionalPosition(initialX, initialY);
        return initialPosition;
    }

    @Override
    public boolean isGoal(TwoDimensionalPosition position) {
        return position.equals(goal);
    }

    @Override
    public Set<CrossMove> legalMoves(TwoDimensionalPosition position) {
        TwoDimensionalPosMove move = new TwoDimensionalPosMove(position);
        return move.crossMove().stream()
            .filter(crossMove -> movable(move.move(crossMove)))
            .collect(Collectors.toSet());
    }

    private boolean movable(TwoDimensionalPosition position) {
        return isValidPosition(position) && !moved(position);
    }

    private boolean isValidPosition(TwoDimensionalPosition position) {
        return position.getX() >= 0 && position.getY() >= 0 &&
            position.getX() < xBound && position.getY() < yBound;
    }

    private boolean moved(TwoDimensionalPosition position) {
        return puzzleMap[position.getX()][position.getY()] != 0;
    }

    @Override
    public TwoDimensionalPosition move(TwoDimensionalPosition position, CrossMove move) {
        TwoDimensionalPosition movedPosition = new TwoDimensionalPosMove(position).move(move);
        setFootprint(movedPosition.getX(), movedPosition.getY());
        return movedPosition;
    }

    private void setFootprint(int x, int y) {
        puzzleMap[x][y] = 1;
    }

    public void printFootPrint(List<CrossMove> moves) {
        System.out.printf("goal     => (%3d, %3d)%n", goal.getX(), goal.getY());
        int[][] footPrints = new int[xBound][yBound];
        int i = 0;
        footPrints[initialPosition.getX()][initialPosition.getY()] = ++i;
        TwoDimensionalPosMove posMove = new TwoDimensionalPosMove(initialPosition);
        for (CrossMove move : moves) {
            TwoDimensionalPosition nextMove = posMove.move(move);
            footPrints[nextMove.getX()][nextMove.getY()] = ++i;
            posMove = new TwoDimensionalPosMove(nextMove);
        }

        for (int j = 0; j < yBound; j++) {
            for (int k = 0; k < xBound; k++) {
                System.out.printf("%4d", footPrints[k][j]);
            }
            System.out.println();
        }
    }
}
