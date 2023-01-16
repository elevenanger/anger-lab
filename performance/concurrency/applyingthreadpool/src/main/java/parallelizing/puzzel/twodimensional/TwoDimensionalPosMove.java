package parallelizing.puzzel.twodimensional;

import java.util.EnumSet;
import java.util.Set;

/**
 * @author : anger
 */
public class TwoDimensionalPosMove {
    private final TwoDimensionalPosition position;

    public TwoDimensionalPosMove(TwoDimensionalPosition position) {
        this.position = position;
    }

    public Set<CrossMove> crossMove() {
        return EnumSet.allOf(CrossMove.class);
    }

    public TwoDimensionalPosition move(CrossMove move) {
        switch (move) {
            case MOVE_UP:
                return new TwoDimensionalPosition(position.getX(), position.getY() - 1);
            case MOVE_DOWN:
                return new TwoDimensionalPosition(position.getX(), position.getY() + 1);
            case MOVE_LEFT:
                return new TwoDimensionalPosition(position.getX() - 1, position.getY());
            case MOVE_RIGHT:
                return new TwoDimensionalPosition(position.getX() + 1, position.getY());
            default:
                throw new UnsupportedOperationException("unsupported move" + move.name());
        }
    }

}
