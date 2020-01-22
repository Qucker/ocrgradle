package springbootcomponents.controller;
import java.io.*;
import java.util.*;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import mybatiscomponents.beans.*;
import mybatiscomponents.services.*;
import springbootcomponents.exceptionhandler.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.Module;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import aliyunpost.AliyunPost;
import com.baidu.aip.*;
import sun.security.pkcs11.Secmod;


@Controller

@ComponentScan({"mybatiscomponents.beans"
        ,"mybatiscomponents.services"
        ,"springbootcomponents.controller"
        ,"springbootcomponents.exceptionhandler"})
@MapperScan("mybatiscomponents.mapper")
public class SearchController {

    @Resource
    private DetailService detailService;

    @Resource
    private AllInvoiceInfoService allInvoiceInfoService;

    @Resource
    private InvoiceService invoiceService;

    @RequestMapping("/do.search_invoice")
    @ResponseBody
    public String searchinvoice(String invoicenum,
                                String invoicecode,
                                String invoicedate,
                                String checkcode,
                                String totalamount,
                                String purchasertaxid,
                                String purchasername,
                                String purchaseraddrtel,
                                String purchaserbank,
                                String sellertaxid,
                                String sellername,
                                String selleraddrtel,
                                String sellerbank,
                                String detailitems,
                                String detailamountmoney,
                                String detailtaxrate,
                                String detailtax){

        Invoice Info = new Invoice();
        if(invoicenum!=null&&!invoicenum.isEmpty()) {
            Info.setInvoice_Num(invoicenum);
        }
        if(invoicecode!=null&&!invoicecode.isEmpty()) {
            Info.setInvoice_Code(invoicecode);
        }
        if(invoicedate!=null&&!invoicedate.isEmpty()) {
            Info.setInvoice_Date(invoicedate);
        }
        if(checkcode!=null&&!checkcode.isEmpty()) {
            Info.setCheck_Code(checkcode);
        }

        if(totalamount!=null&&!totalamount.isEmpty()) {
            Info.setTotal_Amount(Double.parseDouble(totalamount));
        }
        if(purchasertaxid!=null&&!purchasertaxid.isEmpty()) {
            Info.setPurchaser_Tax_Id(purchasertaxid);
        }
        if(purchasername!=null&&!purchasername.isEmpty()) {
            Info.setPurchaser_Name(purchasername);
        }
        if(purchaseraddrtel!=null&&!purchaseraddrtel.isEmpty()) {
            Info.setPurchaser_Add_Tel(purchaseraddrtel);
        }

        if(purchaserbank!=null&&!purchaserbank.isEmpty()) {
            Info.setPurchaser_Bank(purchaserbank);
        }
        if(sellertaxid!=null&&!sellertaxid.isEmpty()) {
            Info.setSeller_Tax_Id(sellertaxid);
        }
        if(sellername!=null&&!sellername.isEmpty()) {
            Info.setSeller_Name(sellername);
        }
        if(selleraddrtel!=null&&!selleraddrtel.isEmpty()) {
            Info.setSeller_Add_Tel(selleraddrtel);
        }

        if(sellerbank!=null&&!sellerbank.isEmpty()) {
            Info.setSeller_Bank(sellerbank);
        }

        if(detailitems!=null&&!detailitems.isEmpty()) {
            Info.setDetail_Item(detailitems);
        }

        if(detailamountmoney!=null&&!detailamountmoney.isEmpty()) {
            Info.setDetail_Amount(Double.parseDouble(detailamountmoney));
        }

        if(detailtaxrate!=null&&!detailtaxrate.isEmpty()) {
            Info.setDetail_Tax_Rate(Float.parseFloat(detailtaxrate));
        }
        if(detailtax!=null&&!detailtax.isEmpty()) {
            Info.setDetail_Tax(Double.parseDouble(detailtax));
        }

        List<AllInvoiceInfo> result =  allInvoiceInfoService.findByElements(Info);
        JSONArray jsonArray = new JSONArray();
        for(int i=0;i<result.size();i++) {
            JSONObject singleResult=new JSONObject(result.get(i));
            jsonArray=jsonArray.put(singleResult);
        }
        return jsonArray.toString();
    }


