package cn.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * MyExceptionHandler
 */
@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandle(Exception e,HttpServletRequest request) throws Exception {
        logger.error("url:{};exception:{}", request.getRequestURI(), e);
        //如果异常本身带有状态码，就无须拦截，直接抛出
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null)
            throw e;
        ModelAndView mv=new ModelAndView();
        mv.addObject("url",request.getRequestURI());
        mv.addObject("exception",e);
        mv.setViewName("error/error");
        return mv;
    }
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
}