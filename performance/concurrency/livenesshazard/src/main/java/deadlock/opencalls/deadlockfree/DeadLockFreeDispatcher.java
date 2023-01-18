package deadlock.opencalls.deadlockfree;

import deadlock.opencalls.Image;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : anger
 */
public class DeadLockFreeDispatcher {
    private final Set<DeadLockFreeTaxi> taxis;
    private final Set<DeadLockFreeTaxi> availableTaxis;

    public DeadLockFreeDispatcher() {
        taxis = new HashSet<>();
        availableTaxis = new HashSet<>();
    }

    public synchronized void join(DeadLockFreeTaxi taxi) {
        taxis.add(taxi);
    }

    public synchronized void notifyAvailable(DeadLockFreeTaxi taxi) {
        availableTaxis.add(taxi);
    }

    public Image getImage() {
        Set<DeadLockFreeTaxi> copy;
        synchronized (this) {
            copy = new HashSet<>(taxis);
        }
        Image image = new Image();
        copy.stream()
            .map(DeadLockFreeTaxi::getLocation)
            .forEach(image::drawMarker);
        return image;
    }
}