    @RequestMapping("/do.search_detail")
    @ResponseBody
    public String searchdetail(String invoicenum,
                               String invoicecode,
                               String invoicedate,
                               String checkcode,
                               String totalamount,
                               String purchasertaxid,
                               String purchasername,
                               String purchaseraddrtel,
                               String purchaserbank,
                               String sellertaxid,
                               String sellername,
                               String selleraddrtel,
                               String sellerbank){
        Invoice Info = new Invoice();
        if(invoicenum!=null&&!invoicenum.isEmpty()) {
            Info.setInvoice_Num(invoicenum);
        }
        if(invoicecode!=null&&!invoicecode.isEmpty()) {
            Info.setInvoice_Code(invoicecode);
        }
        if(invoicedate!=null&&!invoicedate.isEmpty()) {
            Info.setInvoice_Date(invoicedate);
        }
        if(checkcode!=null&&!checkcode.isEmpty()) {
            Info.setCheck_Code(checkcode);
        }
        if(totalamount!=null&&!totalamount.isEmpty()) {
            Info.setTotal_Amount(Double.parseDouble(totalamount));
        }
        if(purchasertaxid!=null&&!purchasertaxid.isEmpty()) {
            Info.setPurchaser_Tax_Id(purchasertaxid);
        }
        if(purchasername!=null&&!purchasername.isEmpty()) {
            Info.setPurchaser_Name(purchasername);
        }
        if(purchaseraddrtel!=null&&!purchaseraddrtel.isEmpty()) {
            Info.setPurchaser_Add_Tel(purchaseraddrtel);
        }
        if(purchaserbank!=null&&!purchaserbank.isEmpty()) {
            Info.setPurchaser_Bank(purchaserbank);
        }
        if(sellertaxid!=null&&!sellertaxid.isEmpty()) {
            Info.setSeller_Tax_Id(sellertaxid);
        }
        if(sellername!=null&&!sellername.isEmpty()) {
            Info.setSeller_Name(sellername);
        }
        if(selleraddrtel!=null&&!selleraddrtel.isEmpty()) {
            Info.setSeller_Add_Tel(selleraddrtel);
        }
        if(sellerbank!=null&&!sellerbank.isEmpty()) {
            Info.setSeller_Bank(sellerbank);
        }


        List<Detail> result =  detailService.findByElements(Info);
        JSONArray jsonArray = new JSONArray();
        for(int i=0;i<result.size();i++) {
            JSONObject singleResult=new JSONObject(result.get(i));
            jsonArray=jsonArray.put(singleResult);
        }
        return jsonArray.toString();
    }

