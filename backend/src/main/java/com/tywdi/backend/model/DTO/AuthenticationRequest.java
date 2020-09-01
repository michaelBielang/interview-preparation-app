package com.tywdi.backend.model.DTO;

import lombok.Data;

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

    private String email;
    private String password;
}
