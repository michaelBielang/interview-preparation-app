package com.tywdi.backend.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.exceptionhandler
 * Date: 13.09.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Component
@Slf4j
public class FilterChainExceptionHandler extends OncePerRequestFilter implements FilterChainExceptionHandlerInterface {

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    public void doFilterInternal(final HttpServletRequest request,
                                 final HttpServletResponse response,
                                 final FilterChain filterChain) {
        try {
            filterChain.doFilter(request, response);
        } catch (final Exception exception) {
            log.error("Spring Security Filter Chain Exception:", exception);
            resolver.resolveException(request, response, null, exception);
        }
    }
}