    @RequestMapping("/do.update_invoice")
    @ResponseBody
    public String updateinvoice(String originalinvoicenum,
                                String originalinvoicecode,
                                String originalinvoicedate,
                                String originalcheckcode,
                                String originaltotalamount,
                                String originalpurchasertaxid,
                                String originalpurchasername,
                                String originalpurchaseraddrtel,
                                String originalpurchaserbank,
                                String originalsellertaxid,
                                String originalsellername,
                                String originalselleraddrtel,
                                String originalsellerbank,
                                String originalimagepath,
                                String invoicenum,
                                String invoicecode,
                                String invoicedate,
                                String checkcode,
                                String totalamount,
                                String purchasertaxid,
                                String purchasername,
                                String purchaseraddrtel,
                                String purchaserbank,
                                String sellertaxid,
                                String sellername,
                                String selleraddrtel,
                                String sellerbank){

        InvoiceUpdate invoiceUpdate = new InvoiceUpdate();

        AllInvoiceInfo allInvoiceInfo = new AllInvoiceInfo();

        if(originalinvoicenum!=null&&!originalinvoicenum.isEmpty())
            invoiceUpdate.setOriginalInvoice_Num(originalinvoicenum);

        if(originalinvoicecode!=null&&!originalinvoicecode.isEmpty())
            invoiceUpdate.setOriginalInvoice_Code(originalinvoicecode);

        if(originalinvoicedate!=null&&!originalinvoicedate.isEmpty())
            invoiceUpdate.setOriginalInvoice_Date(originalinvoicedate);

        if(originalcheckcode!=null&&!originalcheckcode.isEmpty())
            invoiceUpdate.setOriginalCheck_Code(originalcheckcode);

        if(originaltotalamount!=null&&!originaltotalamount.isEmpty())
            invoiceUpdate.setOriginalTotal_Amount(Double.parseDouble(originaltotalamount));

        if(originalpurchasertaxid!=null&&!originalpurchasertaxid.isEmpty())
            invoiceUpdate.setOriginalPurchaser_Tax_Id(originalpurchasertaxid);

        if(originalpurchasername!=null&&!originalpurchasername.isEmpty())
            invoiceUpdate.setOriginalPurchaser_Name(originalpurchasername);

        if(originalpurchaseraddrtel!=null&&!originalpurchaseraddrtel.isEmpty())
            invoiceUpdate.setOriginalPurchaser_Add_Tel(originalpurchaseraddrtel);

        if(originalpurchaserbank!=null&&!originalpurchaserbank.isEmpty())
            invoiceUpdate.setOriginalPurchaser_Bank(originalpurchaserbank);

        if(originalsellertaxid!=null&&!originalsellertaxid.isEmpty())
            invoiceUpdate.setOriginalSeller_Tax_Id(originalsellertaxid);

        if(originalsellername!=null&&!originalsellername.isEmpty())
            invoiceUpdate.setOriginalSeller_Name(originalsellername);

        if(originalselleraddrtel!=null&&!originalselleraddrtel.isEmpty())
            invoiceUpdate.setOriginalSeller_Add_Tel(originalselleraddrtel);

        if(originalsellerbank!=null&&!originalsellerbank.isEmpty())
            invoiceUpdate.setOriginalSeller_Bank(originalsellerbank);

        if(originalimagepath!=null&&!originalimagepath.isEmpty())
            invoiceUpdate.setOriginalImage_Path(originalimagepath);


        if(invoicenum!=null&&!invoicenum.isEmpty())
            invoiceUpdate.setInvoice_Num(invoicenum);

        if(invoicecode!=null&&!invoicecode.isEmpty())
            invoiceUpdate.setInvoice_Code(invoicecode);

        if(invoicedate!=null&&!invoicedate.isEmpty())
            invoiceUpdate.setInvoice_Date(invoicedate);

        if(checkcode!=null&&!checkcode.isEmpty())
            invoiceUpdate.setCheck_Code(checkcode);

        if(totalamount!=null&&!totalamount.isEmpty())
            invoiceUpdate.setTotal_Amount(Double.parseDouble(totalamount));

        if(purchasertaxid!=null&&!purchasertaxid.isEmpty())
            invoiceUpdate.setPurchaser_Tax_Id(purchasertaxid);

        if(purchasername!=null&&!purchasername.isEmpty())
            invoiceUpdate.setPurchaser_Name(purchasername);

        if(purchaseraddrtel!=null&&!purchaseraddrtel.isEmpty())
            invoiceUpdate.setPurchaser_Add_Tel(purchaseraddrtel);

        if(purchaserbank!=null&&!purchaserbank.isEmpty())
            invoiceUpdate.setPurchaser_Bank(purchaserbank);

        if(sellertaxid!=null&&!sellertaxid.isEmpty())
            invoiceUpdate.setSeller_Tax_Id(sellertaxid);

        if(sellername!=null&&!sellername.isEmpty())
            invoiceUpdate.setSeller_Name(sellername);

        if(selleraddrtel!=null&&!selleraddrtel.isEmpty())
            invoiceUpdate.setSeller_Add_Tel(selleraddrtel);

        if(sellerbank!=null&&!sellerbank.isEmpty())
            invoiceUpdate.setSeller_Bank(sellerbank);

        invoiceService.update(invoiceUpdate);



        Invoice Info = new Invoice();
        if(invoicenum!=null&&!invoicenum.isEmpty())
            Info.setInvoice_Num(invoicenum);

        if(invoicecode!=null&&!invoicecode.isEmpty())
        Info.setInvoice_Code(invoicecode);

        if(invoicedate!=null&&!invoicedate.isEmpty())
        Info.setInvoice_Date(invoicedate);

        if(checkcode!=null&&!checkcode.isEmpty())
        Info.setCheck_Code(checkcode);

        if(totalamount!=null&&!totalamount.isEmpty())
        Info.setTotal_Amount(Double.parseDouble(totalamount));

        if(purchasertaxid!=null&&!purchasertaxid.isEmpty())
        Info.setPurchaser_Tax_Id(purchasertaxid);

        if(purchasername!=null&&!purchasername.isEmpty())
        Info.setPurchaser_Name(purchasername);

        if(purchaseraddrtel!=null&&!purchaseraddrtel.isEmpty())
        Info.setPurchaser_Add_Tel(purchaseraddrtel);

        if(purchaserbank!=null&&!purchaserbank.isEmpty())
        Info.setPurchaser_Bank(purchaserbank);

        if(sellertaxid!=null&&!sellertaxid.isEmpty())
        Info.setSeller_Tax_Id(sellertaxid);

        if(sellername!=null&&!sellername.isEmpty())
        Info.setSeller_Name(sellername);

        if(selleraddrtel!=null&&!selleraddrtel.isEmpty())
        Info.setSeller_Add_Tel(selleraddrtel);

        if(sellerbank!=null&&!sellerbank.isEmpty())
        Info.setSeller_Bank(sellerbank);

        if(originalimagepath!=null&&!originalimagepath.isEmpty())
        Info.setImage_Path(originalimagepath);

        List<AllInvoiceInfo> result =  invoiceService.find(Info);
        //System.out.print(result.size());
        JSONArray jsonArray = new JSONArray();
        for(int i=0;i<result.size();i++) {
            JSONObject singleResult=new JSONObject(result.get(i));
            jsonArray=jsonArray.put(singleResult);
        }
        return jsonArray.toString();
    }

