package com.bonial.challenge.interceptor;

import com.bonial.challenge.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
@Component
public class CorrelationIdInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
                             final Object handler) {
        final String correlationId = getCorrelationIdFromHeader(request);
        MDC.put(Constants.CORRELATION_ID_LOG_VAR_NAME, correlationId);
        return true;
    }

    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
                                final Object handler, final Exception ex) throws Exception {
        MDC.remove(Constants.CORRELATION_ID_LOG_VAR_NAME);
    }

    private String getCorrelationIdFromHeader(final HttpServletRequest request) {
        String correlationId = request.getHeader(Constants.CORRELATION_ID_HEADER_NAME);
        if (correlationId == null || correlationId.isEmpty()) {
            correlationId = generateUniqueCorrelationId();
        }
        return correlationId;
    }

    private String generateUniqueCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
