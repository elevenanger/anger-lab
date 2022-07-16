package context.domain;

/**
 * author : liuanglin
 * date : 2022/7/15 14:53
 * description : 鹦鹉
 */
public class Parrot {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Parrot{" +
            "name='" + name + '\'' +
            '}';
    }
}
