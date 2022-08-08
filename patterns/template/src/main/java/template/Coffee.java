package template;

import java.util.Scanner;

/**
 * @author Anger
 * created on 2022/8/8
 */
public class Coffee extends CaffeineBeverage {
    @Override
    void brew() {
        System.out.println("研磨咖啡豆");
    }

    @Override
    void addCondiment() {
        System.out.println("加入牛奶和糖");
    }

    @Override
    protected boolean customWantCondiment() {
        return askUserForCondiment();
    }

    private boolean askUserForCondiment() {
        String answer = null;
        System.out.println("你需要在咖啡中加入牛奶和糖吗？");

        Scanner in = new Scanner(System.in);
        answer = in.nextLine();
        assert answer != null;
        return answer.equals("Y");
    }
}
