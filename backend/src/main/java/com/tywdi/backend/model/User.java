package com.tywdi.backend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * Organisation: Codemerger Ldt.
 * Project: backend
 * Package: com.tywdi.backend.model
 * Date: 10.08.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Entity
@Data
public final class User {

    @Id
    private String email;

    @NotBlank(message = "username is mandatory")
    private String username;

    @NotBlank(message = "password is mandatory")
    private String password;

    public User() {
        //dflt ctor for hibernate
    }

    public User(final String username, final String email, final String password) {
        this.username = username;
        this.password = password; // TODO: 10.08.2020 salt
        this.email = email;
    }
}
