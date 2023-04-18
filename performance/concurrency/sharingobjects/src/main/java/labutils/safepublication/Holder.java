package labutils.safepublication;

/**
 * @author : anger
 */
public class Holder {
    private int hold;
    public Holder(int hold) { this.hold = hold; }
    public void assertSanity() {
        if (hold != hold) {
            System.out.println("hold inconsistency");
            throw new AssertionError("This statement is false.");
        }
    }
}
