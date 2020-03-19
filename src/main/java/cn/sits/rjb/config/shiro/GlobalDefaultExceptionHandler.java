package cn.sits.rjb.config.shiro;

import cn.sits.rjb.common.data.BaseResponse;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
  * 1、新建一个Class,这里取名为GlobalDefaultExceptionHandler
  * 2、在class上添加注解，@ControllerAdvice;
  * 3、在class中添加一个方法
  * 4、在方法上添加@ExcetionHandler拦截相应的异常信息；
  * 5、如果返回的是View -- 方法的返回值是ModelAndView;
  * 6、如果返回的是String或者是Json数据，那么需要在方法上添加@ResponseBody注解.
  */
@Controller
@ControllerAdvice
public class GlobalDefaultExceptionHandler {



    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public BaseResponse defaultExceptionHandler(HttpServletRequest req, Exception e){
        BaseResponse response = new BaseResponse();
        Long endTime;
        Long startTime = System.currentTimeMillis();
        endTime = System.currentTimeMillis();
        response.elapseTime = endTime - startTime;
        response.isSuccess = false;
        response.state = 500;
        response.message = "暂无权限";
        return response;
    }


    @RequestMapping(value = "/unauth")
    @ResponseBody
    public BaseResponse login1() {
        BaseResponse response = new BaseResponse();
        Long endTime;
        Long startTime = System.currentTimeMillis();
        endTime = System.currentTimeMillis();
        response.elapseTime = endTime - startTime;
        response.isSuccess = false;
        response.state = 500;
        response.message = "暂无权限";
        return response;
    }
}
