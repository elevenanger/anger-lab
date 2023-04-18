package labutils.safepublication;

import java.util.Random;

/**
 * @author : anger
 */
public class StuffIntoPublic {
    private static final Random rand = new Random();
    // 不安全的发布对象
    public Holder holder;
    public void toHold() {
        holder = new Holder(rand.nextInt());
    }
}
