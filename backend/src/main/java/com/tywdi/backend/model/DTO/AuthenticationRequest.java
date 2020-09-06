package com.tywdi.backend.model.DTO;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.model.DTO
 * Date: 31.08.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Data
public class AuthenticationRequest {

    @Email
    private String email;

    @Length(min = 5)
    private String password;

    public AuthenticationRequest(final String email, final String password) {
        this.email = email;
        this.password = password;
    }


}
