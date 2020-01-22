package mybatiscomponents.services;

import org.json.JSONObject;
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
    public void insert(JSONObject input,String path){

        Invoice invoiceinsert = new Invoice();

        invoiceinsert.setImage_Path(path);

        if(input.getJSONObject("words_result").has("InvoiceCode")&&
                !input.getJSONObject("words_result")
                        .getString("InvoiceCode").isEmpty())
            invoiceinsert.setInvoice_Code(
                    input.getJSONObject("words_result")
                            .getString("InvoiceCode"));

        if(input.getJSONObject("words_result").has("InvoiceNum")&&
                !input.getJSONObject("words_result")
                        .getString("InvoiceNum").isEmpty())
            invoiceinsert.setInvoice_Num(
                    input.getJSONObject("words_result")
                            .getString("InvoiceNum"));

        if(input.getJSONObject("words_result").has("InvoiceDate")&&
                !input.getJSONObject("words_result")
                        .getString("InvoiceDate").isEmpty()) {

            String invoicedate = input.getJSONObject("words_result")
                    .getString("InvoiceDate");

            if (invoicedate != null && invoicedate.length() != 0) {
                String[] splited1 = invoicedate.split("年");
                String year = splited1[0];
                String[] splited2 = splited1[1].split("月");
                String month = splited2[0].length() == 1 ? ("0" + splited2[0]) : splited2[0];
                String day = splited2[1].replace("日", "").length() == 1 ? ("0" + splited2[1].replace("日", "")) : splited2[1].replace("日", "");

                invoiceinsert.setInvoice_Date(year + month + day);
            }
        }

        if(input.getJSONObject("words_result").has("CheckCode")&&
                !input.getJSONObject("words_result")
                        .getString("CheckCode").isEmpty())
            invoiceinsert.setCheck_Code(
                    input.getJSONObject("words_result")
                            .getString("CheckCode"));

        if(input.getJSONObject("words_result").has("PurchaserName")&&
                !input.getJSONObject("words_result")
                        .getString("PurchaserName").isEmpty())
            invoiceinsert.setPurchaser_Name(
                    input.getJSONObject("words_result")
                            .getString("PurchaserName"));

        if(input.getJSONObject("words_result").has("PurchaserRegisterNum")&&
                !input.getJSONObject("words_result")
                        .getString("PurchaserRegisterNum").isEmpty())
            invoiceinsert.setPurchaser_Tax_Id(
                    input.getJSONObject("words_result")
                            .getString("PurchaserRegisterNum"));

        if(input.getJSONObject("words_result").has("PurchaserAddress")&&
                !input.getJSONObject("words_result")
                        .getString("PurchaserAddress").isEmpty())
            invoiceinsert.setPurchaser_Add_Tel(
                    input.getJSONObject("words_result")
                            .getString("PurchaserAddress"));

        if(input.getJSONObject("words_result").has("PurchaserBank")&&
                !input.getJSONObject("words_result")
                        .getString("PurchaserBank").isEmpty())
            invoiceinsert.setPurchaser_Bank(
                    input.getJSONObject("words_result")
                            .getString("PurchaserBank"));

        if(input.getJSONObject("words_result").has("SellerName")&&
                !input.getJSONObject("words_result")
                        .getString("SellerName").isEmpty())
            invoiceinsert.setSeller_Name(
                    input.getJSONObject("words_result")
                            .getString("SellerName"));

        if(input.getJSONObject("words_result").has("SellerRegisterNum")&&
                !input.getJSONObject("words_result")
                        .getString("SellerRegisterNum").isEmpty())
            invoiceinsert.setSeller_Tax_Id(
                    input.getJSONObject("words_result")
                            .getString("SellerRegisterNum"));

        if(input.getJSONObject("words_result").has("SellerAddress")&&
                !input.getJSONObject("words_result")
                        .getString("SellerAddress").isEmpty())
            invoiceinsert.setSeller_Add_Tel(
                    input.getJSONObject("words_result")
                            .getString("SellerAddress"));

        if(input.getJSONObject("words_result").has("SellerBank")&&
                !input.getJSONObject("words_result")
                        .getString("SellerBank").isEmpty())
            invoiceinsert.setSeller_Bank(
                    input.getJSONObject("words_result")
                            .getString("SellerBank"));

        if(input.getJSONObject("words_result").has("AmountInFiguers")&&
                !input.getJSONObject("words_result")
                        .getString("AmountInFiguers").isEmpty())
            invoiceinsert.setTotal_Amount(Double.parseDouble(
                    input.getJSONObject("words_result")
                            .getString("AmountInFiguers"))
            );

        if(input.getJSONObject("words_result").has("CommodityName")
        && input.getJSONObject("words_result").has("CommodityAmount")
        && input.getJSONObject("words_result").has("CommodityTaxRate")
        && input.getJSONObject("words_result").has("CommodityTax")
        && input.getJSONObject("words_result").getJSONArray("CommodityName").length()
        == input.getJSONObject("words_result").getJSONArray("CommodityAmount").length()
        && input.getJSONObject("words_result").getJSONArray("CommodityAmount").length()
        == input.getJSONObject("words_result").getJSONArray("CommodityTaxRate").length()
        && input.getJSONObject("words_result").getJSONArray("CommodityTaxRate").length()
        == input.getJSONObject("words_result").getJSONArray("CommodityTax").length()
           )
        {
            int length = input.getJSONObject("words_result").getJSONArray("CommodityTax").length();
            for(int i=0;i<length;i++){

                if(input.getJSONObject("words_result").has("CommodityName")&&
                !input.getJSONObject("words_result")
                .getJSONArray("CommodityName")
                .getJSONObject(i).getString("word").isEmpty()) {

                    invoiceinsert.setDetail_Item(input.getJSONObject("words_result")
                    .getJSONArray("CommodityName")
                    .getJSONObject(i).getString("word"));

                }

                if(input.getJSONObject("words_result").has("CommodityAmount")&&
                !input.getJSONObject("words_result")
                .getJSONArray("CommodityAmount")
                .getJSONObject(i).getString("word").isEmpty()) {

                    invoiceinsert.setDetail_Amount(
                            Double.parseDouble(
                                    input.getJSONObject(
                                            "words_result"
                                    ).getJSONArray(
                                            "CommodityAmount"
                                    ).getJSONObject(i).getString("word")
                            )
                    );
                }

                if(input.getJSONObject("words_result").has("CommodityTaxRate")&&
                !input.getJSONObject("words_result")
                .getJSONArray("CommodityTaxRate")
                .getJSONObject(i).getString("word").replace("%","").isEmpty()) {

                    invoiceinsert.setDetail_Tax_Rate(
                            Float.parseFloat(
                                    input.getJSONObject(
                                            "words_result"
                                    ).getJSONArray(
                                            "CommodityTaxRate"
                                    ).getJSONObject(i).getString("word").replace("%","")
                            )
                    );
                }


                if(input.getJSONObject("words_result").has("CommodityTax")&&
                        !input.getJSONObject("words_result")
                                .getJSONArray("CommodityTax")
                                .getJSONObject(i).getString("word").isEmpty()) {

                    invoiceinsert.setDetail_Tax(
                            Float.parseFloat(
                                    input.getJSONObject(
                                            "words_result"
                                    ).getJSONArray(
                                            "CommodityTax"
                                    ).getJSONObject(i).getString("word")
                            )
                    );
                }

                invoiceMapper.insert(invoiceinsert);
            }
        }


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
