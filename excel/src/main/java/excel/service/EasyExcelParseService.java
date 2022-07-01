package excel.service;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.read.listener.PageReadListener;
import excel.domain.TestData;

/**
 * author : liuanglin
 * date : 2022/7/1 15:31
 * description : 使用 easy Excel 解析文件
 */
public class EasyExcelParseService {

    public static void parseFile(String filePath) {
        EasyExcelFactory.read(filePath, TestData.class,
            new PageReadListener<TestData>(
                testDataList -> {
                    System.out.println(testDataList.size());
                    testDataList.forEach(System.out::println);
                }))
            .sheet().doReadSync();
    }
}
