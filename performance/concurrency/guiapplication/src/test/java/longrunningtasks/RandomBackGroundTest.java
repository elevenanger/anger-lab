package longrunningtasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class RandomBackGroundTest {

    @Test
    void randomColorTest() {
        RandomBackGround randomBackGround = new RandomBackGround();
        randomBackGround.renderPage();
    }
}