package com.u2d.signer.config.interceptor;

import com.u2d.signer.interceptor.CorrelationIdInterceptor;
import com.u2d.signer.interceptor.LoggerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfigInterceptor implements WebMvcConfigurer {

    @Autowired
    LoggerInterceptor loggerInterceptor;

    @Autowired
    CorrelationIdInterceptor correlationIdInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(correlationIdInterceptor);
        registry.addInterceptor(loggerInterceptor);
    }
}
