package com.u2d.signer.interceptor;


import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorrelationIdInterceptor implements HandlerInterceptor {

    private static final String HEADER_CORRELATION_ID = "Correlation-Id";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (StringUtils.isBlank(request.getHeader(HEADER_CORRELATION_ID))) {
            MDC.put(HEADER_CORRELATION_ID, getCorrelationId());
            return true;
        }
        MDC.put(HEADER_CORRELATION_ID, request.getHeader(HEADER_CORRELATION_ID));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(HEADER_CORRELATION_ID);
    }

    private String getCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
