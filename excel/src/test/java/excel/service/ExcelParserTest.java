package excel.service;

import excel.service.PoiExcelParseService;
import org.junit.jupiter.api.Test;

/**
 * author : liuanglin
 * date : 2022/7/1 08:08
 * description : Excel 解析测试
 * 使用 pou 库
 */
class ExcelParserTest {

    static final String FILE_PATH= "./src/test/resources/normal.xlsx";

    @Test
    void parseFile() {
        PoiExcelParseService.parseFile(FILE_PATH);
    }
}
