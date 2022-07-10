package excel.service;

import excel.data.ExcelDataMapper;
import excel.data.ExcelDataPO;
import excel.domain.ExcelDataBO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.StreamSupport;

import static objects.ObjectTransformer.fromSourceToTargetObject;

/**
 * author : liuanglin
 * date : 2022/7/1 14:56
 * description : 使用 Poi 库进行 Excel 文件解析
 */
@Slf4j
@Service
public class PoiExcelParseService {

    @Autowired
    ExcelDataMapper mapper;

    public void parseFile(String filePath) {
        try (InputStream in = Files.newInputStream(Paths.get(filePath));
            Workbook workbook = WorkbookFactory.create(in)){
                StreamSupport.stream(workbook.getSheetAt(0).spliterator(),false)
                    .skip(1)
                    .map(rowMapper)
                    .map(bo -> fromSourceToTargetObject(bo, ExcelDataPO::new))
                    .forEach(mapper::insert);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Function<Row, ExcelDataBO> rowMapper =
        row -> {
            final ExcelDataBO data = new ExcelDataBO();
            data.setLineNo(row.getCell(0).getStringCellValue());
            data.setAcc(row.getCell(1).toString());
            data.setName(row.getCell(2).getStringCellValue());
            data.setAmt(BigDecimal.valueOf(row.getCell(3).getNumericCellValue()));
            data.setDesc(row.getCell(4).getStringCellValue());
            return data;};
}
