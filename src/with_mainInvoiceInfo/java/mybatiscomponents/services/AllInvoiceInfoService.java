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
@Service("AllInvoiceInfoService")
public class AllInvoiceInfoService implements AllInvoiceInfoIService {

    @Resource
    private AllInvoiceInfoMapper allInvoiceInfoMapper;

    @Resource
    private InvoiceMapper invoiceMapper;


    @Override
    /*插入需要全部信息，如果不存在taxpayer，则会自动插入新taxpayer，
    如果存在taxpayer，则不会自动插入，同时检查其他几列是不是空，如果
    为空就更改为插入的值，否则不更改*/
    public void insert(AllInvoiceInfo allInvoiceInfo){

        /*if(findByElements(allInvoiceInfo
                        .getPurchaser()
                        .getTaxpayer_Tax_Id()).isEmpty()){
            taxpayerMapper.insert(allInvoiceInfo
                    .getPurchaser());
        }

        if(taxpayerMapper.find(allInvoiceInfo
                        .getSeller()
                        .getTaxpayer_Tax_Id()).isEmpty()){
            taxpayerMapper.insert(allInvoiceInfo
                    .getSeller());
        }

        Invoice invoiceinsert = new Invoice(allInvoiceInfo.getInvoice_Code(),
                allInvoiceInfo.getInvoice_Num(),
                allInvoiceInfo.getInvoice_Data(),
                allInvoiceInfo.getCheck_Code(),
                allInvoiceInfo.getTotal_Amount(),
                allInvoiceInfo.getInvoice_Password(),
                allInvoiceInfo.getImage_Path(),
                allInvoiceInfo.getPurchaser().getTaxpayer_Tax_Id(),
                allInvoiceInfo.getSeller().getTaxpayer_Tax_Id()
        );

        invoiceMapper.insert(
                new Invoice(allInvoiceInfo.getInvoice_Code(),
                        allInvoiceInfo.getInvoice_Num(),
                        allInvoiceInfo.getInvoice_Data(),
                        allInvoiceInfo.getCheck_Code(),
                        allInvoiceInfo.getTotal_Amount(),
                        allInvoiceInfo.getInvoice_Password(),
                        allInvoiceInfo.getImage_Path(),
                        allInvoiceInfo.getPurchaser().getTaxpayer_Tax_Id(),
                        allInvoiceInfo.getSeller().getTaxpayer_Tax_Id()
                        ));


        for(int i=0;allInvoiceInfo.getDetails()!=null&&i<allInvoiceInfo.getDetails().size();i++){
            detailMapper.insert(allInvoiceInfo.getDetails().get(i));
        }*/
        allInvoiceInfoMapper.insert(allInvoiceInfo);

    }

    /*删除只需要指定主码，即发票号和发票代码。先删除这张表
    的所有明细，然后检查是否有其他发票表依赖当前要删除
    的发票的纳税人，如果没有其他依赖就先删发票再删纳税人，
    否则只删发票，不删纳税人*/
    public void delete(AllInvoiceInfo allInvoiceInfo){
        allInvoiceInfoMapper.delete(allInvoiceInfo);
    }
    /*用户一次提交统一更改三张表，顺序为纳税人表，发票表，
    明细表，更改方式全部为如果存在更新，如果不存在插入。*/
    public void update(AllInvoiceInfo allInvoiceInfo){
        allInvoiceInfoMapper.update(allInvoiceInfo);
    }

    //这种查找方式为简单查找，只需要输入主码
    public List<AllInvoiceInfo> find(AllInvoiceInfo allInvoiceInfo){
        return allInvoiceInfoMapper.find(allInvoiceInfo);
    }

    /*这种查找方式为复杂筛选查找，输入需要的筛选信息，AllInvoiceInfo
    对象的detail型list只能有一个成员，只按照第一个成员*/
    public List<AllInvoiceInfo> findByElements(Invoice invoiceInfoForSearch){
        return allInvoiceInfoMapper.findByElements(invoiceInfoForSearch);
    }

}
