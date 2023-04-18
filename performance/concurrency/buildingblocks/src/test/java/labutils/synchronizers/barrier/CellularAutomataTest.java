package labutils.synchronizers.barrier;

import org.junit.jupiter.api.Test;

/**
 * @author : anger
 */
class CellularAutomataTest {

    @Test
    void testAutoMata() {
        CellularAutomata cellularAutomata = new CellularAutomata(
            new SimpleBoard(100, 100, 0, 0));
        cellularAutomata.start();
    }

}