package com.architecture.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.HttpEntityMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {
 
    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;
 
    /**
     * 注册SpringMVC Interceptor
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器,为每个请求统计处理耗时
        registry.addInterceptor(new ProcessInterceptor());
    }
 
    @Bean
    public ReturnHandler getReturnHandler(){
        List<HttpMessageConverter<?>> messageConverters = requestMappingHandlerAdapter.getMessageConverters();
        return new ReturnHandler(messageConverters);//初始化过滤器
    }
 
 
    /**
     * 解决ReturnHandler不生效问题
     */
    @PostConstruct
    public void init() {
        final List<HandlerMethodReturnValueHandler> originalHandlers = new ArrayList<>(
                requestMappingHandlerAdapter.getReturnValueHandlers());
        final int deferredPos = obtainValueHandlerPosition(originalHandlers, HttpEntityMethodProcessor.class);
        originalHandlers.add(deferredPos - 1, getReturnHandler());
        requestMappingHandlerAdapter.setReturnValueHandlers(originalHandlers);
        return;
    }
 
    private int obtainValueHandlerPosition(final List<HandlerMethodReturnValueHandler> originalHandlers, Class<?> handlerClass) {
        for (int i = 0; i < originalHandlers.size(); i++) {
            final HandlerMethodReturnValueHandler valueHandler = originalHandlers.get(i);
            if (handlerClass.isAssignableFrom(valueHandler.getClass())) {
                return i;
            }
        }
        return -1;
    }
 
}