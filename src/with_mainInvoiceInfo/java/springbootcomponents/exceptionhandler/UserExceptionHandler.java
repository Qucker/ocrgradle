package springbootcomponents.exceptionhandler;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import springbootcomponents.exceptionhandler.*;
/**
 * 控制器的异常处理类
 * @author Shinelon
 *
 */

@ComponentScan({"mybatiscomponents.beans"
        ,"mybatiscomponents.services"
        ,"springbootcomponents.controller"
        ,"springbootcomponents.exceptionhandler"})
@MapperScan("mybatiscomponents.mapper")

//这个注解是指这个类是处理其他controller抛出的异常
@ControllerAdvice
public class UserExceptionHandler {

    //这个注解是指当controller中抛出这个指定的异常类的时候，都会转到这个方法中来处理异常
    @ExceptionHandler(DuplicatedException.class)
    //将返回的值转成json格式的数据
    @ResponseBody
    //返回的状态码
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)     //服务内部错误
    public String handlerDuplicatedException(DuplicatedException ex){
        String result;
        result="发票已存在于数据库，发票代码："+ex.getInvoiceCode()
                +"，发票号码：" + ex.getInvoiceNum();
        return result;
    }

    @ExceptionHandler(OCRFailureException.class)
    @ResponseBody
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    public String handlerOCRFailureException(OCRFailureException ex){
        String result;
        result="发票识别失败，原因为："+ex.getMessage();
        return result;
    }
}
