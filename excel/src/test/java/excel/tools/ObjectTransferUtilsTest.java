package excel.tools;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author : liuanglin
 * date : 2022/7/2 22:00
 * description :
 */
class ObjectTransferUtilsTest {

    @Test
    void test() {
        EasyCome come = new EasyCome();
        come.setI(10);
        EasyGo go= ObjectTransferUtils.fromSourceToTargetObject(come, EasyGo::new);
        assertEquals(come.getI(), go.getI());
    }

}

@Data
class EasyCome{ private int i;}

@Data
class EasyGo { private int i;}
