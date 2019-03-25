package com.architecture.config;

import com.architecture.pojo.common.WebResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    /**
     * 处理Rest接口请求时的异常
     * @param request
     * @param response
     * @param ex
     * @return
     */
    @ExceptionHandler(InvalidReqException.class)
    @ResponseBody
    public WebResponse restError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        InvalidReqException restException = (InvalidReqException) ex;
        WebResponse resp = new WebResponse();
        resp.setSuccess(false);
        resp.setStatusCode(restException.getErrorCode());
        resp.setErrorMessage(restException.getErrorMessage());
        return resp;
    }
}