package labutils.threadconfinement.stackconfinement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : anger
 */
class AnimalsTest {
    private static final Random random = new Random();
    private static Collection<Animal> animalCollection;
    private final Ark ark = new Ark();
    private final Animals animals = new Animals();

    @BeforeAll
    static void initAnimalCollection() {
        Supplier<Animal> randomAnimalSupplier = () ->
            new Animal(
                Species.values()[random.nextInt(Species.values().length)],
                Gender.values()[random.nextInt(Gender.values().length)]
            );

        animalCollection = Stream.generate(randomAnimalSupplier)
            .limit(10)
            .collect(Collectors.toCollection(ArrayList::new));

        System.out.println(animalCollection);
    }

    @Test
    void testLoadArk() {
        animals.setArk(ark);
        int pairs = animals.loadTheArk(animalCollection);
        System.out.println(pairs);
        System.out.println(ark);
    }

}