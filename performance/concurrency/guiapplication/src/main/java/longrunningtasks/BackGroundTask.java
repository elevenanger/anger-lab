package longrunningtasks;

import singlethread.GuiExecutor;

import java.util.concurrent.*;

/**
 * @author : anger
 * 支持取消，任务完成通知和任务进行中通知的任务
 */
public abstract class BackGroundTask<V> implements Runnable, Future<V> {
    private final FutureTask<V> computation = new Computation();

    private class Computation extends FutureTask<V> {
        public Computation() {
            super(BackGroundTask.this::compute);
        }

        @Override
        protected final void done() {
            GuiExecutor.instance().execute(() -> {
                V value = null;
                Throwable thrown = null;
                boolean canceledFlag = false;
                try {
                    value = get();
                } catch (ExecutionException e) {
                    thrown = e.getCause();
                } catch (CancellationException e) {
                    canceledFlag = true;
                } catch (InterruptedException consumed)
                {} finally {
                    onCompletion(value, thrown, canceledFlag);
                }
            });
        }
    }

    protected void setProgress(final int current, final int max) {
        GuiExecutor.instance().execute(() -> onProgress(current, max));
    }

    protected abstract V compute() throws Exception;

    protected void onCompletion(V result, Throwable exception, boolean cancelled) {
        if (result != null)
            System.out.printf("task completed, result => %s%n", result.toString());
    }

    protected void onProgress(int current, int max) {}

    public boolean cancel(boolean mayInterruptIfRunning) {
        return computation.cancel(mayInterruptIfRunning);
    }

    public V get() throws  InterruptedException, ExecutionException {
        return computation.get();
    }

    @Override
    public V get(long timeout, TimeUnit unit)
        throws InterruptedException,
        ExecutionException,
        TimeoutException {
        return computation.get(timeout, unit);
    }

    public boolean isCancelled() {
        return computation.isCancelled();
    }

    public boolean isDone() {
        return computation.isDone();
    }

    public void run() {
        computation.run();
    }
}
