package iterator;

import org.junit.jupiter.api.Test;

/**
 * @author Anger
 * created on 2022/8/9
 */
class WaitressTest {

    @Test
    void testWaitressPrintMenu() {
        Waitress waitress = new Waitress(new DinerMenu());
        waitress.printMenu();
        waitress = new Waitress(new CafeMenu());
        waitress.printMenu();
    }

}