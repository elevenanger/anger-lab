package excel.service;

import excel.domain.TestData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.StreamSupport;

/**
 * author : liuanglin
 * date : 2022/7/1 14:56
 * description : 使用 Poi 库进行 Excel 文件解析
 */
public class PoiExcelParseService {

    public static void parseFile(String filePath) {
        try (InputStream in = Files.newInputStream(Paths.get(filePath));
            Workbook workbook = WorkbookFactory.create(in)){
            StreamSupport.stream(workbook.getSheetAt(0).spliterator(),false)
                .skip(1)
                .map(PoiExcelParseService::rowMapper)
                .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static TestData rowMapper(final Row row) {
        final TestData data = new TestData();
        data.setLineNo(row.getCell(0).getStringCellValue());
        data.setAcc(row.getCell(1).toString());
        data.setName(row.getCell(2).getStringCellValue());
        data.setAmt(BigDecimal.valueOf(row.getCell(3).getNumericCellValue()));
        data.setDesc(row.getCell(4).getStringCellValue());
        return data;
    }
}
