package com.tywdi.backend.exceptionhandler;

import com.tywdi.backend.exceptions.ApplicationException;
import com.tywdi.backend.exceptions.QuestionNotFoundException;
import com.tywdi.backend.exceptions.UserExistsException;
import com.tywdi.backend.exceptions.UserNotPresentException;
import com.tywdi.backend.model.web.WebError;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.exceptionhandler
 * Date: 06.12.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@ControllerAdvice
@Slf4j
public final class GlobalExceptionHandler {

    private static final String DEV_PROFILE = "h2";

    private final boolean devProfileActive;

    @Autowired
    public GlobalExceptionHandler(final Environment environment) {
        this.devProfileActive = Arrays.asList(environment.getActiveProfiles())
                .contains(DEV_PROFILE);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<WebError> handleException(final Exception exception) {

        if (exception instanceof UserExistsException) {
            return handleApplicationException(HttpStatus.BAD_REQUEST, (ApplicationException) exception);
        } else if (exception instanceof UserNotPresentException) {
            return handleApplicationException(HttpStatus.BAD_REQUEST, (ApplicationException) exception);
        } else if (exception instanceof QuestionNotFoundException) {
            return handleApplicationException(HttpStatus.NOT_FOUND, (ApplicationException) exception);
        } else {
            return handleInternalException(HttpStatus.INTERNAL_SERVER_ERROR, exception);
        }
    }


    private ResponseEntity<WebError> handleApplicationException(final HttpStatus httpStatus,
                                                                final ApplicationException applicationException) {
        final WebError webError = new WebError();

        if (devProfileActive) {
            webError.setErrorMessage(ExceptionUtils.getMessage(applicationException));
        } else {
            webError.setErrorMessage(applicationException.getMessage());
        }

        return new ResponseEntity<>(webError, httpStatus);
    }


    private ResponseEntity<WebError> handleInternalException(final HttpStatus httpStatus,
                                                             final Exception exception) {
        final WebError webError = new WebError();

        if (devProfileActive) {
            webError.setErrorMessage(ExceptionUtils.getMessage(exception));
        }

        return new ResponseEntity<>(webError, httpStatus);
    }


}
