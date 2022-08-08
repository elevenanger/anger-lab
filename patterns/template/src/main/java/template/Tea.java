package template;

/**
 * @author Anger
 * created on 2022/8/8
 */
public class Tea extends CaffeineBeverage {
    @Override
    void brew() {
        System.out.println("抖动茶包");
    }

    @Override
    void addCondiment() {
        System.out.println("加入柠檬");
    }
}
