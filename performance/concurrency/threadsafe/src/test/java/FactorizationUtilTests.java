import labutils.util.FactorizationUtil;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author : anger
 */
public class FactorizationUtilTests {

    @Test
    void testFactorization() {
        BigInteger[] factors =
            FactorizationUtil.doFactorization(new BigInteger("1000"));

        System.out.println(Arrays.toString(factors));
    }
}