    @RequestMapping("/do.update_detail")
    @ResponseBody
    public String updatedetail(String invoicenum,
                               String invoicecode,
                               String invoicedate,
							   String checkcode,
							   String totalamount,
							   String purchasertaxid,
							   String purchasername,
							   String purchaseraddrtel,
							   String purchaserbank,
							   String sellertaxid,
							   String sellername,
							   String selleraddrtel,
							   String sellerbank,
							   String imagepath,
							   String originaldetailitem,
							   String originaldetailamount,
							   String originaldetailtaxrate,
							   String originaldetailtax,
							   String detailitem,
							   String detailamount,
							   String detailtaxrate,
							   String detailtax){

        DetailUpdate detailUpdate = new DetailUpdate();


        if(invoicenum!=null&&!invoicenum.isEmpty())
            detailUpdate.setInvoice_Num(invoicenum);

        if(invoicecode!=null&&!invoicecode.isEmpty())
            detailUpdate.setInvoice_Code(invoicecode);

        if(invoicedate!=null&&!invoicedate.isEmpty())
            detailUpdate.setInvoice_Date(invoicedate);

        if(checkcode!=null&&!checkcode.isEmpty())
            detailUpdate.setCheck_Code(checkcode);

        if(totalamount!=null&&!totalamount.isEmpty())
            detailUpdate.setTotal_Amount(Double.parseDouble(totalamount));

        if(purchasertaxid!=null&&!purchasertaxid.isEmpty())
            detailUpdate.setPurchaser_Tax_Id(purchasertaxid);

        if(purchasername!=null&&!purchasername.isEmpty())
            detailUpdate.setPurchaser_Name(purchasername);

        if(purchaseraddrtel!=null&&!purchaseraddrtel.isEmpty())
            detailUpdate.setPurchaser_Add_Tel(purchaseraddrtel);

        if(purchaserbank!=null&&!purchaserbank.isEmpty())
            detailUpdate.setPurchaser_Bank(purchaserbank);

        if(sellertaxid!=null&&!sellertaxid.isEmpty())
            detailUpdate.setSeller_Tax_Id(sellertaxid);

        if(sellername!=null&&!sellername.isEmpty())
            detailUpdate.setSeller_Name(sellername);

        if(selleraddrtel!=null&&!selleraddrtel.isEmpty())
            detailUpdate.setSeller_Add_Tel(selleraddrtel);

        if(sellerbank!=null&&!sellerbank.isEmpty())
            detailUpdate.setSeller_Bank(sellerbank);

        if(imagepath!=null&&!imagepath.isEmpty())
            detailUpdate.setImage_Path(imagepath);



        if(originaldetailitem!=null&&!originaldetailitem.isEmpty())
            detailUpdate.setOriginalDetail_Item(originaldetailitem);

        if(originaldetailamount!=null&&!originaldetailamount.isEmpty())
            detailUpdate.setOriginalDetail_Amount(originaldetailamount);

        if(originaldetailtaxrate!=null&&!originaldetailtaxrate.isEmpty())
            detailUpdate.setOriginalDetail_Taxrate(originaldetailtaxrate);

        if(originaldetailtax!=null&&!originaldetailtax.isEmpty())
            detailUpdate.setOriginalDetail_Tax(originaldetailtax);



        if(detailitem!=null&&!detailitem.isEmpty())
            detailUpdate.setDetail_Item(detailitem);

        if(detailamount!=null&&!detailamount.isEmpty())
            detailUpdate.setDetail_Amount(detailamount);

        if(detailtaxrate!=null&&!detailtaxrate.isEmpty())
            detailUpdate.setDetail_Taxrate(detailtaxrate);

        if(detailtax!=null&&!detailtax.isEmpty())
            detailUpdate.setDetail_Tax(detailtax);



        detailService.update(detailUpdate);



        Invoice Info = new Invoice();
        if(invoicenum!=null&&!invoicenum.isEmpty())
            Info.setInvoice_Num(invoicenum);

        if(invoicecode!=null&&!invoicecode.isEmpty())
            Info.setInvoice_Code(invoicecode);

        if(invoicedate!=null&&!invoicedate.isEmpty())
            Info.setInvoice_Date(invoicedate);

        if(checkcode!=null&&!checkcode.isEmpty())
            Info.setCheck_Code(checkcode);

        if(totalamount!=null&&!totalamount.isEmpty())
            Info.setTotal_Amount(Double.parseDouble(totalamount));

        if(purchasertaxid!=null&&!purchasertaxid.isEmpty())
            Info.setPurchaser_Tax_Id(purchasertaxid);

        if(purchasername!=null&&!purchasername.isEmpty())
            Info.setPurchaser_Name(purchasername);

        if(purchaseraddrtel!=null&&!purchaseraddrtel.isEmpty())
            Info.setPurchaser_Add_Tel(purchaseraddrtel);

        if(purchaserbank!=null&&!purchaserbank.isEmpty())
            Info.setPurchaser_Bank(purchaserbank);

        if(sellertaxid!=null&&!sellertaxid.isEmpty())
            Info.setSeller_Tax_Id(sellertaxid);

        if(sellername!=null&&!sellername.isEmpty())
            Info.setSeller_Name(sellername);

        if(selleraddrtel!=null&&!selleraddrtel.isEmpty())
            Info.setSeller_Add_Tel(selleraddrtel);

        if(sellerbank!=null&&!sellerbank.isEmpty())
            Info.setSeller_Bank(sellerbank);

        if(imagepath!=null&&!imagepath.isEmpty())
            Info.setImage_Path(imagepath);


        if(detailitem!=null&&!detailitem.isEmpty())
            Info.setDetail_Item(detailitem);

        if(detailamount!=null&&!detailamount.isEmpty())
            Info.setDetail_Amount(Double.parseDouble(detailamount));

        if(detailtaxrate!=null&&!detailtaxrate.isEmpty())
            Info.setDetail_Tax_Rate(Float.parseFloat(detailtaxrate));

        if(detailtax!=null&&!detailtax.isEmpty())
            Info.setDetail_Tax(Double.parseDouble(detailtax));

        List<Detail> result =  detailService.findByElements(Info);
        JSONArray jsonArray = new JSONArray();
        for(int i=0;i<result.size();i++) {
            JSONObject singleResult=new JSONObject(result.get(i));
            jsonArray=jsonArray.put(singleResult);
        }
        return jsonArray.toString();
    }

