package labutils.pubandescape.innerclassescape;

/**
 * @author : anger
 */
public class EventSource {
    private  EventListener listener;
    public void registerListener(EventListener listener) {
        this.listener = listener;
    }
    public EventListener getListener() {
        return listener;
    }
    public void doEvent(Event event) {
        listener.onEvent(event);
    }
}
