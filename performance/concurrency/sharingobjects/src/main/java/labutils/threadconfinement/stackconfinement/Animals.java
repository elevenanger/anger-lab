package labutils.threadconfinement.stackconfinement;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author : anger
 */
public class Animals {
    private Ark ark;
    public void setArk(Ark ark) {
        this.ark = ark;
    }
    public int loadTheArk(Collection<Animal> candidates) {
        SortedSet<Animal> animals;
        int numPairs = 0;
        Animal candidate = null;

        // 创建局部变量 animals 封闭在方法中，无法逃逸
        // 先排序
        animals = new TreeSet<>(speciesGenderComparator);
        animals.addAll(candidates);
        // 后配对
        for (Animal animal : animals) {
            if (candidate == null || !candidate.isPotentialMate(animal))
                candidate = animal;
            else {
                ark.load(new AnimalPair(candidate, animal));
                numPairs++;
                candidate = null;
            }
        }

        return numPairs;
    }

    private static final Comparator<Animal> speciesGenderComparator = (a, b) -> {
        int speciesCompare = a.getSpecies().compareTo(b.getSpecies());
        return (speciesCompare != 0) ?
                speciesCompare :
                a.getGender().compareTo(b.getGender());
    };
}
