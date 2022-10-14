package com.u2d.signer.interceptor;

import com.u2d.signer.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoggerInterceptor implements HandlerInterceptor {

    private static final String HEADER_CLIENT_ID = "Client-Id";
    private static final String HEADER_CORRELATION_ID = "Correlation-Id";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Request: Method: {}, Path: {}, CorrelationId: {}, clientId: {}, Date: {}", request.getMethod(), request.getRequestURI(), MDC.get(HEADER_CORRELATION_ID), request.getHeader(HEADER_CLIENT_ID), DateUtil.now());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("Response: Status: {}, CorrelationId: {}, clientId: {}, Date: {}", response.getStatus(), MDC.get(HEADER_CORRELATION_ID), request.getHeader(HEADER_CLIENT_ID), DateUtil.now());
    }
}
