package labutils.threadconfinement.stackconfinement;

/**
 * @author : anger
 * 配对好的动物
 */
public class AnimalPair {
    private final Animal a;
    private final Animal b;
    public AnimalPair(Animal a, Animal b) {
        this.a = a;
        this.b = b;
    }
    @Override
    public String toString() {
        return "AnimalPair{" +
            "a=" + a +
            ", b=" + b +
            '}';
    }
}
