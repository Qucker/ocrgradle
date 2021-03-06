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
import com.baidu.aip.*;
import sun.security.pkcs11.Secmod;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

@Controller

@ComponentScan({"mybatiscomponents.beans"
        ,"mybatiscomponents.services"
        ,"springbootcomponents.controller"
        ,"springbootcomponents.exceptionhandler"})
@MapperScan("mybatiscomponents.mapper")
public class BaiduUploadController {

    private boolean baiduHasInitialized=false;

    private static String APP_ID="14370090";

    private static String API_KEY="YB630v8IfK5ZySq4soS5vkan";

    private static String SECRET_KEY="svCxFWAhABfjU0Ogelh1kKrL7u266YsW";

    private static AipOcr client;

    @Resource
    private DetailService detailService;

    @Resource
    private AllInvoiceInfoService allInvoiceInfoService;



    @Resource
    private InvoiceService invoiceService;


    @RequestMapping("/do.baidu.uploader")
    @ResponseBody
    public Map<String, Object> baidu_upload(HttpServletRequest request, HttpServletResponse response) throws IOException {

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
            if(!baiduHasInitialized){
                client=new AipOcr(APP_ID,API_KEY,SECRET_KEY);
                client.setConnectionTimeoutInMillis(2000);
                client.setSocketTimeoutInMillis(60000);
                baiduHasInitialized=true;
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
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
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
                int ch;
                while((ch=inputStream.read())!=-1) {
                    outputStream.write(ch);
                }
                /*if(1==1) {
                    throw new DuplicatedException("12345","34567");
                }*/

                String stringResult = client.vatInvoice(outputStream.toByteArray(),new HashMap<String,String>()).toString();

                JSONObject result = new JSONObject(stringResult);

                //System.out.print(result);

                if(!result.has("words_result")){
                    throw new OCRFailureException(stringResult);
                }

                /*if(result.getJSONObject("data")
                        .getString("发票代码")==null||
                        result.getJSONObject("data")
                                .getString("发票代码").isEmpty()||
                        result.getJSONObject("data")
                                .getString("发票号码")==null||
                        result.getJSONObject("data")
                                .getString("发票号码").isEmpty()){
                    throw new OCRFailureException("未能识别发票代码和发票号码等基本信息");
                }*/

                Invoice invoiceForSearch = new Invoice();

                if(!result.getJSONObject("words_result").has("InvoiceCode")||
                   result.getJSONObject("words_result")
                   .getString("InvoiceCode").isEmpty()||
                   !result.getJSONObject("words_result").has("InvoiceNum")||
                   result.getJSONObject("words_result")
                   .getString("InvoiceNum").isEmpty()){

                    if(result.getJSONObject("words_result").has("InvoiceCode")&&
                    !result.getJSONObject("words_result")
                    .getString("InvoiceCode").isEmpty())
                        invoiceForSearch.setInvoice_Code(
                        result.getJSONObject("words_result")
                        .getString("InvoiceCode"));

                    if(result.getJSONObject("words_result").has("InvoiceNum")&&
                    !result.getJSONObject("words_result")
                    .getString("InvoiceNum").isEmpty())
                        invoiceForSearch.setInvoice_Num(
                        result.getJSONObject("words_result")
                        .getString("InvoiceNum"));

                    if(result.getJSONObject("words_result").has("InvoiceDate")&&
                            !result.getJSONObject("words_result")
                                    .getString("InvoiceDate").isEmpty()){

                        String invoicedate = result.getJSONObject("words_result")
                                .getString("InvoiceDate");

                        if(invoicedate!=null&&invoicedate.length()!=0){
                            String[] splited1=invoicedate.split("年");
                            String year = splited1[0];
                            String[] splited2=splited1[1].split("月");
                            String month = splited2[0].length()==1?("0"+splited2[0]):splited2[0];
                            String day = splited2[1].replace("日","").length()==1?("0"+splited2[1].replace("日","")):splited2[1].replace("日","");

                            invoiceForSearch.setInvoice_Date(year+month+day);
                        }


                    }

                    if(result.getJSONObject("words_result").has("CheckCode")&&
                            !result.getJSONObject("words_result")
                                    .getString("CheckCode").isEmpty())
                        invoiceForSearch.setCheck_Code(
                                result.getJSONObject("words_result")
                                        .getString("CheckCode"));

                    if(allInvoiceInfoService.findByElements(invoiceForSearch).size()!=0) {
                        throw new DuplicatedException(
                                invoiceForSearch.getInvoice_Code(),
                                invoiceForSearch.getInvoice_Num());
                    }
                }
                else{

                    invoiceForSearch.setInvoice_Code(
                            result.getJSONObject("words_result")
                                    .getString("InvoiceCode"));

                    invoiceForSearch.setInvoice_Num(
                            result.getJSONObject("words_result")
                                    .getString("InvoiceNum"));

                    if(allInvoiceInfoService.findByElements(invoiceForSearch).size()!=0) {
                        throw new DuplicatedException(
                                invoiceForSearch.getInvoice_Code(),
                                invoiceForSearch.getInvoice_Num());
                    }
                }


                allInvoiceInfoService.insert(result,"D://asd");


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
}
