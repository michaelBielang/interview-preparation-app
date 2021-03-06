package com.tywdi.backend.exceptionhandler;

import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.controller
 * Date: 10.08.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler implements ControllerExceptionHandlerInterface {

    @Override
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(final EntityNotFoundException entityNotFoundException) {
        log.error(entityNotFoundException.getMessage());
        return new ResponseEntity<>(entityNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @Override
    @ExceptionHandler(SignatureException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMsg handleSignatureException(final SignatureException signatureException, final WebRequest webRequest) {

        log.error("error fetching for user {} with error {} with parameters {}",
                webRequest.getHeader("authorization"), signatureException.getMessage(), webRequest.getParameterMap());

        return new ErrorMsg(signatureException.getMessage());
    }

}
