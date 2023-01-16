package parallelizing.puzzel;

import java.util.Set;

/**
 * @author : anger
 * 滑块拼图
 * 从初始位置移动到目标位置
 */
public interface Puzzle<P, M> {
    /**
     * 初始位置
     * @return 位置
     */
    P initialPosition();

    /**
     * 判断是否已经到达目标位置
     * @param position 位置
     * @return true 已经到达目标位置
     */
    boolean isGoal(P position);

    /**
     * 传入位置信息，返回合法的移动集合
     * @param position 位置信息
     * @return 当前位置可以进行的移动集合
     */
    Set<M> legalMoves(P position);

    /**
     * 移动位置
     * @param position 当前位置信息
     * @param move 位移动作
     * @return 移动后的位置信息
     */
    P move(P position, M move);
}
