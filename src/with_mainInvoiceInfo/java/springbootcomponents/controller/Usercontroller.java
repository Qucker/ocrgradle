package springbootcomponents.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
import sun.security.pkcs11.Secmod;


@Controller

@ComponentScan({"mybatiscomponents.beans"
        ,"mybatiscomponents.services"
        ,"springbootcomponents.controller"
        ,"springbootcomponents.exceptionhandler"})
@MapperScan("mybatiscomponents.mapper")

public class Usercontroller {

    private boolean postHasInitialized=false;

    @Resource
    private DetailService detailService;

    @Resource
    private AllInvoiceInfoService allInvoiceInfoService;

    @Resource
    private MainInvoiceInfoService mainInvoiceInfoService;

    @Resource
    private InvoiceService invoiceService;
    @RequestMapping("/upload")
    public String say(Model module){
        module.addAttribute("name","asd");
        return "uploader.html";
    }

    /*
    @RequestMapping("/insert")
    @ResponseBody
    public String insert(){

    }*/

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
        //jsonResult.put("rows",jsonArray);
        //jsonResult.put("total",jsonArray.length());
        //ModelAndView mav = new ModelAndView();
        //mav.addObject("user","sadf");
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
        MainInvoiceInfo Info = new MainInvoiceInfo();
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
        //jsonResult.put("rows",jsonArray);
        //jsonResult.put("total",jsonArray.length());
        //ModelAndView mav = new ModelAndView();
        //mav.addObject("user","sadf");
        return jsonArray.toString();
    }

    //@SuppressWarnings("resource")

    @RequestMapping("/do.uploader")
    @ResponseBody
    public Map<String, Object> upload(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setCharacterEncoding("UTF-8");
        Map<String, Object> json = new HashMap<String, Object>();
        Enumeration<String> headerNames = request.getHeaderNames();
        boolean headerHascontype = false;
        while(headerNames.hasMoreElements()){
            String value = (String)headerNames.nextElement();
            if(value.contains("content-type")){
                headerHascontype=true;
                break;
            }
        }

        if((!headerHascontype)||(!request.getHeader("content-type").contains("multipart/form-data"))){
            json.put("message","不能直接访问");
            return json;
        }
        else{
            if(!postHasInitialized){
                AliyunPost.InitUrl();
                postHasInitialized=true;
            }


            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            /** 页面控件的文件流* */
            MultipartFile multipartFile = null;
            Map map =multipartRequest.getFileMap();
            Iterator i = map.keySet().iterator();
            Object obj = i.next();
            multipartFile=(MultipartFile) map.get(obj);

            /** 获取文件的后缀* */
            String filename = multipartFile.getOriginalFilename();
            InputStream inputStream;
            //String basePath=request.getSession().getServletContext().getRealPath("/").substring(0, request.getSession().getServletContext().getRealPath("/").lastIndexOf("bspheis-wechat"));
            String outPath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
            //System.out.print(basePath);
            String newVersionName = "";
            String fileMd5 = "";
            byte[] data = new byte[1024];
            int len = 0;
            FileOutputStream fileOutputStream = null;
            try {
                inputStream = multipartFile.getInputStream();
                /*if(1==1) {
                    throw new DuplicatedException("12345","34567");
                }*/

                String stringResult = AliyunPost.postReturnString(inputStream);

                JSONObject result = new JSONObject(stringResult);

                //System.out.print(result);

                if(!result.has("data")){
                    throw new OCRFailureException(stringResult);
                }

                if(result.getJSONObject("data")
                        .getString("发票代码")==null||
                        result.getJSONObject("data")
                                .getString("发票代码").isEmpty()||
                        result.getJSONObject("data")
                                .getString("发票号码")==null||
                        result.getJSONObject("data")
                                .getString("发票号码").isEmpty()){
                    throw new OCRFailureException("未能识别发票代码和发票号码等基本信息");
                }

                Invoice invoiceForSearch = new Invoice();

                invoiceForSearch.setInvoice_Code(
                        result.getJSONObject("data")
                        .getString("发票代码"));

                invoiceForSearch.setInvoice_Num(
                        result.getJSONObject("data")
                        .getString("发票号码"));

                /*if(invoiceService.find(invoiceForSearch).size()!=0) {
                    throw new DuplicatedException(
                            invoiceForSearch.getInvoice_Code(),
                            invoiceForSearch.getInvoice_Num());
                }*/

                AllInvoiceInfo allInvoiceInfoInsert = new AllInvoiceInfo();

                allInvoiceInfoInsert.setInvoice_Code(
                        invoiceForSearch.getInvoice_Code());

                allInvoiceInfoInsert.setInvoice_Num(
                        invoiceForSearch.getInvoice_Num());

                String invoicedate = result.getJSONObject("data")
                        .getString("开票日期");

                if(invoicedate!=null&&invoicedate.length()!=0){
                    String[] splited1=invoicedate.split("年");
                    String year = splited1[0];
                    String[] splited2=splited1[1].split("月");
                    String month = splited2[0].length()==1?("0"+splited2[0]):splited2[0];
                    String day = splited2[1].replace("日","").length()==1?("0"+splited2[1].replace("日","")):splited2[1].replace("日","");

                    //allInvoiceInfoInsert.setInvoice_Data(year+month+day);
                }

                if(result.getJSONObject("data")
                        .getString("校验码")!=null
                &&!result.getJSONObject("data")
                        .getString("校验码").isEmpty()){

                    allInvoiceInfoInsert.setCheck_Code(result.getJSONObject("data")
                            .getString("校验码"));

                }

                //Taxpayer purchaserInsert = new Taxpayer();
                //Taxpayer sellerInsert = new Taxpayer();

                if(result.getJSONObject("data")
                        .getString("受票方税号")!=null
                        &&!result.getJSONObject("data")
                        .getString("受票方税号").isEmpty()){
                    //purchaserInsert.setTaxpayer_Tax_Id(result.getJSONObject("data")
                    //        .getString("受票方税号"));
                }
                else if(result.getJSONObject("data")
                        .getString("受票方名称").equals("个人")){
                    //purchaserInsert.setTaxpayer_Tax_Id("personal");
                }
                else {
                    //purchaserInsert.setTaxpayer_Tax_Id("unidentified");
                }

                if(result.getJSONObject("data")
                        .getString("受票方名称")!=null
                        &&!result.getJSONObject("data")
                        .getString("受票方名称").isEmpty()){
                    //purchaserInsert.setTaxpayer_Name(result.getJSONObject("data")
                    //        .getString("受票方名称"));
                }

                if(result.getJSONObject("data")
                        .getString("受票方地址、电话")!=null
                        &&!result.getJSONObject("data")
                        .getString("受票方地址、电话").isEmpty()){
                    //purchaserInsert.setTaxpayer_Addr_Tel(result.getJSONObject("data")
                    //        .getString("受票方地址、电话"));
                }

                if(result.getJSONObject("data")
                        .getString("受票方开户行、账号")!=null
                        &&!result.getJSONObject("data")
                        .getString("受票方开户行、账号").isEmpty()){
                    //purchaserInsert.setTaxpayer_Bank(result.getJSONObject("data")
                    //        .getString("受票方开户行、账号"));
                }


                if(result.getJSONObject("data")
                        .getString("销售方税号")!=null
                        &&!result.getJSONObject("data")
                        .getString("销售方税号").isEmpty()){
                    //sellerInsert.setTaxpayer_Tax_Id(result.getJSONObject("data")
                    //        .getString("销售方税号"));
                }
                else if(result.getJSONObject("data")
                        .getString("销售方名称").equals("个人")){
                    //sellerInsert.setTaxpayer_Tax_Id("personal");
                }
                else {
                    //sellerInsert.setTaxpayer_Tax_Id("unidentified");
                }

                if(result.getJSONObject("data")
                        .getString("销售方名称")!=null
                        &&!result.getJSONObject("data")
                        .getString("销售方名称").isEmpty()){
                    //sellerInsert.setTaxpayer_Name(result.getJSONObject("data")
                    //        .getString("销售方名称"));
                }

                if(result.getJSONObject("data")
                        .getString("销售方地址、电话")!=null
                        &&!result.getJSONObject("data")
                        .getString("销售方地址、电话").isEmpty()){
                    //sellerInsert.setTaxpayer_Addr_Tel(result.getJSONObject("data")
                    //        .getString("销售方地址、电话"));
                }

                if(result.getJSONObject("data")
                        .getString("销售方开户行、账号")!=null
                        &&!result.getJSONObject("data")
                        .getString("销售方开户行、账号").isEmpty()){
                    //sellerInsert.setTaxpayer_Bank(result.getJSONObject("data")
                    //        .getString("销售方开户行、账号"));
                }

                //allInvoiceInfoInsert.setPurchaser(purchaserInsert);

                //allInvoiceInfoInsert.setSeller(sellerInsert);

                allInvoiceInfoInsert.setImage_Path(filename);

                allInvoiceInfoService.insert(allInvoiceInfoInsert);




                //System.out.print(result.toString());
                //fileOutputStream = new FileOutputStream(basePath+filename);
                //while ((len = inputStream.read(data)) != -1) {
                //    fileOutputStream.write(data, 0, len);
                //}
            }
            catch(DuplicatedException e){
                throw e;
            }
            catch(OCRFailureException e){
                throw e;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            /*
            json.put("newVersionName", "\""+filename+"\"");
            json.put("fileMd5", "\""+fileMd5+"\"");
            json.put("message", "\""+"不对"+"\"");
            json.put("status", "\""+"true"+"\"");
            */
            //json.put("code", "\"001\"");
            //json.put("message", "\""+"不对"+"\"");
            //json.put("status", "\""+"false"+"\"");
            //System.out.print(json.toString());
            return json;
        }
    }

//    public static void main(String[] args){
//        SpringApplication.run(UserController.class,args);
//    }
}
