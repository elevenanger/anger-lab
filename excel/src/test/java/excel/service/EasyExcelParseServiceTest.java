package excel.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * author : liuanglin
 * date : 2022/7/1 15:39
 */
@SpringBootTest
class EasyExcelParseServiceTest {

    static final String FILE_PATH =  "./src/test/resources/test.xlsx";

    @Autowired
    EasyExcelParseService service;
    @Test
    void testRead() {
        service.parseFile(FILE_PATH);
    }

}