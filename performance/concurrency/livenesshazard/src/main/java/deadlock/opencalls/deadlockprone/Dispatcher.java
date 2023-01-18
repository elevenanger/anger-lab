package deadlock.opencalls.deadlockprone;

import deadlock.opencalls.Image;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : anger
 */
public class Dispatcher {
    private final Set<Taxi> taxis;
    private final Set<Taxi> availableTaxis;

    public Dispatcher() {
        taxis = new HashSet<>();
        availableTaxis = new HashSet<>();
    }

    public synchronized void join(Taxi taxi) {
        taxis.add(taxi);
    }

    public synchronized void notifyAvailable(Taxi taxi) {
        availableTaxis.add(taxi);
    }

    public synchronized Image getImage() {
        Image image = new Image();
        taxis.stream()
            .map(Taxi::getLocation)
            .forEach(image::drawMarker);
        return image;
    }
}