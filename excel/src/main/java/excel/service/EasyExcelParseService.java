package excel.service;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.read.listener.PageReadListener;
import excel.data.ExcelDataPO;
import excel.data.ExcelDataRepository;
import excel.domain.ExcelDataBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static objects.ObjectTransformer.*;

/**
 * author : liuanglin
 * date : 2022/7/1 15:31
 * description : 使用 easy Excel 解析文件
 */

@Service
public class EasyExcelParseService {

    @Autowired
    ExcelDataRepository repository;

    public void parseFile(String filePath) {
        EasyExcelFactory.read(filePath, ExcelDataBO.class,
            new PageReadListener<ExcelDataBO>(
                excelDataBOS ->
                    repository.saveAll(
                        batchTrans(excelDataBOS, ExcelDataPO::new)))).sheet().doRead();
    }

}
