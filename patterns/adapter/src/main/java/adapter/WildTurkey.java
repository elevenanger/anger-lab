package adapter;

/**
 * author : liuanglin
 * date : 2022/8/5 21:00
 * description : Turkey 实现类
 * 野生火鸡
 */
public class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("WildTurkey.gobble");
    }

    @Override
    public void fly() {
        System.out.println("WildTurkey.fly");
    }
}