    @RequestMapping("/do.delete_invoice")
    @ResponseBody
    public String deleteinvoice(String invoicenum,
                                String invoicecode,
								String invoicedate,
								String checkcode,
								String totalamount,
								String purchasertaxid,
								String purchasername,
								String purchaseraddrtel,
								String purchaserbank,
								String sellertaxid,
								String sellername,
								String selleraddrtel,
								String sellerbank,
								String imagepath){

        Invoice invoicedelete = new Invoice();

        if(invoicenum!=null&&!invoicenum.isEmpty())
            invoicedelete.setInvoice_Num(invoicenum);

        if(invoicecode!=null&&!invoicecode.isEmpty())
            invoicedelete.setInvoice_Code(invoicecode);

        if(invoicedate!=null&&!invoicedate.isEmpty())
            invoicedelete.setInvoice_Date(invoicedate);

        if(checkcode!=null&&!checkcode.isEmpty())
            invoicedelete.setCheck_Code(checkcode);

        if(totalamount!=null&&!totalamount.isEmpty())
            invoicedelete.setTotal_Amount(Double.parseDouble(totalamount));

        if(purchasertaxid!=null&&!purchasertaxid.isEmpty())
            invoicedelete.setPurchaser_Tax_Id(purchasertaxid);

        if(purchasername!=null&&!purchasername.isEmpty())
            invoicedelete.setPurchaser_Name(purchasername);

        if(purchaseraddrtel!=null&&!purchaseraddrtel.isEmpty())
            invoicedelete.setPurchaser_Add_Tel(purchaseraddrtel);

        if(purchaserbank!=null&&!purchaserbank.isEmpty())
            invoicedelete.setPurchaser_Bank(purchaserbank);

        if(sellertaxid!=null&&!sellertaxid.isEmpty())
            invoicedelete.setSeller_Tax_Id(sellertaxid);

        if(sellername!=null&&!sellername.isEmpty())
            invoicedelete.setSeller_Name(sellername);

        if(selleraddrtel!=null&&!selleraddrtel.isEmpty())
            invoicedelete.setSeller_Add_Tel(selleraddrtel);

        if(sellerbank!=null&&!sellerbank.isEmpty())
            invoicedelete.setSeller_Bank(sellerbank);

        if(imagepath!=null&&!imagepath.isEmpty())
            invoicedelete.setImage_Path(imagepath);

        invoiceService.delete(invoicedelete);

        List<AllInvoiceInfo> result =  invoiceService.find(invoicedelete);
        //System.out.print(result.size());
        if(result.size()==0){
            return "{\"status\":\"success\"}";
        }
        else{
            return "{\"status\":\"failure\"}";
        }

    }

