package cn.anger.safepublication;

import java.util.Random;

/**
 * @author : anger
 */
public class SafeIntoPublic {
    private static final Random rand = new Random();
    public volatile Holder holder;
    public void toHold() {
        holder = new Holder(rand.nextInt());
    }
}
