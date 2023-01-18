package deadlock.opencalls.deadlockfree;

import deadlock.opencalls.Point;

/**
 * @author : anger
 */
public class DeadLockFreeTaxi {
    private final DeadLockFreeDispatcher dispatcher;
    private Point location;
    private Point destination;

    public DeadLockFreeTaxi(DeadLockFreeDispatcher dispatcher) {
        this.dispatcher = dispatcher;
        dispatcher.join(this);
    }

    private synchronized void setDestination(Point destination) {
        this.destination = destination;
    }

    public synchronized Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        boolean reachedDestination = false;
        // 只同步特定的代码块而不是整个方法
        synchronized (this) {
            this.location = location;
            if (location.equals(destination))
                reachedDestination = true;
        }
        if (reachedDestination)
            dispatcher.notifyAvailable(this);
    }
}
