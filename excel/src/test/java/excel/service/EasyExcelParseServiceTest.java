package excel.service;

import org.junit.jupiter.api.Test;

/**
 * author : liuanglin
 * date : 2022/7/1 15:39
 */
class EasyExcelParseServiceTest {

    static final String FILE_PATH =  "./src/test/resources/normal.xlsx";
    @Test
    void testRead() {
        EasyExcelParseService.parseFile(FILE_PATH);
    }

}