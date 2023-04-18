package labutils.threadconfinement.stackconfinement;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : anger
 */
public class Ark {
    private final Set<AnimalPair> animalPairs = new HashSet<>();
    public void load(AnimalPair animalPair) {
        animalPairs.add(animalPair);
    }
    @Override
    public String toString() {
        return "Ark{" +
            "animalPairs=" + animalPairs +
            '}';
    }
}
