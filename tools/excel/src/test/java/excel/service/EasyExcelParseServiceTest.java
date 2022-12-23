package excel.service;

import excel.domain.ExcelDataBO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * author : liuanglin
 * date : 2022/7/1 15:39
 */
@SpringBootTest
@Slf4j
class EasyExcelParseServiceTest {

    static final String FILE_PATH =  "./src/test/resources/write1661568972131.xlsx";
    static final String WRITE_FILE_PATH =  "./src/test/resources/";

    String fileName = WRITE_FILE_PATH + "write" + System.currentTimeMillis() + ".xlsx";

    static final AtomicInteger line_no = new AtomicInteger(0);

    @Autowired
    EasyExcelParseService service;

    @Test
    void testWrite() {
        log.info(fileName);
        service.writeExcel(fileName, genList);
    }

    @Test
    void testRead() {
        log.info(fileName);
        service.parseFile(FILE_PATH);
    }

    @Test
    void testReadRecursive() {
        log.info(fileName);
        service.parseFileRecursive(FILE_PATH);
    }
    IntFunction<ExcelDataBO> genData = i -> {
        ExcelDataBO bo = new ExcelDataBO();
        bo.setLineNo(String.valueOf(line_no.getAndIncrement()));
        bo.setName("nobody");
        bo.setAcc("622848112800064451");
        bo.setAmt(BigDecimal.valueOf(100.00 + i));
        bo.setDesc("test");
        return bo;
    };

    Supplier<Collection<?>> genList = () ->
        IntStream.range(0, 1000)
            .mapToObj(genData)
            .collect(Collectors.toCollection(ArrayList::new));
}