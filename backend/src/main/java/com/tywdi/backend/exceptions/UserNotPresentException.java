package com.tywdi.backend.exceptions;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.exceptions
 * Date: 06.12.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

public class UserNotPresentException extends ApplicationException {

    public UserNotPresentException(final String webError, final String internalError) {
        super(webError, internalError);
    }
}
