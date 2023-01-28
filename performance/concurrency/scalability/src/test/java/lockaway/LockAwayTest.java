package lockaway;

import org.junit.jupiter.api.Test;

/**
 * @author : anger
 */
class LockAwayTest {

    @Test
    void lockAwayTest() {
        LockAway away = new LockAway();
        away.lockAway();
    }

    @Test
    void neverEscapeTest() {
        LockAway away = new LockAway();
        System.out.println(away.neverEscape());
    }

}