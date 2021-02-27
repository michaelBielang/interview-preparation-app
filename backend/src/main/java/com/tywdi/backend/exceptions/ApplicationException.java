package com.tywdi.backend.exceptions;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.exceptions
 * Date: 27.02.2021
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

public abstract class ApplicationException extends RuntimeException {

    private final String webError;

    public ApplicationException(final String webError, final String internalError) {
        super(internalError);
        this.webError = webError;
    }

    @Override
    public String toString() {
        return "ApplicationException{" +
                "webError='" + webError + '\'' +
                '}';
    }
}
