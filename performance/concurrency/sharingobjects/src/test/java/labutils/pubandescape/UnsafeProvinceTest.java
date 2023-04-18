package labutils.pubandescape;

import labutils.pubandescape.nonprivatemethodescape.UnsafeProvince;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author : anger
 */
class UnsafeProvinceTest {

    final UnsafeProvince province = new UnsafeProvince();

    @Test
    void testUnsafeMutableInnerStatePublication() {
        final String[] provinces = province.getProvinces().clone();
        province.getProvinces()[2] = "Anhui";
        assertNotEquals(provinces, province.getProvinces());
    }

}