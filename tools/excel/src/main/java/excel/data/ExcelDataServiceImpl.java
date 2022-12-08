package excel.data;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service("excelService")
public class ExcelDataServiceImpl extends ServiceImpl<ExcelDataMapper, ExcelDataPO> implements ExcelDataService{

}