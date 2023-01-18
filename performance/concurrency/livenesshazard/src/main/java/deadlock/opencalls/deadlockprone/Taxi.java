package deadlock.opencalls.deadlockprone;

import deadlock.opencalls.Point;

/**
 * @author : anger
 */
public class Taxi {
    private final Dispatcher dispatcher;
    private Point location;
    private Point destination;

    public Taxi(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
        dispatcher.join(this);
    }

    private synchronized void setDestination(Point destination) {
        this.destination = destination;
    }

    public synchronized Point getLocation() {
        return location;
    }

    public synchronized void setLocation(Point location) {
        this.location = location;
        System.out.printf("%d %d location set.%n", location.getX(), location.getY());
        if (location.equals(destination))
            dispatcher.notifyAvailable(this);
    }
}
