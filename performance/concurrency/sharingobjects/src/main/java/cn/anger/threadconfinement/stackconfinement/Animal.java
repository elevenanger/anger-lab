package cn.anger.threadconfinement.stackconfinement;

/**
 * @author : anger
 */
public class Animal {
    private final Species species;
    private final Gender gender;
    public Animal(Species species, Gender gender) {
        this.species = species;
        this.gender = gender;
    }
    public Species getSpecies() { return species; }
    public Gender getGender() { return gender; }
    public boolean isPotentialMate(Animal other) {
        return other.gender != this.gender && other.species == this.species;
    }
    @Override
    public String toString() {
        return "Animal{" +
            "species=" + species +
            ", gender=" + gender +
            '}';
    }
}
