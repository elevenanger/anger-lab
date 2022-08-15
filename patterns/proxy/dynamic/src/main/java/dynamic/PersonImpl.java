package dynamic;

import java.util.function.BiFunction;

/**
 * author : liuanglin
 * date : 2022/8/15 08:25
 * description : Person 接口实现类
 */
public class PersonImpl implements Person {
    String name;
    String gender;
    int geekRating;
    int ratingCount;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String genGender() {
        return gender;
    }

    @Override
    public int getGeekRating() {
        if (ratingCount == 0) return 0;
        return geekRating / ratingCount;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public void setGeekRating(int geekRating) {
        this.geekRating += geekRating;
        ratingCount ++;
    }

    public static final BiFunction<String, String, Person> newPersonInstance =
        (perName, perGender) -> {
            Person person = new PersonImpl();
            person.setName(perName);
            person.setGender(perGender);
            return person;
        };

    @Override
    public String toString() {
        return "PersonImpl{" +
            "name='" + name + '\'' +
            ", gender='" + gender + '\'' +
            ", geekRating=" + getGeekRating() +
            '}';
    }
}