    @RequestMapping("/do.delete_detail")
    @ResponseBody
    public String deletedetail(String invoicenum,
								String invoicecode,
								String invoicedate,
								String checkcode,
								String totalamount,
								String purchasertaxid,
								String purchasername,
								String purchaseraddrtel,
								String purchaserbank,
								String sellertaxid,
								String sellername,
								String selleraddrtel,
								String sellerbank,
								String imagepath,
								String detailitem,
								String detailamount,
								String detailtaxrate,
								String detailtax){

        Invoice invoicedelete = new Invoice();

        if(invoicenum!=null&&!invoicenum.isEmpty())
            invoicedelete.setInvoice_Num(invoicenum);

        if(invoicecode!=null&&!invoicecode.isEmpty())
            invoicedelete.setInvoice_Code(invoicecode);

        if(invoicedate!=null&&!invoicedate.isEmpty())
            invoicedelete.setInvoice_Date(invoicedate);

        if(checkcode!=null&&!checkcode.isEmpty())
            invoicedelete.setCheck_Code(checkcode);

        if(totalamount!=null&&!totalamount.isEmpty())
            invoicedelete.setTotal_Amount(Double.parseDouble(totalamount));

        if(purchasertaxid!=null&&!purchasertaxid.isEmpty())
            invoicedelete.setPurchaser_Tax_Id(purchasertaxid);

        if(purchasername!=null&&!purchasername.isEmpty())
            invoicedelete.setPurchaser_Name(purchasername);

        if(purchaseraddrtel!=null&&!purchaseraddrtel.isEmpty())
            invoicedelete.setPurchaser_Add_Tel(purchaseraddrtel);

        if(purchaserbank!=null&&!purchaserbank.isEmpty())
            invoicedelete.setPurchaser_Bank(purchaserbank);

        if(sellertaxid!=null&&!sellertaxid.isEmpty())
            invoicedelete.setSeller_Tax_Id(sellertaxid);

        if(sellername!=null&&!sellername.isEmpty())
            invoicedelete.setSeller_Name(sellername);

        if(selleraddrtel!=null&&!selleraddrtel.isEmpty())
            invoicedelete.setSeller_Add_Tel(selleraddrtel);

        if(sellerbank!=null&&!sellerbank.isEmpty())
            invoicedelete.setSeller_Bank(sellerbank);

        if(imagepath!=null&&!imagepath.isEmpty())
            invoicedelete.setImage_Path(imagepath);


        if(detailitem!=null&&!detailitem.isEmpty())
            invoicedelete.setDetail_Item(detailitem);

        if(detailamount!=null&&!detailamount.isEmpty())
            invoicedelete.setDetail_Amount(Double.parseDouble(detailamount));

        if(detailtaxrate!=null&&!detailtaxrate.isEmpty())
            invoicedelete.setDetail_Tax_Rate(Float.parseFloat(detailtaxrate));

        if(detailtax!=null&&!detailtax.isEmpty())
            invoicedelete.setDetail_Tax(Double.parseDouble(detailtax));



        detailService.delete(invoicedelete);

        List<Detail> result =  detailService.find(invoicedelete);
        //System.out.print(result.size());
        if(result.size()==0){
            return "{\"status\":\"success\"}";
        }
        else{
            return "{\"status\":\"failure\"}";
        }

    }
}
