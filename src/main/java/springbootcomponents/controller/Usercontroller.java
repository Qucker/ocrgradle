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


@Controller

@ComponentScan({"mybatiscomponents.beans"
        ,"mybatiscomponents.services"
        ,"springbootcomponents.controller"
        ,"springbootcomponents.exceptionhandler"})
@MapperScan("mybatiscomponents.mapper")

public class Usercontroller {

    @RequestMapping("/upload")
    public String say(){
        return "uploader.html";
    }

}
