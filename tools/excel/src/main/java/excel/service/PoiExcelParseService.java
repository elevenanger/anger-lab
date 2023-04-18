package excel.service;

import excel.data.ExcelDataPO;
import excel.data.ExcelDataService;
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
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static cn.anger.util.objects.ObjectTransformer.fromSourceToTargetObject;

/**
 * author : anger
 * date : 2022/7/1 14:56
 * description : 使用 Poi 库进行 Excel 文件解析
 */
@Slf4j
@Service
public class PoiExcelParseService {

    @Autowired
    ExcelDataService service;

    public void parseFile(String filePath) {
        try (InputStream in = Files.newInputStream(Paths.get(filePath));
            Workbook workbook = WorkbookFactory.create(in)){
                service.saveBatch(
                    StreamSupport.stream(workbook.getSheetAt(0).spliterator(),false)
                        .skip(1)
                        .map(rowMapper)
                        .map(bo -> fromSourceToTargetObject(bo, ExcelDataPO::new))
                        .collect(Collectors.toList()));
        } catch (IOException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
            Thread.currentThread().interrupt();
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
            return data;
    };
}
