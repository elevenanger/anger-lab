package cn.anger.synchronizers.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

/**
 * @author : anger
 */
public class CellularAutomata {
    private final CyclicBarrier barrier;
    private final Board mainBoard;
    private final Worker[] workers;

    public CellularAutomata(Board board) {
        this.mainBoard = board;
        // CPU 数量
        int count = Runtime.getRuntime().availableProcessors();
        this.barrier =
            // 线程数 = CPU 数 & barrier action = commitNewValue
            new CyclicBarrier(count, mainBoard::commitNewValue);
        this.workers = new Worker[count];
        for (int i = 0; i < count; i++)
            // 将任务拆分成若干子任务
            workers[i] = new Worker(mainBoard.getSubBoard(count, i));
    }

    private class Worker implements Runnable {
        private final Board board;

        public Worker(Board board) {
            this.board = board;
        }

        @Override
        public void run() {
            // 子任务中的状态位标识子任务是否已经聚合
            while (!board.hasConverged()) {
                for (int x = 0; x <= board.getMaxX(); x++) {
                    for (int y = 0; y <= board.getMaxY(); y++)
                        board.setNewValue(x, y, computeValue(x, y));
                }

                // 运算结束，线程抵达屏障点,进入 await 状态
                try {
                    barrier.await();
                } catch (BrokenBarrierException | InterruptedException e) {
                    return;
                }
            }
        }

        private int computeValue(int x, int y) {
            return x + y;
        }
    }

    /**
     * 每个任务都用一个线程来执行
     */
    public void start() {
        IntStream
            .range(0, workers.length)
            .forEach(i -> new Thread(workers[i]).start());
    }
}
