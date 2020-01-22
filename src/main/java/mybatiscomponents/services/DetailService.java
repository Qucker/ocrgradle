package mybatiscomponents.services;

import mybatiscomponents.mapper.*;
import mybatiscomponents.beans.*;
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
@Service("DetailService")
public class DetailService implements DetailIService{

    @Resource
    public DetailMapper detailMapper;

    @Override
    public void delete(Invoice invoice){
        detailMapper.delete(invoice);
    }

    @Override
    public void update(DetailUpdate detailUpdate){
        detailMapper.update(detailUpdate);
    }

    @Override
    public List<Detail> findByElements(Invoice invoice){
        return detailMapper.findByElements(invoice);
    }

    @Override
    public List<Detail> find(Invoice mainInvoiceInfo){
        return detailMapper.find(mainInvoiceInfo);
    }
}
