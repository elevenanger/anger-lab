package labutils.pubandescape.innerclassescape;

/**
 * @author : anger
 * 使用静态工厂方法来避免构造过程中的逃逸问题
 */
public class SafeListener {
    private ConstructionStatus constructionStatus = ConstructionStatus.INCOMPLETE;
    private final EventListener listener;
    private SafeListener() {
        listener = new EventListener() {
            @Override
            public void onEvent(Event event) {
                doSomething(event);
            }
        };

        try {
            System.out.println("Safe Listener Constructing.");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        constructionStatus = ConstructionStatus.COMPLETE;
    }
    public static SafeListener newInstance(EventSource source) {
        SafeListener safeListener = new SafeListener();
        source.registerListener(safeListener.listener);
        new Thread(() -> source.doEvent(new Event("SafeListener Construction "))).start();
        return safeListener;
    }

    private void doSomething(Event event) {
        System.out.println(event.getEventMsg() + " " + constructionStatus.name());
    }
}
