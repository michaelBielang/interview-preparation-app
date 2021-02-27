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
public class QuestionNotFoundException extends ApplicationException {
    public QuestionNotFoundException(final String webError, final String internalError) {
        super(webError, internalError);
    }
}
