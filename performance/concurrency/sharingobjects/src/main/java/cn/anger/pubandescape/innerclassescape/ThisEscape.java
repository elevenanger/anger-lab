package cn.anger.pubandescape.innerclassescape;

import cn.anger.annotation.NotThreadSafe;

/**
 * @author : anger
 * 内部类的实例隐式包含了对于 this 的引用
 * 发布内部类的实例同时隐式发布了对于 this 对象实例
 */
@NotThreadSafe
public class ThisEscape {
    private ConstructionStatus constructionStatus = ConstructionStatus.INCOMPLETE;
    public ThisEscape(EventSource source) {
        // 匿名内部类包含对于外部类的 this 的引用
        // 将匿名内部去发布给其它的对象
        // 等于将当前类的实例发布出去
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event event) {
                doSomething(event);
            }
        });

        new Thread(() -> source.doEvent(new Event("ThisEscape "))).start();

        try {
            System.out.println("This Escape Constructing.");
            Thread.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        constructionStatus = ConstructionStatus.COMPLETE;

    }

    void doSomething(Event event) {
        System.out.println(event.getEventMsg() + " " + constructionStatus.name());
    }
}