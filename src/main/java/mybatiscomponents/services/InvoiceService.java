package mybatiscomponents.services;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import mybatiscomponents.beans.*;
import mybatiscomponents.mapper.*;

import javax.annotation.Resource;
import java.util.List;

@ComponentScan({"mybatiscomponents.beans"
        ,"mybatiscomponents.services"
        ,"springbootcomponents.controller"
        ,"springbootcomponents.exceptionhandler"})
@MapperScan("mybatiscomponents.mapper")
@Service("InvoiceService")
public class InvoiceService implements InvoiceIService{

    @Resource
    InvoiceMapper invoiceMapper;

    @Override
    public void insert(Invoice invoice){
        invoiceMapper.insert(invoice);
    }

    public void update(InvoiceUpdate invoice){
        invoiceMapper.update(invoice);
    }

    public List<AllInvoiceInfo> find(Invoice invoice){
        return invoiceMapper.find(invoice);
    }

    public void delete(Invoice invoice){
        invoiceMapper.delete(invoice);
    }

    public List<Invoice> findByElements(Invoice invoiceInfoForSearch){
        return invoiceMapper.findByElements(invoiceInfoForSearch);
    }
}
