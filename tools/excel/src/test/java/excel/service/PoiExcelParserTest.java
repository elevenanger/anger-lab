package excel.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * author : anger
 * date : 2022/7/1 08:08
 * description : Excel 解析测试
 * 使用 pou 库
 */
@SpringBootTest
class PoiExcelParserTest {

    static final String FILE_PATH = "./src/test/resources/test.xlsx";

    @Autowired
    PoiExcelParseService service;

    @Test
    void parseFile() {
        service.parseFile(FILE_PATH);
    }
}
