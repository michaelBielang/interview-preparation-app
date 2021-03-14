package com.tywdi.backend.exceptionhandler;

import io.jsonwebtoken.SignatureException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.exceptionhandler
 * Date: 15.09.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */
public interface ControllerExceptionHandlerInterface {

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex);

    @ExceptionHandler(SignatureException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    ErrorMsg handleSignatureException(SignatureException signatureException, WebRequest webRequest);

    @Data
    //getter required for jackson
    class ErrorMsg {
        private final String error;
    }
}
