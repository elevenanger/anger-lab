package duck.observer;

/**
 * author : anger
 * date : 2022/8/16 12:22
 * description : 实际的观察者对象
 */
public class QuackObserver implements Observer {

    @Override
    public void update(QuackObservable duck) {
        System.out.printf("%s 叫了一声。。。%n",
                            duck.getClass().getSimpleName());
    }
}
