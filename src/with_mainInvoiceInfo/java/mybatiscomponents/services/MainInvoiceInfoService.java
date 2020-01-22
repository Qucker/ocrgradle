package mybatiscomponents.services;

import mybatiscomponents.beans.*;
import mybatiscomponents.mapper.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@ComponentScan({"mybatiscomponents.beans"
        ,"mybatiscomponents.services"
        ,"springbootcomponents.controller"
        ,"springbootcomponents.exceptionhandler"})
@MapperScan("mybatiscomponents.mapper")
@Service("MainInvoiceInfoService")
public class MainInvoiceInfoService implements MainInvoiceInfoIService {

    @Resource
    private MainInvoiceInfoMapper mainInvoiceInfoMapper;



    @Override

    /*这种查找方式为复杂筛选查找，输入需要的筛选信息，AllInvoiceInfo
    对象的detail型list只能有一个成员，只按照第一个成员*/
    public List<MainInvoiceInfo> findByElements(Invoice invoiceInfoForSearch){
        return mainInvoiceInfoMapper.findByElements(invoiceInfoForSearch);
    }

}
