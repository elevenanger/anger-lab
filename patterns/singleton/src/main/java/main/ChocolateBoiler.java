package main;

/**
 * @author Anger
 * created on 2022/8/2
 *
 * 巧克力锅炉
 * 倒入巧克力和牛奶煮沸
 * 完成搅拌将混合物排干到下一个处理阶段
 * 需要确保其操作的唯一性
 */
public class ChocolateBoiler {

    private static volatile ChocolateBoiler uniqueBoiler;
    // 是否经过煮沸
    private boolean boiled;
    // 是否为空
    private boolean empty;
    /**
     * 初始化巧克力锅炉
     * 初始化状态为未煮沸
     * 锅炉是空的
     */
    private ChocolateBoiler() {
        boiled = false;
        empty = true;
    }
    public static ChocolateBoiler getChocolateBoilerInstance() {
        if (uniqueBoiler == null)
            synchronized (ChocolateBoiler.class){
                if (uniqueBoiler == null)
                    uniqueBoiler = new ChocolateBoiler();
            }
        return uniqueBoiler;
    }
    /**
     * 将牛奶和巧克力填满锅炉
     * 只有在锅炉为空的状态调用此方法才有效
     */
    public void fill() {
        if (isEmpty()) {
            empty = false;
            boiled = false;
        }
    }

    /**
     * 煮沸混合物
     * 只有在锅炉不为空并且还未煮沸的状态调用此方法才有效
     * 调用结束后煮沸状态变成已煮沸的状态
     */
    public void boil() {
        if (!isEmpty() && !isBoiled()) {
            boiled = true;
        }
    }

    /**
     * 排空已经煮沸完的混合物
     * 只有在不为空并且已经煮沸的状态调用此方法才有效
     */
    public void drain() {
        if (!isEmpty() && isBoiled()) {
            empty = true;
        }
    }
    public boolean isBoiled() {
        return boiled;
    }

    public boolean isEmpty() {
        return empty;
    }
}
