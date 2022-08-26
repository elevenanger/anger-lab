package excel.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import excel.data.ExcelDataPO;
import excel.data.ExcelDataService;
import excel.domain.ExcelDataBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static objects.ObjectTransformer.batchTrans;

/**
 * author : liuanglin
 * date : 2022/7/1 15:31
 * description : 使用 easy Excel 解析文件
 */
@Service
public class EasyExcelParseService {

    @Autowired
    ExcelDataService excelDataService;

    public void parseFile(String filePath) {
        EasyExcelFactory.read(filePath, ExcelDataBO.class,
            new DemoPageListener<> (
                1000, excelProcessTask)
            )
            .sheet()
            .doRead();
    }

    public void parseFileRecursive(String filePath) {
        EasyExcelFactory.read(filePath, ExcelDataBO.class,
            new ForkJoinListener<> (1000, excelProcessTask))
            .sheet()
            .doRead();
    }

    public void write_excel(String fileName, Supplier<Collection<?>> data) {
        try (ExcelWriter writer =
                 EasyExcel.write(fileName, ExcelDataBO.class)
                     .excelType(ExcelTypeEnum.XLSX)
                     .build()){
            WriteSheet writeSheet = EasyExcel.writerSheet("test").build();
            for (int i = 0; i < 100; i++) {
                writer.write(data.get(), writeSheet);
            }
        }
    }

     Consumer<List<ExcelDataBO>> excelProcessTask =
        excelData ->
            excelDataService.saveBatch(
                batchTrans(excelData, ExcelDataPO::new), 100);
}
