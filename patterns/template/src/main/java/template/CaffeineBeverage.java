package template;

/**
 * @author Anger
 * created on 2022/8/8
 * 咖啡因饮料抽象类
 * 模板方法模式：
 * 使用一个方法定义算法实现框架和步骤
 * 每个步骤都是一个方法
 * 允许子类实现其中一个或者多个步骤
 * 确保模板的框架结构不会发生变化
 */
public abstract class CaffeineBeverage {

    /**
     * 准备饮料的方法适用于所有的子类（茶和咖啡）
     * 定义了饮料的制作过程
     * 这个方法定义为 final
     * 确保算法的步骤或者说模板的框架不会被改变
     * 防止被子类重写
     * 尽管其中一些步骤不同的子类实现的方式不一样
     * 但是准备食谱的过程本质上是相同的
     * 所以将这个方法放置在父类中
     * 这个方法则称之为模板方法,原因：
     * 1、它是一个方法
     * 2、它作为一个算法的模板（制作咖啡因饮料）
     */
    final void prepareRecipe() {
        /*
        在模板方法中
        算法的每一个步骤都是一个方法
        其中一些方法在这个类中实现
        一些方法在子类中实现
        需要子类实现的方法定义为抽象方法
         */
        boilWater();
        brew();
        pourInCup();
        /*
        customWantCondiment() 方法是一个钩子方法
        这个方法声明在抽象类中
        但是只进行空的或者默认实现
        这个方法可以由子类进行重写
        使得子类能够在各个步骤之间与算法挂钩
        当某个步骤子类必须提供实现的情况下使用抽象方法
        当某个步骤是子类的可选项的情况下使用钩子方法来控制算法流程
        钩子方法还可以使得子类可以对模板方法中已经发生或者将要发生的步骤做出反应
         */
        if (customWantCondiment())
            addCondiment();
    }

    /**
     * 饮料的制作步骤为
     * 1、煮沸水
     * 2、准备原材料
     * 3、将溶解原材料的饮料倒入杯中
     * 4、加入配料
     * 由于煮沸水喝倒入杯中的动作是具体的行为
     * 在各个子类中是相同的
     * 所以直接在父类中完成实现
     * 准备原材料和加入配料的步骤
     * 随着子类类型而变化
     * 定义为抽象类
     * 由子类进行具体的实现
     */
    abstract void brew();
    abstract void addCondiment();
    protected boolean customWantCondiment() {
        return true;
    }
    protected void boilWater() {
        System.out.println("煮开水");
    }
    protected void pourInCup() {
        System.out.println("将饮料倒入杯中");
    }
}
