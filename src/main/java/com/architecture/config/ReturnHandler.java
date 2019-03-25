package com.architecture.config;

import com.architecture.pojo.common.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class ReturnHandler extends RequestResponseBodyMethodProcessor {
 
    public ReturnHandler(List<HttpMessageConverter<?>> converters) {
        super(converters);
    }
 
 
 
    /**
     * 该处理程序是否支持给定的方法返回类型(只有返回true才回去调用handleReturnValue)
     */
    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        if (methodParameter.hasMethodAnnotation(ResponseBody.class)
                || (!methodParameter.getDeclaringClass().equals(ModelAndView.class))
                && methodParameter.getMethod().getDeclaringClass().isAnnotationPresent(RestController.class)) {
            return true;
        }
        return false;
    }
 
    /**
     * 处理给定的返回值
     * 通过向 ModelAndViewContainer 添加属性和设置视图或者
     * 通过调用 ModelAndViewContainer.setRequestHandled(true) 来标识响应已经被直接处理(不再调用视图解析器)
     */
    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws IOException, HttpMediaTypeNotAcceptableException {
        WebResponse resp = new WebResponse();
        resp.setSuccess(true);
        resp.setData(o);
        /**
         * 标识请求是否已经在该方法内完成处理
         */
        modelAndViewContainer.setRequestHandled(true);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        //获取开始时间
        Object startTimerObj = request.getAttribute(ProcessInterceptor.requestName);
        if (startTimerObj != null && (startTimerObj instanceof Long)){
            Long startTimer=(Long) startTimerObj;
            Long millis=System.currentTimeMillis()-startTimer;
            //记录处理时间
            resp.setCostTime(millis);
        }
        super.handleReturnValue(resp,methodParameter,modelAndViewContainer,nativeWebRequest);
    }

}