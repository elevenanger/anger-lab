package adapter;

/**
 * author : liuanglin
 * date : 2022/8/5 21:23
 * description : Duck 适配器
 * 将 Turkey 转换成 Duck
 */
public class DuckAdapter implements Turkey {
    private final Duck duck;

    public DuckAdapter(Duck duck) {
        this.duck = duck;
    }

    @Override
    public void gobble() {
        for (int i = 0; i < 3; i++) {
            duck.quack();
        }
    }

    @Override
    public void fly() {
        duck.fly();
    }
}
