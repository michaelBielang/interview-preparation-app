package com.tywdi.backend.exceptionhandler;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.exceptionhandler
 * Date: 15.09.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */
public interface FilterChainExceptionHandlerInterface {

    void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain);
}
