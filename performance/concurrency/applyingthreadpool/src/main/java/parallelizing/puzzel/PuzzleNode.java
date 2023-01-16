package parallelizing.puzzel;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : anger
 * 拼图解法链
 */
public class PuzzleNode<P, M> {
    // 当前位置
    protected final P pos;
    // 移动
    protected final M move;
    // 之前的位置
    protected final PuzzleNode<P, M> prev;

    public PuzzleNode(P pos, M move, PuzzleNode<P, M> prev) {
        this.pos = pos;
        this.move = move;
        this.prev = prev;
    }

    /**
     * 移动步骤
     * @return 从起始位置移动到当前位置的移动步骤列表
     */
    public List<M> asMoveList() {
        List<M> solution = new LinkedList<>();
        for (PuzzleNode<P, M> n = this; n.move != null; n = n.prev)
            solution.add(0, n.move);
        return solution;
    }
}
