package excel.tools;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static excel.tools.ObjectTransformer.*;

/**
 * author : liuanglin
 * date : 2022/7/2 22:00
 * description :
 */
class ObjectTransformerTest {

    @Test
    void singleTrans() {
        EasyCome come = new EasyCome();
        come.setI(10);
        EasyGo go = fromSourceToTargetObject(come, EasyGo::new);
        assertEquals(come.getI(), go.getI());
    }

    @Test
    void batchTransform() {
        IntFunction<Easy> easyFunction =
            integer -> new Easy().setIAndReturn(integer);
        batchTrans(IntStream.range(0, 10)
            .mapToObj(easyFunction)
            .collect(Collectors.toList()), EasyGo::new)
                .forEach(easyGo -> System.out.print(easyGo.getI() + " "));
    }
}

@Data
class Easy {
    private int i;
    public Easy setIAndReturn(final int i) {
        System.out.println("Easy.setIAndReturn : " + i);
        this.setI(i);
        return this;
    }
}

class EasyCome extends Easy{ }

class EasyGo extends Easy{ }
